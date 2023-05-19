import request from '@/util/reqeust'
import qs from 'qs'

/**
 * 添加接口
 * post /car
 * 参数类型 application/json
 */
export function addCar(car) {
  // this.$ajax.post('/car', car).then(resp => {}).catch(e => {})
  return request({
    url: '/car',
    method: 'post',
    data: car
  })
}

/**
 * 查询接口
 * get /car
 */
export function getCarList(pageNumber, pageSize, queryForm) {
  // this.$ajax.get(`/car?pageNumber=${pageNumber}&pageSize=${pageSize}`, { params: queryForm })
  return request({
    url: `/car?pageNumber=${pageNumber}&pageSize=${pageSize}`,
    method: 'get',
    params: queryForm,
    paramsSerializer: {
      serialize: function (params) {
        // 序列化数组
        // https://www.npmjs.com/package/qs
        return qs.stringify(params, { arrayFormat: 'comma' })
      }
    }
  })
}

/**
 * 删除接口
 * delete /car
 */
export function deleteCar(ids) {
  return request({
    url: '/car',
    method: 'delete',
    data: ids
  })
}

/**
 * 更新接口
 * put /car/{id}
 */
export function updateCar(id, car) {
  return request({
    url: `/car/${id}`,
    method: 'put',
    data: car
  })
}

/**
 * 品牌列表接口
 */
export function getBrand() {
  return request({
    url: '/car/brand',
    method: 'get'
  })
}

/**
 * 车系列表接口
 */
export function getSeries(brand) {
  return request({
    url: `/car/series?brand=${brand}`,
    method: 'get'
  })
}

