import request from '@/utils/request'
import qs from 'qs'

export function getRoutes() {
  return request({
    url: 'licona-erp/acl/permission/routes',
    method: 'get'
  })
}

export function getRoles(userId) {
  return request({
    url: 'licona-erp/acl/role/getRoles',
    method: 'get',
    params: {
      "user_id": userId
    }
  })
}

export function addRole(role) {
  return request({
    url: 'licona-erp/acl/role/addRole',
    method: 'post',
    data: qs.stringify(role)
  })
}

export function deleteRole(roleId, roleName) {
  return request({
    url: 'licona-erp/acl/role/deleteRole',
    method: 'post',
    data: qs.stringify({
      "role_id": roleId,
      "role_name": roleName
    })
  })
}

export function updateRole(id, roleName, remark, paths) {
  return request({
    url: 'licona-erp/acl/role/updateRole',
    method: 'post',
    data: qs.stringify({
      "id": id,
      "role_name": roleName,
      "remark": remark,
      "paths": paths
    })
  })
}


