<template>
	<view class="container">
		<view class="tui-btn-box">
			<view class="tui-btn-btm">
				<tui-button type="white" shape="circle" @click="loginRequest">Login(GET) Request</tui-button>
			</view>
			<view class="tui-btn-btm">
				<tui-button type="white" shape="circle" @click="getRequest">HTTP GET Request</tui-button>
			</view>
			<view class="tui-btn-btm">
				<tui-button type="white" shape="circle" @click="postRequest">HTTP POST Request</tui-button>
			</view>
			<view class="tui-btn-btm">
				<tui-button type="white" shape="circle" @click="putRequest">HTTP PUT Request</tui-button>
			</view>
			<view class="tui-btn-btm">
				<tui-button type="white" shape="circle" @click="deleteRequest">HTTP DELETE Request</tui-button>
			</view>
			<view class="tui-btn-btm">
				<tui-button type="white" shape="circle" @click="logoutRequest">Logout(DELETE) Request</tui-button>
			</view>
		</view>
		<view class="tui-result">
			request：<br /><text class="tui-red">{{requestData}}</text>
			<br />
			<br />
			response: <br /><text class="tui-red">{{responseData}}</text>
		</view>
	</view>
</template>

<script>
	import {
		mapGetters
	} from 'vuex';
	import {
		login,
		logout,
		getInfo,
		findByName,
		save,
		update,
		del
	} from '@/api/user.js'
	export default {
		computed: {
			...mapGetters([
				'token'
			])
		},
		data() {
			return {
				requestData: '',
				responseData: '',
			};
		},
		onLoad() {
			// 强制登录
			if (!this.token) {
				this.$Router.replaceAll({name: 'login'})
			}
		},
		methods: {
			handleClean() {
				this.requestData = ''
				this.responseData = ''
			},
			loginRequest() {
				this.handleClean()
				let data = {
					username: '555',
					password: 'cyle123'
				}
				// // 直接调用login请求方法
				// login(data).then(res => {
				// 	this.responseData = JSON.stringify(res.data)
				// 	this.requestData = JSON.stringify(data)
				// })
				// 调用store中的login action，发送登录请求并设置Token
				this.$store.dispatch('user/login', data).then(() => {
					uni.showToast({
						title: '登录成功',
						icon: 'none'
					})
					this.requestData = JSON.stringify(data)
					this.responseData = JSON.stringify(this.$store.getters)
				}).catch(() => {
					console.log('login catch..')
				})
			},
			getRequest() {
				this.handleClean()
				getInfo().then(res => {
					this.responseData = JSON.stringify(res.data)
				})
			},
			postRequest() {
				this.handleClean()
				let data = {
					username: 'tycoding',
					password: '123'
				}
				save(data).then(res => {
					this.requestData = JSON.stringify(data)
					findByName(data.username).then(res => {
						this.responseData = JSON.stringify(res.data)
					})
				})
			},
			putRequest() {
				this.handleClean()
				let data = {
					id: 2447,
					username: 'tycoding-update',
				}
				update(data).then(res => {
					this.requestData = JSON.stringify(data)
					findByName(data.username).then(res => {
						this.responseData = JSON.stringify(res.data)
					})
				})
			},
			deleteRequest() {
				this.handleClean()
				let id = 2447
				del(id).then(res => {
					this.responseData = JSON.stringify(res.data)
					this.requestData = JSON.stringify({
						id: id
					})
				})
			},
			logoutRequest() {
				this.handleClean()
				// 调用store中的logout action，发送注销请求并删除Token
				this.$store.dispatch('user/logout').then(() => {
					uni.showToast({
						title: '注销成功',
						icon: 'none'
					})
					this.responseData = JSON.stringify(this.$store.getters)
				})
			}
		}
	};
</script>

<style>
	.container {
		padding: 20rpx 0 120rpx 0;
		box-sizing: border-box;
	}

	.header {
		padding: 80rpx 90rpx 60rpx 90rpx;
		box-sizing: border-box;
	}

	.title {
		font-size: 34rpx;
		color: #333;
		font-weight: 500;
	}

	.sub-title {
		font-size: 24rpx;
		color: #7a7a7a;
		padding-top: 18rpx;
	}

	.tui-btn-box {
		padding: 10rpx 40rpx;
		box-sizing: border-box;
	}

	.tui-btn-btm {
		margin-bottom: 36rpx;
	}

	.tui-result {
		width: 100%;
		padding: 30rpx 40rpx;
		box-sizing: border-box;
		font-size: 28rpx;
	}

	.tui-red {
		color: #ee0900;
	}
</style>
