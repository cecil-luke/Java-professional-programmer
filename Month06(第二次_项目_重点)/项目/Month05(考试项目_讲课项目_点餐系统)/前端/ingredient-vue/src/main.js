import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

/* 导入ElementUI */
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

/* Vue定义base_url */
const base_url = process.env.VUE_APP_URL
Vue.prototype.base_url = base_url

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
