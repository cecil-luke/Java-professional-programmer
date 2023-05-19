import Vue from 'vue'
import router from './router'
import App from './App.vue'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

Vue.config.productionTip = false

Vue.prototype.base_url = process.env.VUE_APP_BASE_URL

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
