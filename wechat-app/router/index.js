import Vue from 'vue'
import Router from '@/components/uni-simple-router/index.js'
Vue.use(Router)

const router = new Router({
	routes: [{
			//注意：path必须跟pages.json中的地址对应，最前面别忘了加'/'哦
			path: '/pages/tabbar/index/index',
			name: 'index',
			meta: {
				title: '首页',
			},
		},
		{
			path: '/pages/tabbar/extend/extend',
			name: 'extend',
			meta: {
				title: '教学',
			},
		},
		{
			path: '/pages/tabbar/my/my',
			name: 'my',
			meta: {
				title: '我的',
			},
		},
		{
			path: '/pages/common/login/login',
			name: 'login',
			meta: {
				title: '登录',
			},
		},
		{
			path: '/pages/common/about/about',
			name: 'about',
			meta: {
				title: '关于',
			},
		},
	]
});
export default router
