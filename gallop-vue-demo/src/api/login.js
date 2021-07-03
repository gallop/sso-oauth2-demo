import {http,post} from '@/utils/ajax'

export function loginByUsername(username, password) {
  const data = {
    username,
    password
  }
  return post('/auth/login',data)
  /*return http({
    url: '/auth/login',
    method: 'post',
    data
  })*/
}

export function logout() {
  return http({
    url: '/auth/logout',
    method: 'post'
  })
}

export function getUserInfo() {
  return http({
    url: '/auth/info',
    method: 'get'
  })
}
