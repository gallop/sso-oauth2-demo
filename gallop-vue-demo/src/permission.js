import router from './router'
import { getToken } from '@/utils/cookie' // getToken from cookie
import cas from '@utils/sso'


const whiteList = ['/login', '/ssoLogin', '/401']// no redirect whitelist

router.beforeEach((to, from, next) => {
  if (getToken()) { // determine if there has token
    console.log("to-url="+to.path)
    /* has token*/
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
      next()
    }
  } else {
    /* has no token*/
    if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
      next()
    } else {
      console.log('no token!!!')
      //next({ path: '/401', replace: true, query: { noGoBack: true }}) // 重定向到sso 登入处理页面
      //next({ path: '/ssoLogin' })
      cas.enableCasAuth().then(()=>{
        next()
      })

    }
  }
})

router.afterEach(() => {

})
