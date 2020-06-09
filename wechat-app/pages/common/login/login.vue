<!-- 
登录页面：
	强制登录，具体参考QQ小程序的设计，当然这里是通过填写登录表单从服务器端获取用户数据
	并不是通过微信授权方式获取微信用户信息，开发者可以自定义Login页面实现逻辑
	
	强制登录实现逻辑：
		1. 路由守卫中首先判断没有token跳转到登录页面
		2. 小程序TabBar第一页面中在onLoad()函数中再次判断没有Token就跳转到登录页面
		小程序中无法拦截原生Navbar的路由，除非自定义Navbar，我看谁闲的没事来回跳
-->
<template>
	<view class="container">
		<view class="tui-bg-box">
			<image src="/static/images/login/bg_login.png" class="tui-bg-img"></image>
			<image src="/static/images/my/mine_def_touxiang_3x.png" class="tui-photo"></image>
			<view class="tui-login-name">Login</view>
		</view>
		<view :report-submit="true">
			<view class="tui-login-from">
				<view class="tui-line-cell">
					<tui-icon name="people" :size="20" color="#6d7a87"></tui-icon>
					<input placeholder-class="tui-phcolor" class="tui-input" placeholder="请输入用户名" maxlength="11" v-model="username"
					 type="number" />
				</view>
				<view class="tui-line-cell tui-top28">
					<tui-icon name="pwd" :size="20" color="#6d7a87"></tui-icon>
					<input placeholder-class="tui-phcolor" class="tui-input" placeholder="请输入密码" maxlength="6" type="password" v-model="password" />
				</view>
				<button @click="handleLogin" class="tui-button-primary tui-btn-submit" hover-class="tui-button-hover" form-type="submit">登录</button>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		login
	} from '@/api/user.js'
	const util = require('@/utils/util.js');
	const form = require('@/components/common/tui-validation/tui-validation.js');

	export default {
		data() {
			return {
				username: 'tycoding',
				password: '123456',
			};
		},
		onReady() {
			uni.showToast({
				title: '请先登录，再继续其他操作~',
				icon: 'none'
			})
		},
		methods: {
			handleLogin() {
				let rules = [{
						name: 'username',
						rule: ['required'],
						msg: ['请输入用户名']
					},
					{
						name: 'password',
						rule: ['required'],
						msg: ['请输入密码']
					}
				];

				//校验
				let data = {
					username: this.username,
					password: this.password
				};
				let checkRes = form.validation(data, rules);
				if (checkRes) {
					uni.showToast({
						icon: 'none',
						title: checkRes
					})
					return;
				}

				// 调用store中的login action，发送登录请求并设置Token
				this.$store.dispatch('user/login', data).then(() => {
					// 路由到首页
					this.$Router.replaceAll({
						name: 'my'
					})
				}).catch(() => {
					console.log('login error...')
				})
			}
		}
	};
</script>

<style>
	page {
		background-color: #fff;
	}

	.tui-bg-box {
		width: 100%;
		box-sizing: border-box;
		position: relative;
		padding-top: 44px;
	}

	.tui-photo {
		height: 138rpx;
		width: 138rpx;
		display: block;
		margin: 10rpx auto 0 auto;
		border-radius: 50%;
		position: relative;
		z-index: 2;
	}

	.tui-login-name {
		width: 128rpx;
		height: 40rpx;
		font-size: 30rpx;
		color: #fff;
		margin: 36rpx auto 0 auto;
		text-align: center;
		position: relative;
		z-index: 2;
	}

	.tui-bg-img {
		width: 100%;
		height: 346rpx;
		display: block;
		position: absolute;
		top: 0;
	}

	.tui-login-from {
		width: 100%;
		padding: 128rpx 104rpx 0 104rpx;
		box-sizing: border-box;
	}

	.tui-input {
		font-size: 32rpx;
		flex: 1;
		display: inline-block;
		padding-left: 32rpx;
		box-sizing: border-box;
		overflow: hidden;
	}

	.tui-line-cell {
		padding: 27rpx 0;
		display: -webkit-flex;
		display: flex;
		-webkiit-align-items: center;
		align-items: center;
		position: relative;
		box-sizing: border-box;
		overflow: hidden;
	}

	.tui-line-cell::after {
		content: '';
		position: absolute;
		border-bottom: 1rpx solid #e0e0e0;
		-webkit-transform: scaleY(0.5);
		transform: scaleY(0.5);
		bottom: 0;
		right: 0;
		left: 0;
	}

	.tui-top28 {
		margin-top: 28rpx;
	}

	.tui-btn-class {
		width: 196rpx !important;
		height: 54rpx !important;
		border-radius: 27rpx !important;
		font-size: 28rpx !important;
		padding: 0 !important;
		line-height: 54rpx !important;
	}

	.tui-btn-submit {
		margin-top: 100rpx;
	}

	.tui-protocol {
		color: #333;
		font-size: 24rpx;
		text-align: center;
		width: 100%;
		margin-top: 29rpx;
	}

	.tui-protocol-red {
		color: #f54f46;
	}
</style>
