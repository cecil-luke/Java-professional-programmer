import request from '@/util/request'
/* import axios from 'axios'

const request = axios.create({
  baseURL: 'http://localhost:9998'
}) */

export function getCode() {
  return request({
    url: '/code/getCode',
    method: 'get'
  })
}