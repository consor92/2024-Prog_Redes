const { DataTypes } = require('sequelize');
const sequelize = require('./SQL_config');
const User = require('./SQL_User'); // Importa el modelo User

// Definir el modelo de Changuito
const Changuito = sequelize.define('Changuito', {
  id: {
    type: DataTypes.INTEGER,
    autoIncrement: true,
    primaryKey: true,
  },
  numeroChanguito: {
    type: DataTypes.INTEGER,
    allowNull: false,
    unique: true,
    validate: {
      isInt: true,
      len: {
        args: [4, 4],
        msg: 'El número de changuito debe tener 4 cifras.',
      },
    },
  },
  userId: {
    type: DataTypes.INTEGER,
    references: {
      model: User, // Relación con la tabla User
      key: 'id',
    },
    allowNull: false,
  },
}, {
  tableName: 'changuitos',  // Nombre de la tabla en MySQL
  timestamps: true,
});

// Definir la relación entre Changuito y User
Changuito.belongsTo(User, { foreignKey: 'userId' });

module.exports = Changuito;
