import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Welcome from '@/views/Welcome'
import Ingredient from '@/views/ingredient'
import Prepare from '@/views/prepare'
import Order from '@/views/order'

import {Message} from 'element-ui'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    redirect: '/welcome',
    children: [
      {
        path: '/welcome',
        name: 'welcome',
        component: Welcome
      },
      {
        path: '/ingredient',
        name: 'ingredient',
        component: Ingredient
      },
      {
        path: '/prepare',
        name: 'prepare',
        component: Prepare
      },
      {
        path: '/order',
        name: 'order',
        component: Order
      },
    ]
  },

  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login')
  },

  {
    path: '/register',
    name: 'register',
    component: () => import('../views/Register.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

/**
 * to: 将要进入的路由
 * from: 来自哪个路由
 * next: 函数, 控制要不要进入下一个路由
 */
router.beforeEach((to, from, next) => {
  const token = sessionStorage.getItem('token')
  const { path } = to

  if (path === '/register') {
    // 进入to表示路由
    return next()
  }

  if (path === '/login') {
    if (token) { // 登录状态
      return next('/')
    }
    // 进入to表示路由

    return next()
  } else {
    if (token) { // 登录状态下访问的不是登录路由
      return next()
    }
    Message.error('请登录后操作！')
    return next('/login')
  }
})

// 解决重复跳转错误
let routerPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(err => err)
}

export default router
