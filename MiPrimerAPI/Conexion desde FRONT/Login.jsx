import userService from '../../service/user'


const json = {}
const dato = useParams()


const resp1 = (await userService.addUser( json )).status;
const resp2 = (await userService.editarPaciente( dato.id , json )).status;
const resp3 = await userService.getPaciente( dato )