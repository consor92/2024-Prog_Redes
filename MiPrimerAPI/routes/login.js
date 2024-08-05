// Importa el módulo Express para manejar rutas HTTP
const express = require('express')

// Crea una nueva instancia de Router para definir rutas relacionadas con usuarios
const router = express.Router()

//  localhost:2000/
router.get('/:idUsuario' , getUsuario )
//  localhost:2000/register
router.post('/register' , registrarUsuario )
//  localhost:2000/login
router.post('/login' , logIn)
//  localhost:2000/resetPassword
router.post('/resetPassword' , recuperarPass )


function getUsuario( req , res)
{
    const par = req.params.idUsuario
    
    res.status(200).json( par );//como texto
    res.status(200).json( {par} );//como json
}

//( request , response)
function registrarUsuario(req , res)
{   
    //recepcion de datos por la API
    const body = req.body

    // aca en el medio: logica de negocio de la funcion
    // acompañada de  consultas SQL/Mongo

    //se intercala IF/TRY-CATCH con posibles devoluciones de errores
    /*
        res.status(404).json( {mensaje: "No se encontro el recurso"} );
        res.status(401).json( );
        res.status(400).json( );
        res.status(500).json( );
    */

    //API responde resultado + status
    res.status(200).json( {par} );
}

function logIn(req , res)
{

}

function recuperarPass(req , res)
{

}

module.exports = router;