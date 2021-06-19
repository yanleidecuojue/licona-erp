const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  Authorization: state => state.user.Authorization,
  avatar: state => state.user.avatar,
  roles: state => state.user.roles,
  permission_routes: state => state.permission.routes,
  name: state => state.user.name
}
export default getters
