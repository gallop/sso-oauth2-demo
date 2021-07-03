import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { get, post } from './utils/ajax'
import Loading from './components/loading'
import './permission' // permission control
import * as echarts from 'echarts'
import Element from 'element-ui'

import './assets/css/reset.css'
import "./assets/icons";

// Vue.prototype.$echarts = echarts
Vue.config.productionTip = false
Vue.prototype.$echarts = echarts
Vue.prototype.$http = { get, post }
Vue.prototype.$loading = Loading

Vue.use(Element, {
  size: 'medium' // set element-ui default size
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
