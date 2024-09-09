const mongoose = require('mongoose');
const validate = require('mongoose-validator');
const bcrypt = require('bcrypt');

const Schema = mongoose.Schema;
const { ObjectId } = Schema.Types;


function calcularEdad(fecha) {
  const hoy = new Date();
  const nacimiento = new Date(fecha);
  const m = nacimiento.getMonth()-hoy.getMonth();
  let edad =  hoy.getFullYear() - nacimiento.getFullYear();

  if (m < 0 || (m === 0 && hoy.getDate() < nacimiento.getDate())) {
    edad--;
  }

  return edad;
}

//Validadores
function telValidator(dato) {
  return validate.isMobilePhone(dato, 'any', { strictMode: false });
}

function soloLetrasValidator(dato) { /^[a-zA-Z]+$/.test(dato)}
function soloLetrasConEspaciosValidator (dato){ /^[a-zA-Z\s]+$/.test(dato)}
function usernameValidator  (dato) { /^[a-zA-Z0-9_]+$/.test(dato)}
const emailValidator = validate({ validator: 'isEmail' });

 
const lowercaseValidator = validate({ 
  validator: function (v) { return /[a-z]/.test(v); }, message: 'La contraseña debe contener al menos una letra minúscula.' });

const uppercaseValidator = validate({
  validator: function (v) { return /[A-Z]/.test(v); },
  message: 'La contraseña debe contener al menos una letra mayúscula.'
});

const specialCharValidator = validate({
  validator: function (v) { return /[_\-.\&$#]/.test(v); },
  message: 'La contraseña debe contener al menos un carácter especial (_ - . & $ #).'
});

const numerosValidator = validate({
  validator: function (v) {return /\d/.test(v);},
  message: 'La contraseña debe contener al menos un número.'
});

const longitudPasswordValidator = validate({
  validator: function (v) {return v.length >= 8 && v.length <= 16; },
  message: 'La contraseña debe tener entre 8 y 16 caracteres.'
});

//Esytructura de la table en la DB
const userSchema = new Schema({
  nombre: {
    type: String,
    require: true,
    trim: true,
    lowercase: true,
    validate: soloLetrasValidator
  },
  apellido: {
    type: String,
    require: true,
    trim: true,
    lowercase: true,
    validate: soloLetrasConEspaciosValidator
  },
  email: {
    type: String,
    require: true,
    trim: true,
    lowercase: true,
    unique: true,
    validate: emailValidator
  },
  username: {
    type: String,
    required: true,
    unique: true,
    trim: true,
    validate: usernameValidator,
  },
  password: {
    type: String,
    require: true,
    select: false,
    validate: [
      lowercaseValidator ,
      uppercaseValidator ,
      numerosValidator ,
      specialCharValidator ,
      //longitudPasswordValidator 
    ]
  },
  nacimiento: {
    type: Date,
    require: true,
  },
  edad: {
    type: Number,
    default: function () {
      return calcularEdad(this.nacimiento)
    }
  },
  tel: {
    type: String,
    trim: true,
    validator: telValidator
  },
  isActive: {
    type: Boolean,
    default: true,
  },
  //timestamps: true

});//fin userSchema

userSchema.pre('save', function (next) {
  if (this.isModified('nacimiento')) {
    this.edad = calcularEdad(this.nacimiento);
  }
  next();
});

userSchema.methods.checkPassword = async function (pass)
{
  if(!pass){
    return Promise.reject( new Error('Password Requerido.') );
  }
  //HASH MD5 "miPasword123" =>  "d422s8e2v3d5d8"
  const isMatch = bcrypt.compare(pass,this.password)

  return {isOk: isMatch , isLocked: !this.isActive};
};

// Crea un modelo llamado 'User' basado en el esquema userSchema
// Un modelo es una clase con la que construimos documentos (instancias de datos) que se guardarán en la base de datos
const User = mongoose.model('User', userSchema)

// Exporta el modelo 'User' para que pueda ser usado en otras partes de la aplicación
module.exports = User