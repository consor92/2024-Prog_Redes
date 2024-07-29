// Crea un índice único en los campos 'governmentId.type' y 'governmentId.number'
userSchema.index({ 'governmentId.type': 1, 'governmentId.number': 1 }, { unique: true })

// Define un método de instancia para verificar la contraseña del usuario
userSchema.method('checkPassword', async function checkPassword(potentialPassword) {
  if (!potentialPassword) {
    return Promise.reject(new Error('Password is required'))
  }

  // Compara la contraseña proporcionada con la contraseña almacenada (hashed)
  const isMatch = await bcrypt.compare(potentialPassword, this.password)

  // Devuelve un objeto indicando si la contraseña es correcta y si la cuenta está activa
  return { isOk: isMatch, isLocked: !this.isActive }
})

// Crea un modelo llamado 'User' basado en el esquema userSchema
// Un modelo es una clase con la que construimos documentos (instancias de datos) que se guardarán en la base de datos
const User = mongoose.model('User', userSchema)

// Exporta el modelo 'User' para que pueda ser usado en otras partes de la aplicación
module.exports = User