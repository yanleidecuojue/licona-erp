import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '仪表盘', icon: 'dashboard' }
    }]
  },
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes =
[
  {
    path: '/acl',
    component: Layout,
    redirect: '/acl/menu',
    alwaysShow: true, // will always show the root menu
    name: '权限管理',
    meta: {
      title: '权限管理',
      icon: 'lock',
      roles: ['admin', 'useradmin']
    },
    children: [{
      path: '/menu',
      name: '菜单管理',
      component: () => import('@/views/acl/menu'),
      meta: {
        title: '菜单管理',
        roles: ['admin', 'useradmin']
      }
    }, {
      path: '/user',
      name: '用户管理',
      component: () => import('@/views/acl/user'),
      meta: {
        title: '用户管理',
        roles: ['admin', 'useradmin']
      }
    }, {
      path: '/role',
      name: '角色管理',
      component: () => import('@/views/acl/role'),
      meta: {
        title: '角色管理',
        roles: ['admin', 'useradmin']
      }
    }]
  }
]
const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
