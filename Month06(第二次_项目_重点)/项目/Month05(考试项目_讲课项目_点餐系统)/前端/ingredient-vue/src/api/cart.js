import request from '@/util/request'

/**
 * 添加、减少
 */
export function modifyCart(cart) {
  return request({
    url: '/ingredient-sys/cart',
    method: 'post',
    data: cart
  })
}

/**
 * 列表
 */
export function getCartList() {
  return request({
    url: '/ingredient-sys/cart',
    method: 'get',
  })
}

/**
 * 删除
 */
export function deleteIngredient(ingredientId) {
  return request({
    url: `/ingredient-sys/cart/${ingredientId}`,
    method: 'delete',
  })
}
