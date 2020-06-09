// 判断开发环境和生产环境
let BASE_URL = ''
if (process.env.NODE_ENV === 'development') {
	BASE_URL = 'http://127.0.0.1:8080' //dev
} else {
	BASE_URL = 'http://127.0.0.1:8080' //prod
}

const config = {
	BASE_URL: BASE_URL
}

export {
	config
}
