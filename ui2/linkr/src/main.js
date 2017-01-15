// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueMaterial from 'vue-material'
import VueRouter from 'vue-router'
import Login from './components/Login'
import Hello from './components/Hello'
import Content from './components/Content'
import Register from './components/Register'
/* eslint-disable no-new */

Vue.use(VueMaterial)
Vue.use(VueRouter)

const routes = [
  { path: '/login', component: Login },
  { path: '/register', component: Register },
  { path: '/', component: Hello },
  { path: '/content', component: Content }
]

const router = new VueRouter({
  routes
})

// router.start(App, '#app')
new Vue({
  el: '#app',
  template: '<App/>',
  components: { App },
  router
})

export default router
