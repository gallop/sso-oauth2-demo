import {post} from '@/utils/ajax'


export function getAuthToken(data) {
  console.log("调用了api接口--getAuthToken")
  return post('/sso/getToken',data,false)
}
