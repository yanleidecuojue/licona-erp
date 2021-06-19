import { constantRoutes } from '@/router'
import { getRoutes } from '@/api/role'
import { getAsyncRoutes } from '@/utils/get-async-routes'
import Layout from '@/layout'
const _import = require('../../router/_import_' + process.env.NODE_ENV) //获取组件的方法

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * change component
 * @param routes asyncRoutes
 */
export function enhanceAsyncRoutes(routes) {
  const accessedRouters = routes.filter(route => {
    if (route.component) {
      if (route.component === 'Layout') { //Layout组件特殊处理
        route.component = Layout
      } else {
        route.component = _import(route.component)
      }
    }
    if (route.children && route.children.length) {
      route.children = enhanceAsyncRoutes(route.children)
    }
    return true
  })

  return accessedRouters
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(roles, tmp)) {

      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  generateRoutes({ commit }, roles) {
    return new Promise((resolve, reject) => {
      let accessedRoutes
      getRoutes().then(response => {
        const { data } = response

        if (roles.includes('admin')) {
          accessedRoutes = enhanceAsyncRoutes(getAsyncRoutes(data)) || []
        } else {
          accessedRoutes = filterAsyncRoutes(enhanceAsyncRoutes(getAsyncRoutes(data)), roles)
        }

        // 404 page must be placed at the end !!!
        accessedRoutes.concat({ path: '*', redirect: '/404', hidden: true })

        commit('SET_ROUTES', accessedRoutes)
        resolve(accessedRoutes)
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
