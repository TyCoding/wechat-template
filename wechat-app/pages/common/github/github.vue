<template>
	<view>
		<web-view :webview-styles="webviewStyles" src="https://github.com/TyCoding/wechat-template"></web-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				webviewStyles: {
					progress: {
						color: '#FF3333'
					}
				}
			}
		}
	}
</script>

<style>

</style>
