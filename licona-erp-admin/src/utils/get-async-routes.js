export function getAsyncRoutes(data) {
  const newArr = []
  for (const i in data) {
    const temp = new Object()
    temp.path = data[i].path
    temp.component = data[i].component

    temp.meta = {
      title: data[i].title,
      icon: data[i].icon,
      roles: JSON.stringify(data[i].roles).replace(new RegExp('\"','g'), '').split(',')
    }

    if (data[i].children instanceof Array && data[i].children.length > 0) {
      const cc = getAsyncRoutes(data[i].children)
      temp.children = cc
    }
    newArr.push(temp)
  }
  return newArr
}
