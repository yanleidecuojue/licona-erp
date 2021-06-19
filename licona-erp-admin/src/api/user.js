import request from '@/utils/request'
import qs from 'qs'

export function login(data) {
  return request({
    url: '/licona-erp/auth/oauth/token',
    method: 'post',
    data: qs.stringify(data)
  })
}

export function getInfo(Authorization) {
  return request({
    url: '/licona-erp/user/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/licona-erp/user/logout',
    method: 'post'
  })
}

export function getUsers() {
  return request({
    url: '/licona-erp/acl/user/getUsers',
    method: 'get'
  })
}

export function register(user) {
  return request({
    url: '/licona-erp/user/register',
    method: 'post',
    data: qs.stringify(user)
  })
}

export function deleteUser(username) {
  return request({
    url: '/licona-erp/acl/user/deleteUser',
    method: 'post',
    data: qs.stringify({
      "username": username
    })
  })
}

export function updateUser(updateUser) {
  return request({
    url: 'licona-erp/acl/user/updateUser',
    method: 'post',
    data: qs.stringify(updateUser)
  })
}

