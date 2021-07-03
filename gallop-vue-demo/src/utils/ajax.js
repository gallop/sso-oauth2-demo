import axios from 'axios'
import store from '@/store'
//import vm from '../main'
import {getToken, TokenKey} from '@/utils/cookie'

/* 全局默认配置 */
//axios.defaults.withCredentials = true;
var http = axios.create({
  baseURL: process.env.VUE_APP_API, // api 的 base_url
  timeout: 5000
})

/* 请求拦截器 */
http.interceptors.request.use(
    config => {
      config.headers['Content-Type'] = 'application/json;charset=UTF-8'
      config.headers['Access-Control-Allow-Origin'] = '*'
      config.headers.timestamp = Math.floor(new Date().getTime() / 1000)
      if (store.getters.token) {
        config.headers['Access-Control-Allow-Origin'] = '*'
        // 让每个请求携带token-- ['X-XXX-Admin-Token']为自定义key 请根据实际情况自行修改
        config.headers[TokenKey] = getToken()
      }
      //config.headers.token = sessionStorage.getItem('token') || ''
      // 接口没返回时显示loadin
      if (config.loading === true) {
        //vm.$loading.show()
      }
      return config
    },
    error => {
      //vm.$loading.hide()
      return Promise.reject(error)
    }
)

/* 响应拦截器 */
http.interceptors.response.use(
    res => {
      //vm.$loading.hide()
      return res
    },
    error => {
      //vm.$loading.hide()
      return Promise.reject(error)
    }
)

function get(url, loading) {
  return new Promise((resolve, reject) => {
    http.get(url, {loading: loading}).then(
        response => {
          resolve(response.data)
        },
        err => {
          reject(err)
        }
    ).catch(error => {
      reject(error)
    });
  })
}

function post (url, data, loading) {
  return new Promise((resolve, reject) => {
    http.post(url, data, {loading: loading }).then(
        response => {
          resolve(response.data)
        },
        err => {
          reject(err)
        }
    ).catch(error => {
      reject(error)
    })
  })
}

export { http, get, post }

