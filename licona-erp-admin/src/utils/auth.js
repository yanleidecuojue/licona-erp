import Cookies from 'js-cookie'

const authorizationKey = 'pwa-authorization'

export function getAuthorization() {
  return Cookies.get(authorizationKey)
}

export function setAuthorization(authorizationValue) {
  return Cookies.set(authorizationKey, authorizationValue)
}

export function removeAuthorization() {
  return Cookies.remove(authorizationKey)
}
