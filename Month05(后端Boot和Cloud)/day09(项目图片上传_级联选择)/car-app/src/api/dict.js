import request from "@/util/reqeust";

export function getDictList(type) {
  // this.$ajax.get('/dict/list?type=' + type)
  // this.$ajax.get(`/dict/list?type=${type}`)
  // this.$ajax.get('/dict/list', { params: { type:type } })
  return request({
    url: '/dict/list?type=' + type,
    method: 'get'
  })

  /*
  return request({
    url: `/dict/list?type=${type}`,
    method: 'get'
  })
  */

  /*
  return request({
    url: `/dict/list`,
    params: {
      type: type
    },
    method: 'get'
  })
  */
}