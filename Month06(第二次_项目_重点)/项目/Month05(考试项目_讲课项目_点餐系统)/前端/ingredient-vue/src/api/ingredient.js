import request from '@/util/request'

/**
 * 添加接口
 */
export function addIngredient(ingredient) {
  return request({
    url: '/ingredient-sys/ingredient',
    data: ingredient,
    method: 'post',
  })
}

/**
 * 查询接口
 */
export function getIngredientList(pageNumber, pageSize, queryForm) {
  return request({
    url: `/ingredient-sys/ingredient?pageNumber=${pageNumber}&pageSize=${pageSize}`,
    params: queryForm,
    method: 'get'
  })
}

/**
 * 获取所有非菜品类型列表
 */
export function getTypeList() {
  return request({
    url: '/ingredient-sys/ingredient/list',
    method: 'get'
  })
}

/**
 * 删除菜品或食材
 */
export function deleteIngredient(id) {
  return request({
    url: `/ingredient-sys/ingredient/${id}`,
    method: 'delete'
  })
}

export function updateIngredient(id, ingredient) {
  return request({
    url: `/ingredient-sys/ingredient/${id}`,
    method: 'put',
    data: ingredient
  })
}