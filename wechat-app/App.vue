<script>
	export default {
		onLaunch: function() {},
		onShow: function() {},
		onHide: function() {}
	};
</script>

<style>
	/*每个页面公共css uParse为优化版本*/
	@import './common/app.css';
	/* #ifndef APP-NVUE */
	@import './components/uni/uParse/src/wxParse.css';
	/* #endif */
</style>
