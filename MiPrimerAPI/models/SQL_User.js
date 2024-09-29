const { DataTypes } = require('sequelize');
const sequelize = require('./SQL_config');  // Importa la instancia de Sequelize
const Role = require('./SQL_Role');
const bcrypt = require('bcrypt');

// Definir el modelo de Usuario
const User = sequelize.define('User', {
  id: {
    type: DataTypes.INTEGER,
    autoIncrement: true,
    primaryKey: true
  },
  name: {
    type: DataTypes.STRING,
    allowNull: false,
    validate: {
      notEmpty: {
        msg: 'El nombre no puede estar vacío',
      },
      len: {
        args: [2, 255],
        msg: 'El nombre debe tener al menos 2 caracteres',
      },
    }
  },
  apellido: {
    type: DataTypes.STRING,
    allowNull: false,
    validate: {
      notEmpty: {
        msg: 'El apellido no puede estar vacío',
      },
      len: {
        args: [2, 255],
        msg: 'El apellido debe tener al menos 2 caracteres',
      },
    }
  },
  email: {
    type: DataTypes.STRING,
    allowNull: false,
    unique: {
      msg: 'El correo electrónico ya está en uso',
    },
    validate: {
      isEmail: {
        msg: 'Debe ser un correo electrónico válido',
      },
    }
  },
  password: {
    type: DataTypes.STRING,
    allowNull: false,
    validate: {
      notEmpty: {
        msg: 'La contraseña no puede estar vacía',
      },
      len: {
        args: [8, 255],
        msg: 'La contraseña debe tener al menos 8 caracteres',
      },
    }
  }

}, {
  tableName: 'users',  // Nombre de la tabla en MySQL
  timestamps: true,

  // Hooks para cifrar la contraseña antes de guardar el usuario
  hooks: {
    beforeCreate: async (user) => {
      if (user.password) {
        const salt = await bcrypt.genSalt(10);
        user.password = await bcrypt.hash(user.password, salt);
      }
    },
    beforeUpdate: async (user) => {
      if (user.password && user.changed('password')) {
        const salt = await bcrypt.genSalt(10);
        user.password = await bcrypt.hash(user.password, salt);
      }
    }
  }
});

// Definir la relación de muchos a muchos con la tabla intermedia UserRole
//User.belongsToMany(Role, { through: 'UserRole' });

// Función para comparar la contraseña ingresada con la contraseña almacenada
User.prototype.validPassword = async function (password) {
  if(!pass){
    return Promise.reject( new Error('Password Requerido.') );
  }
  //HASH MD5 "miPasword123" =>  "d422s8e2v3d5d8"
  const isMatch = await bcrypt.compare(pass,this.password)

  if (!isMatch) {
    return Promise.reject( new Error('Contraseña incorrecta.'));
  }

  return {isOk: isMatch , isLocked: !this.isActive};
};
  

module.exports = User;
