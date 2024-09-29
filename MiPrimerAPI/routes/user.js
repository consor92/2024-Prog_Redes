// Importa el módulo Express para manejar rutas HTTP
const express = require('express')
const User = require('../models/SQL_User'); // Importa el modelo User
const Role = require('../models/SQL_Role'); // Importa el modelo Role

// Crea una nueva instancia de Router para definir rutas relacionadas con usuarios
const router = express.Router()


router.get('/' , getUsuarios )
router.get('/:id' , getUsuarioId )
router.post('/' , postUsuario )
router.put('/:id' , putUsuario )
router.delete('/:id' , deleteUsuario )
router.post('/:userId/roles/:roleId' , asignarRole )


async function getUsuarios(req , res)
{   
    try {
        const users = await User.findAll({
        //attributes: [] // los campos especificos que queremos de la table USER
          include: [{
            model: Role,  // Incluye el modelo Role
            //attributes: [] // los campos especificos que queremos de la table ROLE
            through: {    // A través de la tabla intermedia UserRole
              attributes: [] // los campos especificos que queremos de la table INTERMEDIA
            }
          }, {  // para varias relaciones con FK
            model: Changuito, // Incluir el modelo Changuito
            attributes: ['numero_canguito'], // Solo el campo deseado de Changuito
          }]
        });
    
        return res.status(200).json(users);
      } catch (error) {
        console.error(error);
        return res.status(500).json({ message: 'Error al obtener usuarios con sus roles.' });
      }
}

async function getUsuarioId(req , res)
{
    const { id } = req.params;

    try {
      const user = await User.findByPk(id, {
        include: [{
          model: Role,
          through: {
            attributes: []
          }
        }]
      });
  
      if (!user) {
        return res.status(404).json({ message: 'Usuario no encontrado.' });
      }
  
      return res.status(200).json(user);
    } catch (error) {
      console.error(error);
      return res.status(500).json({ message: 'Error al obtener el usuario con sus roles.' });
    }
}

async function postUsuario(req , res)
{
    const { name, apellido, email, password, roles } = req.body;

    try {
      // Crear el nuevo usuario
      const newUser = await User.create({
        name,
        apellido,
        email,
        password,
      });
  
      // Si se proporcionan roles, verificar y asignarlos
      if (roles && roles.length > 0) {
        // Busca los roles existentes en la base de datos
        const existingRoles = await Role.findAll({
          where: {
            name: roles.map(role => role.name)  // Obtiene los nombres de los roles enviados
          }
        });
  
        // Verificar que todos los roles existan
        if (existingRoles.length !== roles.length) {
          return res.status(400).json({ message: 'Uno o más roles no existen.' });
        }
  
        // Asigna los roles al nuevo usuario
        await newUser.addRoles(existingRoles); // Usa el método generado automáticamente
      }
  
      return res.status(201).json(newUser);
    } catch (error) {
      console.error(error);
      return res.status(500).json({ message: 'Error al crear el usuario.' });
    }
}
async function putUsuario(req , res)
{
    try {
        const user = await User.findByPk(req.params.id);
        if (user) {
          await user.update(req.body);
          res.json(user);
        } else {
          res.status(404).json({ message: 'Usuario no encontrado' });
        }
      } catch (error) {
        res.status(400).json({ message: error.message });
      }
}
async function deleteUsuario(req , res)
{
    try {
        const user = await User.findByPk(req.params.id);
        if (user) {
          await user.destroy();
          res.json({ message: 'Usuario eliminado' });
        } else {
          res.status(404).json({ message: 'Usuario no encontrado' });
        }
      } catch (error) {
        res.status(500).json({ message: error.message });
      }
}
async function asignarRole(req , res)
{
    const { userId, roleId } = req.params;

    try {
      // Buscar el usuario por su ID
      const user = await User.findByPk(userId);
      // Buscar el rol por su ID
      const role = await Role.findByPk(roleId);
  
      // Verificar si el usuario y el rol existen
      if (!user || !role) {
        return res.status(404).json({ message: 'Usuario o rol no encontrado.' });
      }
  
      // Asignar el rol al usuario
      await user.addRole(role); // Esto añade el rol al usuario en la tabla intermedia UserRole
      return res.status(200).json({ message: 'Rol asignado al usuario exitosamente.' });
    } catch (error) {
      console.error(error);
      return res.status(500).json({ message: 'Error al asignar rol al usuario.' });
    }
}



module.exports = router;