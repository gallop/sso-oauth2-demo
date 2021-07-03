'use strict'
// JavaScript 严格模式（strict mode）即在严格的条件下运行。
const path = require('path');
const { BundleAnalyzerPlugin } = require('webpack-bundle-analyzer'); // 打包分析
// npm install --save-dev compression-webpack-plugin@1.1.12@
const CompressionPlugin = require('compression-webpack-plugin'); // 引入gzip压缩插件
const productionGzipExtensions = /\.(js|css|json|txt|html|ico|svg)(\?.*)?$/i; // 开启gzip压缩， 按需写入

//mock
//const express = require('express')
//const app = express()
const goodsList = require('./mock/goods.json')

function resolve (dir) {
  return path.join(__dirname, dir);
}

const IS_PROD = ["production", "prod"].includes(process.env.NODE_ENV);
//const IS_DEV = ["development"].includes(process.env.NODE_ENV);

module.exports = {
  publicPath: IS_PROD ? '/site/vue-demo/' : '/', // 公共路径,默认'/'，部署应用包时的基本 URL
  outputDir: process.env.outputDir || 'dist', // 'dist', 将构建好的文件输出到哪里
  assetsDir: 'static', // 相对于outputDir的静态资源(js、css、img、fonts)目录
  // 指定生成的 index.html 的输出路径
  indexPath: 'index.html',

  // 是否使用包含运行时编译器的 Vue 构建版本。设置为 true 后你就可以在 Vue 组件中使用 template 选项了，但是这会让你的应用额外增加 10kb 左右。
  runtimeCompiler: false,
  // 生产环境关闭 source map
  productionSourceMap: false,
  // 是否为 Babel 或 TypeScript 使用 thread-loader。
  // 该选项在系统的 CPU 有多于一个内核时自动启用，仅作用于生产构建。
  parallel: require("os").cpus().length > 1,


  // 是一个函数，会接收一个基于 webpack-chain 的 ChainableConfig 实例。
  // 允许对内部的 webpack 配置进行更细粒度的修改。
  chainWebpack: config => {
    // 配置别名
    config.resolve.alias
        .set('@', resolve('src'))
        .set('@assets', resolve('src/assets'))
        .set('@components', resolve('src/components'))
        .set('@api', resolve('src/api'))
        .set('@utils', resolve('src/utils'))
        .set('@views', resolve('src/views'));
    // 压缩图片
    // 需要 cnpm i -D image-webpack-loader, 用npm 安装 会安装不全导致错误产生
    /*config.module
        .rule("images")
        .test(/\.(png|jpe?g|gif|svg)(\?.*)?$/)
        .use("image-webpack-loader")
        .loader("image-webpack-loader")
        .options({
          mozjpeg: { progressive: true, quality: 65 },
          optipng: { enabled: false },
          pngquant: { quality: [0.65, 0.9], speed: 4 },
          gifsicle: { interlaced: false },
          webp: { quality: 75 }
        });*/

    // svg rule loader
    const svgRule = config.module.rule('svg') // 找到svg-loader
    svgRule.uses.clear() // 清除已有的loader, 如果不这样做会添加在此loader之后
    svgRule.exclude.add(/node_modules/) // 正则匹配排除node_modules目录
    svgRule // 添加svg新的loader处理
        .test(/\.svg$/)
        .use('svg-sprite-loader')
        .loader('svg-sprite-loader')
        .options({
          symbolId: 'icon-[name]',
        });

    // set preserveWhitespace
    config.module
        .rule('vue')
        .use('vue-loader')
        .loader('vue-loader')
        .tap(options => {
          // 如果设置为false,标签之间的空白将会忽略，这可能会导致性能稍好，但可能会影响嵌入式元素的布局。
          // true表示编译的渲染函数将会保留HTML标记之间的所有空白字符
          options.compilerOptions.preserveWhitespace = true
          return options
        })
        .end()

    // 打包分析, 打包之后自动生成一个名叫report.html文件(可忽视)
    if (IS_PROD) {
      config.plugin("webpack-report").use(BundleAnalyzerPlugin, [
        {
          analyzerMode: "static"
        }
      ]);
    }

  },
  configureWebpack: (config) => {
    if (IS_PROD) {
      config.plugins.push(new CompressionPlugin({ // gzip压缩配置
        test: productionGzipExtensions, // 匹配文件名
        threshold: 10240, // 对超过10kb的数据进行压缩
        deleteOriginalAssets: false // 是否删除原文件
      }))
    }
  },
  css:{
    loaderOptions: {
      less: {
        modifyVars: {
          // 直接覆盖变量
          'border-color': '#eee',
          // 或者可以通过 less 文件覆盖（文件路径为绝对路径）
          hack: `true; @import "~@/assets/css/vantVars.less";`,
        }
      }
    }
  },
  devServer: {
    //mock code
    before(app) {
      app.get('/api/getGoods', function (req, res) {
        res.json(goodsList);
      });
    },
    host: '0.0.0.0',
    port: 8088, // 端口号
    https: false, // https:{type:Boolean}
    open: false, // 配置自动启动浏览器  open: 'Google Chrome'-默认启动谷歌
    hotOnly: true, // 热更新
    //配置多个跨域
    proxy: {
      '/api': { // 代理api
        // http://192.168.2.125:9999
        target: 'http://localhost:8086/user', // --------代理服务器api地址
        ws: false, // proxy启用websockets
        changOrigin: true, // 是否跨域
        // 重写路径
        pathRewrite: {
          '^/api': ''
        }
        // 然后你就可以在代码中使用 /api 来代替http://localhost:9527/mock啦
      },
      '/api2': { // 代理api
        // http://192.168.2.125:9999
        target: 'http://localhost:8088', // --------代理服务器api地址
        ws: false, // proxy启用websockets
        changOrigin: true, // 是否跨域
        // 重写路径
        pathRewrite: {
          '^/api2': ''
        }
        // 然后你就可以在代码中使用 /api 来代替http://localhost:9527/mock啦
      },
      '/imgApi': {
        target: 'http://192.168.2.125:8888',
        ws: false, // proxy启用websockets
        changOrigin: true, // 是否跨域
        // 重写路径
        pathRewrite: {
          '^/imgApi': ''
        }
      }
    }
  }
}
