import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
const Tabbar = () => import('@components/Tabbar/');

const routes = [
  {
    path: '/ssoLogin',
    name: 'ssoLogin',
    components: {
      default: () => import('@views/sso/SsoLogin.vue')
    },
    meta: {title: 'sso Login', keepAlive: false}
  },
  {
    path: '/callback',
    name: 'callback',
    components: {
      default: () => import('@views/sso/callback.vue')
    },
    meta: {title: 'sso callback', keepAlive: false}
  },
  {
    path: '/401',
    component: () => import('@/views/errorPage/401'),
    hidden: true
  },
  {
    path: '/',
    name: 'worktable',
    components: {
      default: () => import('@views/Worktable.vue'),
      tabbar: Tabbar
    },
    meta: {title: '工作台', keepAlive: false}
  },
  {
    path: '/analysis',
    name: 'analysis',
    component: () => import('@views/Analysis.vue'),
    meta: {title: '分析', keepAlive: false}
  },
  {
    path: '/subject',
    name: 'subject',
    component: () => import('@views/Subject.vue'),
    meta: {title: '专题', keepAlive: false}
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('@views/About.vue'),
    meta: {title: '其他', keepAlive: false}
  }
]

const router = new VueRouter({
  mode: 'history', // hash history
  base: process.env.BASE_URL,
  routes
})

export default router
