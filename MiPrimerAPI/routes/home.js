const express = require('express')
const { response } = require('../app')

const router = express.Router()

//httpRequest
router.get('/' , mostrarHome)
//router.post()
//router.put()
//router.delete()
//router.patch()

// (info que llega ,  info que sale)
// (  request      ,    response   )
function mostrarHome( req , res ){

    res.send("no me peguessss")

}


module.exports = router
