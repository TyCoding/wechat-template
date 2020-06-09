import http from '@/utils/request'

export function login(data) {
	return http.request({
		url: '/login',
		method: 'get',
		params: {
			username: data.username,
			password: data.password
		}
	})
}

export function getInfo() {
	return http.request({
		url: '/user/info',
		method: 'get'
	})
}

export function logout() {
  return http.request({
    url: '/logout',
    method: 'delete'
  })
}

export function getList(query, data) {
  return http.request({
    url: `/user/list?page=${query.page}&limit=${query.limit}`,
    method: 'post',
    data
  })
}

export function findById(id) {
  return http.request({
    url: '/user/' + id,
    method: 'get'
  })
}

export function findByName(name) {
  return http.request({
    url: `/user/findByName?name=${name}`,
    method: 'get'
  })
}

export function save(data) {
  return http.request({
    url: '/user',
    method: 'post',
    data
  })
}

export function del(id) {
  return http.request({
    url: '/user/' + id,
    method: 'delete'
  })
}

export function update(data) {
  return http.request({
    url: '/user',
    method: 'put',
    data
  })
}

export function changePass(data) {
  return http.request({
    url: '/user/changePass',
    method: 'post',
    data
  })
}
