import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import { Message } from 'element-ui'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login')
  },
  {
    path: '/',
    name: 'home',
    component: HomeView,
    redirect: '/welcome',
    children: [
      {
        path: '/welcome',
        name: 'welcome',
        component: () => import('../views/Welcome')
      },
      {
        path: '/car/manage',
        name: 'manage',
        component: () => import('../views/car/manage')
      },
      {
        path: '/car/show',
        name: 'show',
        component: () => import('../views/car/show')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

/**
 * 全局前置路由守卫
 * to: 将要进入的路由
 * from: 从哪个路由来的
 * next: 函数, 必须确保这个函数被调用
 *   next()  进入to的路由
 *   next(false) 中断当前的导航
 *   next("/login") || next({path: '/login'}) || next({ name: 'login' })
 */
router.beforeEach((to, from, next) => {
  const token = sessionStorage.getItem('token')

  // 访问的是/login路由
  if (to.name === 'login') {
    if (token) {
      Message.warning('您已登录！')
      next('/')
    } else {
      next()
    }
  } else { // 访问的不是/login路由
    if (token) { // 如果登录了, 则允许进入当前路由
      next()
    } else {
      Message.error('您还未登录，请登录后操作！')
      setTimeout(() => {
        next('/login')
      }, 500)
    }
  }
})


// 解决重复跳转错误
let routerPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(err => err)
}

export default router
