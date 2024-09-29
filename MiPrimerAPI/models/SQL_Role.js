const { DataTypes } = require('sequelize');
const sequelize = require('./SQL_config');  // Importa la instancia de Sequelize
//const User = require('./SQL_User');  // Importa el modelo User

// Definir el modelo de Rol
const Role = sequelize.define('Role', {
  id: {
    type: DataTypes.INTEGER,
    autoIncrement: true,
    primaryKey: true
  },
  name: {
    type: DataTypes.STRING,
    allowNull: false,
    unique: true
  }
}, {
  tableName: 'roles',  // Nombre de la tabla en MySQL
  timestamps: false
});

// Definir la relación de muchos a muchos con la tabla intermedia UserRole
//Role.belongsToMany(User, { through: 'UserRole' });

module.exports = Role;
