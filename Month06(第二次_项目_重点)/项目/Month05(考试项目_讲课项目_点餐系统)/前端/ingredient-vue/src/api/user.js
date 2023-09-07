import request from '@/util/request'
import qs from 'qs'

export function login(loginForm) {
  return request({
    url: '/ingredient-sys/user/login',
    method: 'post',
    // data: loginForm  = json类型
    data: qs.stringify(loginForm)  // 表单参数
  })
}

export function register(loginForm) {
  return request({
    url: '/ingredient-sys/user/register',
    method: 'post',
    // data: loginForm  = json类型
    //data: qs.stringify(loginForm)  // 表单参数
    data: loginForm
  })
}


export function logout() {
  return request({
    url: '/ingredient-sys/user/logout',
    method: 'get',
  })
}