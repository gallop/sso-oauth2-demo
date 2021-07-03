<template>
    <div id="app">
        <router-view v-if="!$route.meta.keepAlive"></router-view>
        <keep-alive>
            <router-view v-if="$route.meta.keepAlive"></router-view>
        </keep-alive>
        <tabbar v-if="isShowNav"></tabbar>
    </div>
</template>

<script>
  import Tabbar from '@/components/Tabbar'

  export default {
    name: 'App',
    components: {
      Tabbar
    },
    data() {
      return{
        isShowNav: true, // 是否显示底部导航 Tab
        activeNavIndex: 0, // 底部导航激活下标
        isRouterAlive: true, // 用于刷新页面用
        navTabs: ['home', 'Category', 'cart', 'me'] // 底部导航
      }
    },
    watch: {

    }
  }
</script>

<style>
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
    }

</style>
