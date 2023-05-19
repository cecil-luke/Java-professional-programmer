import request from '@/util/reqeust'

/**
 * 调用省市区接口
 * GET /area/list
 */
export function getAreaList() {
  // this.$ajax.get('/area/list').then().catch()
  return request({
    url: '/area/list',
    method: 'get'
  })
}