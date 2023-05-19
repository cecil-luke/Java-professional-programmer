import axios from 'axios'
import { Message } from 'element-ui'
import router from '@/router'

const request = axios.create({
  // http://localhost:8000
  baseURL: process.env.VUE_APP_BASE_URL
})

// 白名单. 这两个接口不需要携带token
const white_list = ['/user/login', '/getCode']

/* axios请求拦截器 */
request.interceptors.request.use(config => {
  const { url } = config
  if (white_list.indexOf(url) < 0) {
    // 设置请求头
    config.headers['Authorization'] = sessionStorage.getItem('token')
  }

  // 必须return config. 没有这句就发不出请求了
  return config
}, e => {
  return Promise.reject(e)
})

/* axios响应拦截器 */
request.interceptors.response.use(resp => {
  const { url } = resp.config
  // 如果是登录接口和验证码接口, 直接返回调用处
  if (white_list.indexOf(url) >= 0) {
    return resp
  }

  const { code, msg } = resp.data
  if (code === 403) {
    Message.error(msg)
    sessionStorage.clear()

    // 跳转到登录路由
    router.push('/login')
    return
  }
  return resp
}, e => {
  console.log(e)
  return Promise.reject(e)
})

export default request