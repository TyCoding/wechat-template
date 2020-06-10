<template>
	<view class="container">
		<view class="tui-content-box">
			<view class="tui-title">
				项目介绍
			</view>
			<tui-card>
				<template v-slot:body>
					<view class="body-base">
						<view>本项目后端由SpringBoot、Shiro构建，小程序端依赖Uni-app。</view>
						<view>项目开源地址：https://github.com/TyCoding/wechat-template</view>
					</view>
				</template>
			</tui-card>
			<view class="tui-title">
				你能学到什么?
			</view>
			<tui-card>
				<template v-slot:body>
					<view class="body-base">
						<view>1. Uni-app与Java Web项目结合的最佳实践</view>
						<view class="body-text-pd">2. SpringBoot与Shiro的集成，以及身份校验、Token交互的实现</view>
						<view class="body-text-pd">3. 如何优雅的封装一套Uni-app(微信小程序)脚手架，包括：request封装、vuex存储、router页面路由、与Java后端数据交互</view>
						<view class="body-text-pd">4. 如何优雅的封装一套SpringBoot&Shiro权限项目脚手架，如何处理前后端分离Token交互</view>
						<view class="body-text-pd">5. 如何处理小程序端Request请求，后续将从Login请求展开逐步分析前后端交互流程</view>
					</view>
				</template>
			</tui-card>
			<view class="tui-title">
				联系作者
			</view>
			<tui-card>
				<template v-slot:body>
					<view class="body-base">
						<view>Author: TyCoding</view>
						<view class="body-text-pd">Blog: https://tycoding.cn</view>
						<view class="body-text-pd">Github: https://github.com/TyCoding</view>
						<view class="body-text-pd">QQ Group: 671017003</view>
					</view>
				</template>
			</tui-card>
		</view>
	</view>
</template>

<script>
</script>

<style>
	page {
		background: #EDEDED;
	}

	.container {
		position: relative;
		padding-bottom: 100rpx;
	}

	.tui-title {
		width: 100%;
		padding: 70rpx 30rpx 30rpx 30rpx;
		box-sizing: border-box;
		font-size: 30rpx;
		line-height: 30rpx;
		color: #666;
	}

	.body-base {
		padding: 20rpx 30rpx;
	}

	.body-text-pd {
		padding: 10px 0;
	}
</style>
