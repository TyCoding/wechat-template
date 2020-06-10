<template>
	<view class="container">
		<form>
			<tui-list-cell :hover="false">
				<view class="tui-line-cell">
					<view class="tui-title">用户名</view>
					<input v-model="user.username" :adjust-position="false" placeholder-class="tui-phcolor" class="tui-input"
					 placeholder="请输入用户名" maxlength="50" type="text" />
					<view class="tui-icon-close" v-show="user.username" @tap="clearInput('username')">
						<tui-icon name="close-fill" :size="16" color="#bfbfbf"></tui-icon>
					</view>
				</view>
			</tui-list-cell>
			<tui-list-cell :hover="false">
				<view class="tui-line-cell">
					<view class="tui-title">密码</view>
					<input v-model="user.password" :adjust-position="false" placeholder-class="tui-phcolor" class="tui-input"
					 placeholder="请输入密码" maxlength="50" type="password" />
					<view class="tui-icon-close" v-show="user.password" @tap="clearInput('password')">
						<tui-icon name="close-fill" :size="16" color="#bfbfbf"></tui-icon>
					</view>
				</view>
			</tui-list-cell>
			<view class="tui-btn-box">
				<button @click="handleUpdate" class="tui-button-primary" hover-class="tui-button-hover">修改信息</button>
			</view>
		</form>
	</view>
</template>

<script>
	import {
		mapGetters
	} from 'vuex';
	import {
		update,
		findByName
	} from '@/api/user.js'
	const util = require('@/utils/util.js');
	const form = require('@/components/common/tui-validation/tui-validation.js');

	export default {
		computed: {
			...mapGetters([
				'name',
			])
		},
		data() {
			return {
				user: {}
			};
		},
		onLoad() {
			findByName(this.name).then(res => {
				this.user = res.data
				this.user.password = ''
			})
		},
		methods: {
			clearInput(v) {
				if (v == 'username') {
					this.user.username = ''
				} else if (v == 'password') {
					this.user.password = ''
				}
			},
			handleUpdate() {
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
				let checkRes = form.validation(this.user, rules);
				if (checkRes) {
					uni.showToast({
						icon: 'none',
						title: checkRes
					})
					return;
				}
				update(this.user).then(res => {
					let _this = this
					uni.showModal({
						showCancel: false,
						title: '提示',
						content: '账户已注销，请重新登录',
						confirmText: '重新登录',
						success: function(res) {
							if (res.confirm) {
								// 注销账户，清空Token
								_this.$store.dispatch('user/logout').then(res => {
									// 路由到登录页面
									_this.$Router.replaceAll({
										name: 'login'
									})
								})
							}
						}
					})
				})

			}
		}
	};
</script>

<style>
	.container {
		padding: 20rpx 0 50rpx 0;
	}

	.tui-line-cell {
		width: 100%;
		box-sizing: border-box;
		display: flex;
		align-items: center;
	}

	.tui-title {
		line-height: 32rpx;
		min-width: 120rpx;
		flex-shrink: 0;
	}

	.tui-input {
		font-size: 32rpx;
		color: #333;
		padding-left: 20rpx;
		flex: 1;
		overflow: visible;
	}

	.radio-group {
		margin-left: auto;
		transform: scale(0.8);
		transform-origin: 100% center;
		flex-shrink: 0;
	}

	.tui-radio {
		display: inline-block;
		padding-left: 28rpx;
		font-size: 36rpx;
		vertical-align: middle;
	}

	.tui-btn-box {
		padding: 40rpx 50rpx;
		box-sizing: border-box;
	}

	.tui-button-gray {
		margin-top: 30rpx;
	}

	.tui-tips {
		padding: 30rpx;
		color: #999;
		font-size: 24rpx;
	}
</style>
