import request from '@/util/reqeust'
import qs from 'qs'

/**
 * 验证码接口
 */
export function getCode() {
  return request({
    url: '/getCode',
    method: 'get'
  })
}

/**
 * 登录接口
 */
export function login(loginForm) {
  return request({
    url: '/user/login',
    method: 'post',
    data: qs.stringify(loginForm) // 表单参数
  })
}