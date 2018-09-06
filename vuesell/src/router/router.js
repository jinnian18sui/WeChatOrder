import Vue from 'vue'
import VueRouter from 'vue-router'

// 引入组件
import home from './home.vue'
import about from './about.vue'
import seller from './seller.vue'

// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    component: home
  },
  {
    path: '/about',
    component: about
  },
  {
    path: '/seller',
    component: seller
  },
  // 重定向
  {
    path: '/',
    redirect: '/home'
  }
]

let router = new VueRouter({
  routes,
  linkActiveClass: 'active'
})
export default router
