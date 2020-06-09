import Vue from 'vue'
import App from './App'
import store from './store'
import router from './router'
import '@/permission.js'

Vue.config.productionTip = false

Vue.prototype.$eventHub = Vue.prototype.$eventHub || new Vue()
Vue.prototype.$store = store
App.mpType = 'app'

const app = new Vue({
	store,
	router,
	...App
})
app.$mount()