// models/initModels.js
const User = require('./SQL_User');
const Role = require('./SQL_Role');

// Definir las asociaciones
User.belongsToMany(Role, { through: 'UserRole' });
Role.belongsToMany(User, { through: 'UserRole' });
