import request from '@/util/request'

/**
 * 查询字典列表
 */
export function getDictList(type) {
  return request({
    url: `/ingredient-sys/dict/list?type=${type}`,
    method: 'get'
  })
}

export function getDictList2(type) {
  return request({
    url: '/ingredient-sys/dict/list?type=' + type,
    method: 'get'
  })
}

export function getDictList3(type) {
  return request({
    url: '/ingredient-sys/dict/list',
    params: {
      type: type
    },
    method: 'get'
  })
}