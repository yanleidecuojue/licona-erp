import request from '@/utils/request'

export function getMenus() {
  return request({
    url: '/licona-erp/acl/permission/getMenus',
    method: 'get'
  })
}