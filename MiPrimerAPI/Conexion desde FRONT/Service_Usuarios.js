import api from './api'

const userService = {}

userService.getRoot = () => api.get('/')
userService.getUserId = (id) => api.get(`/user/${id}`)
userService.addUser = (json) => api.post('/user/register',json)
userService.editUsuario = (id ,json) => api.put(`/user/${id}`,json)


export default userService