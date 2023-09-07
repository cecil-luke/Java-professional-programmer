import axios from 'axios'
import { Message } from 'element-ui'
import router from '@/router'

const request = axios.create({
  baseURL: process.env.VUE_APP_URL
})

const white_list = ["/code/getCode", "/ingredient-sys/user/login",
  "/ingredient-sys/user/register"]

/* 请求拦截器 */
request.interceptors.request.use(config => {
  const url = config.url
  if (white_list.indexOf(url) >= 0) {
    return config 
  }

  const token = sessionStorage.getItem('token')
  // 添加请求头
  config.headers['Authorization'] = token

  return config
}, e => {
  return Promise.reject(error)
})

/* 添加响应拦截器(结果返回到then()之前执行) */
request.interceptors.response.use(function (response) {
  // const data = response.data
  const { data } = response

  const { code, message } = data

  if (code === 403) {
    Message.error(message)
    sessionStorage.clear()
    router.push('/login')
    return Promise.reject(error)
  }

  return data
}, function (error) {
  return Promise.reject(error)
});

export default request
