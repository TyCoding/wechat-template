import {
	login,
	logout,
	getInfo
} from '@/api/user.js'
import {
	getToken,
	setToken,
	removeToken
} from '@/utils/auth'

const state = {
	token: getToken(),
	name: '',
	avatar: ''
}

const mutations = {
	SET_TOKEN: (state, token) => {
		state.token = token
	},
	SET_NAME: (state, name) => {
		state.name = name
	},
	SET_AVATAR: (state, avatar) => {
		state.avatar = avatar
	}
}

const actions = {
	// user login
	login({
		commit
	}, userInfo) {
		const {
			username,
			password
		} = userInfo
		return new Promise((resolve, reject) => {
			login({
				username: username,
				password: password
			}).then(res => {
				if (res.code == 200 && res.data != null) {

				}
				commit('SET_TOKEN', res.data.token)
				commit('SET_NAME', res.data.user.username)
				commit('SET_AVATAR', res.data.user.avatar)
				setToken(res.data.token)
				resolve()
			}).catch(error => {
				reject(error)
			})
		})
	},

	// get user info
	getInfo({
		commit,
		state
	}) {
		return new Promise((resolve, reject) => {
			getInfo().then(response => {
				const {
					data
				} = response

				if (!data) {
					reject('Verification failed, please Login again.')
				}

				commit('SET_NAME', data.user.username)
				commit('SET_AVATAR', data.user.avatar)
				resolve(data)
			}).catch(error => {
				reject(error)
			})
		})
	},

	// user logout
	logout({
		commit,
		state
	}) {
		return new Promise((resolve, reject) => {
			logout().then(() => {
				commit('SET_TOKEN', '')
				commit('SET_NAME', '')
				commit('SET_AVATAR', '')
				removeToken()
				resolve()
			}).catch(error => {
				reject(error)
			})
		})
	},

	// remove token
	resetToken({
		commit
	}) {
		return new Promise(resolve => {
			commit('SET_TOKEN', '')
			removeToken()
			resolve()
		})
	}
}

export default {
	namespaced: true,
	state,
	mutations,
	actions
}
