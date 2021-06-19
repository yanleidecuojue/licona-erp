module.exports = [
  // user logout
  {
    url: '/licona-erp/user/logout',
    type: 'post',
    response: _ => {
      return {
        code: 200,
        data: 'success'
      }
    }
  }
]
