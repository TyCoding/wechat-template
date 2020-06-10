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
		<view class="tui-page-title">登录</view>
		<view class="tui-form">
			<view class="tui-view-input">
				<tui-list-cell :hover="false" :lineLeft="false" backgroundColor="transparent">
					<view class="tui-cell-input">
						<tui-icon name="people" color="#6d7a87" :size="20"></tui-icon>
						<input :adjust-position="false" v-model="username" placeholder="请输入用户名" placeholder-class="tui-phcolor" type="text" />
						<view class="tui-icon-close" v-show="username" @tap="clearInput('username')">
							<tui-icon name="close-fill" :size="16" color="#bfbfbf"></tui-icon>
						</view>
					</view>
				</tui-list-cell>
				<tui-list-cell :hover="false" :lineLeft="false" backgroundColor="transparent">
					<view class="tui-cell-input">
						<tui-icon name="pwd" color="#6d7a87" :size="20"></tui-icon>
						<input :adjust-position="false" v-model="password" placeholder="请输入密码" :password="true" placeholder-class="tui-phcolor"
						 type="text" />
						<view class="tui-icon-close" v-show="password" @tap="clearInput('password')">
							<tui-icon name="close-fill" :size="16" color="#bfbfbf"></tui-icon>
						</view>
					</view>
				</tui-list-cell>
			</view>
			<view class="tui-btn-box">
				<tui-button @click="handleLogin" :disabledGray="true" :shadow="true" shape="circle">登录</tui-button>
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
			clearInput(v) {
				if (v == 'username') {
					this.username = ''
				} else if (v == 'password') {
					this.password = ''
				}
			},
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

<style lang="scss">
	.container {
		.tui-status-bar {
			width: 100%;
			height: var(--status-bar-height);
		}

		.tui-header {
			width: 100%;
			padding: 40rpx;
			display: flex;
			align-items: center;
			justify-content: space-between;
			box-sizing: border-box;
		}

		.tui-page-title {
			width: 100%;
			font-size: 48rpx;
			font-weight: bold;
			color: $uni-text-color;
			line-height: 42rpx;
			padding: 40rpx;
			box-sizing: border-box;
		}

		.tui-form {
			padding-top: 50rpx;

			.tui-view-input {
				width: 100%;
				box-sizing: border-box;
				padding: 0 40rpx;

				.tui-cell-input {
					width: 100%;
					display: flex;
					align-items: center;
					padding-top: 48rpx;
					padding-bottom: $uni-spacing-col-base;

					input {
						flex: 1;
						padding-left: $uni-spacing-row-base;
					}

					.tui-icon-close {
						margin-left: auto;
					}
				}
			}

			.tui-cell-text {
				width: 100%;
				padding: $uni-spacing-col-lg $uni-spacing-row-lg;
				box-sizing: border-box;
				font-size: $uni-font-size-sm;
				color: $uni-text-color-grey;
				display: flex;
				align-items: center;
				justify-content: space-between;

				.tui-color-primary {
					color: $uni-color-primary;
				}
			}

			.tui-btn-box {
				width: 100%;
				padding: 0 $uni-spacing-row-lg;
				box-sizing: border-box;
				margin-top: 80rpx;
			}
		}

		.tui-login-way {
			width: 100%;
			font-size: 26rpx;
			color: $uni-color-primary;
			display: flex;
			justify-content: center;
			position: fixed;
			left: 0;
			bottom: 80rpx;

			view {
				padding: 12rpx 0;
			}
		}

		.tui-auth-login {
			width: 100%;
			display: flex;
			align-items: center;
			justify-content: center;
			padding-bottom: 80rpx;
			padding-top: 20rpx;

			.tui-icon-platform {
				width: 90rpx;
				height: 90rpx;
				display: flex;
				align-items: center;
				justify-content: center;
				position: relative;
				margin-left: 40rpx;

				&::after {
					content: '';
					position: absolute;
					width: 200%;
					height: 200%;
					transform-origin: 0 0;
					transform: scale(0.5, 0.5) translateZ(0);
					box-sizing: border-box;
					left: 0;
					top: 0;
					border-radius: 180rpx;
					border: 1rpx solid $uni-text-color-placeholder;
				}
			}

			.tui-login-logo {
				width: 60rpx;
				height: 60rpx;
			}
		}
	}
</style>
