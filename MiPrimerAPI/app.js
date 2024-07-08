const express = require('express')
const cors = require('cors')
const logger = require('morgan')
const cookies = require('cookie-parser')


//Creamos las rutas de destino
const homeRouter = require('./routes/home')

//creo un objeto de EXPRESS
//es el que hace la magia en el BACKEND
const app = express()

app.use(logger)
app.use(cors)
app.use(cookies)
app.use(express.json) //para que entienda un JSON
app.use(express.urlencoded({extended: false}) ) //para que entieda URLs


//manejo de RUTAS
//  URL-BASE:  localhost:3000    127.0.0.1:3000

//  127.0.0.1:3000/aviones/mostrar
app.use( '/' , homeRouter)
//app.use( '/aviones' , )
//app.use( '/aviones/mostrar' , )


//le permito a otras classes crear objetos de MI
module.exports = app