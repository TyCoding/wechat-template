<template>
	<view class="container">
		<view class="top-container">
			<image class="bg-img" src="/static/images/my/mine_bg_3x.png"></image>
			<view v-show="name" @tap="logout" class="logout" hover-class="opcity" :hover-stay-time="150">
				<image class="logout-img" src="/static/images/my/icon_out_3x.png"></image>
				<text class="logout-txt" >退出</text>
			</view>
			<view v-show="!name" class="user-wrapper">
				<navigator url="/pages/common/login/login" hover-class="opcity" :hover-stay-time="150" class="user">
					<image class="avatar-img" src="/static/images/my/mine_def_touxiang_3x.png"></image>
					<text class="user-info-mobile">请登录</text>
				</navigator>
			</view>
			<view v-show="name" class="user">
				<image class="avatar-img" src="http://cdn.tycoding.cn/20200609171249.png"></image>
				<view class="user-info-mobile">
					<text>{{ name }}</text>
					<view class="edit-img" hover-class="opcity" :hover-stay-time="150">
						<image src="/static/images/my/mine_icon_bianji_3x.png"></image>
					</view>
				</view>
			</view>
		</view>

		<view class="middle-container">
			<view data-index="1" class="middle-item" hover-class="opcity" :hover-stay-time="150">
				<image class="ticket-img" src="/static/images/index/template.png"></image>
				<text class="middle-tag">About</text>
			</view>
		</view>

		<view class="bottom-container">
			<view class="ul-item">
				<view data-index="3" class="item" hover-class="opcity" :hover-stay-time="150">
					<image class="item-img" src="/static/images/my/log.png"></image>
					<text class="item-name">日志</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		mapGetters
	} from 'vuex';
	export default {
		computed: {
			...mapGetters([
				'token',
				'name',
				'avatar'
			])
		},
		data() {
			return {};
		},
		methods: {
			logout() {
				let _this = this
				uni.showModal({
				    title: '提示',
				    content: '确定退出登录？',
				    success: function (res) {
				        if (res.confirm) {
							// 注销账户，清空Token
							_this.$store.dispatch('user/logout').then(res => {
								// 路由到登录页面
								_this.$Router.replaceAll({
									name: 'login'
								})
							})
				        } else if (res.cancel) {
							uni.showToast({
								icon: 'none',
								title: '已取消'
							})
				        }
				    }
				});
			}
		}
	};
</script>

<style>
	.container {
		position: relative;
		padding-bottom: 100rpx;
	}

	.top-container {
		height: 440rpx;
		position: relative;
		display: flex;
		flex-direction: column;
	}

	.bg-img {
		position: absolute;
		width: 100%;
		height: 440rpx;
	}

	.logout {
		width: 110rpx;
		height: 36rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		margin: 42rpx 0 24rpx 32rpx;
		position: relative;
		z-index: 2;
	}

	.logout-img {
		width: 36rpx;
		height: 36rpx;
		margin-right: 11rpx;
	}

	.logout-txt {
		font-size: 28rpx;
		color: #fefefe;
		line-height: 28rpx;
	}

	.user-wrapper {
		display: flex;
		justify-content: center;
		position: relative;
		z-index: 2;
	}

	.user {
		display: flex;
		flex-direction: column;
		justify-content: center;
		position: relative;
		z-index: 2;
	}

	.avatar-img {
		width: 160rpx;
		height: 160rpx;
		border-radius: 50%;
		align-self: center;
	}

	.user-info {
		display: flex;
		flex-direction: row;
		margin-top: 30rpx;
		align-items: center;
	}

	.user-info-mobile {
		margin-top: 30rpx;
		position: relative;
		font-size: 28rpx;
		color: #fefefe;
		line-height: 28rpx;
		align-self: center;
		padding: 0 50rpx;
	}

	.edit-img {
		position: absolute;
		width: 42rpx;
		height: 42rpx;
		right: 0;
		bottom: -4rpx;
	}

	.edit-img>image {
		width: 42rpx;
		height: 42rpx;
		padding-left: 25rpx;
	}

	.middle-container {
		height: 138rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		border-radius: 10rpx;
		background-color: #ffffff;
		margin: -30rpx 30rpx 26rpx 30rpx;
		box-shadow: 0 15rpx 10rpx -15rpx #efefef;
		position: relative;
		z-index: 2;
	}

	.middle-item {
		height: 100%;
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	.ticket-img {
		width: 80rpx;
		height: 80rpx;
		margin-left: 65rpx;
	}

	.middle-tag {
		font-size: 28rpx;
		color: #333333;
		line-height: 28rpx;
		font-weight: bold;
		padding-left: 22rpx;
	}

	.car-img {
		width: 80rpx;
		height: 80rpx;
		margin-left: 97rpx;
	}

	.bottom-container {
		height: 334rpx;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		padding: 40rpx 74rpx 40rpx 95rpx;
		margin: 0 30rpx;
		background-color: #ffffff;
		border-radius: 10rpx;
		box-sizing: border-box;
		box-shadow: 0 0 10rpx #efefef;
	}

	.ul-item {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}

	.item {
		display: flex;
		flex-direction: column;
		align-items: center;
		position: relative;
	}

	.item-img {
		width: 64rpx;
		height: 64rpx;
	}

	.item-name {
		padding-top: 13rpx;
		font-size: 24rpx;
		color: #666666;
		min-width: 80rpx;
		text-align: center;
	}

	.btn-feedback {
		background: transparent !important;
		position: absolute;
		height: 100%;
		width: 100%;
		left: 0;
		top: 0;
		border: 0;
	}

	.btn-feedback::after {
		border: 0;
	}
</style>
