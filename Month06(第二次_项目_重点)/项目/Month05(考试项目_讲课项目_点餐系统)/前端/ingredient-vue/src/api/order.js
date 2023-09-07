import request from "@/util/request";

export function createOrder(time, cartList) {
    const jsonData = { prepareTime: time, cartList: cartList }
    return request({
        url: '/ingredient-sys/order',
        method: 'post',
        data: jsonData
    })
}