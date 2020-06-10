import {
	config
} from '@/config.js'
import {
	getToken
} from '@/utils/auth.js'
import store from '@/store'
import Request from '@/components/luch-request/index.js'
const http = new Request();

// 全局配置
// http.config.baseURL = config.BASE_URL
http.config.baseURL = config.BURY_URL //测试使用服务地址

// request interceptor
http.interceptors.request.use(config => {
	uni.showLoading({
		mask: true,
		title: 'Loading...'
	})
	if (store.getters.token) {
		// if store has token
		// set request header
		config.header['Authorization'] = getToken()
	}
	
	return config;
}, error => {
	console.log(error)
	uni.hideLoading()
	tui.toast("网络不给力，请稍后再试~")
	return Promise.reject(error)
})

// response interceptor
http.interceptors.response.use(response => {
	let res = response.data
	uni.hideLoading()
	if (res.code == 401) {
		uni.clearStorageSync()
		uni.showModal({
			title: '提示',
			content: "登录信息已失效，请重新登录",
			showCancel: true,
			cancelColor: "#555",
			confirmColor: "#5677fc",
			confirmText: "登录",
			success(res) {
				if (res.confirm) {
					uni.redirectTo({
						url: '/pages/common/login/login'
					})
				} else {
					callback(false)
				}
			}
		})
		
	} else if (res.code != 200) {
		uni.showToast({
			title: res.msg,
			icon: 'none',
			duration: 2000
		})
	}
	return res
}, error => {
	console.log(error)
	uni.hideLoading()
	uni.showToast({
		title: '网络连接异常，请稍后再试~',
		icon: 'none',
		duration: 2000
	})
	return Promise.reject(error)
})

export default http
