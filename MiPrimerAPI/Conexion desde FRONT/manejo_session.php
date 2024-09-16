<?php
require 'API_conection.php';

$client = new APIClient('localhost:2000/MiAPI_32');

//Las sesiones en PHP permiten almacenar datos del usuario en el servidor entre diferentes solicitudes HTTP. Utilizan un identificador único
// que se guarda en una cookie del navegador, lo que permite mantener el estado del usuario (como autenticación y preferencias) durante su visita.
// Son útiles para gestionar información persistente y segura a lo largo de la interacción del usuario con el sitio web.

// Iniciar la sesión
session_start();

// Configurar duración de la sesión
ini_set('session.gc_maxlifetime', 3600); // 1 hora
// Configurar duración de la cookie de sesión
ini_set('session.cookie_lifetime', 3600); // 1 hora
// Configurar cookie HttpOnly
ini_set('session.cookie_httponly', 1); // No accesible desde JavaScript
// Usar solo cookies para sesiones
ini_set('session.use_only_cookies', 1); // Solo cookies para sesiones





$data = [
    'username' => 'Profesor',
    'password' => 'sderfgyhu'
];
// Llamamos al método POST y enviamos el array convertido a JSON
$responsePost = $client->post('user/login', $data, true); // true para enviar como JSON
// Mostramos el código de estado HTTP y la respuesta

if( $responsePost['http_code'] == 200  && isset(  $responsePost['response']['jwt'] )  ){
    // Regenera el ID de sesión y elimina el anterior
    session_regenerate_id(true); 

    $_SESSION['jwt'] = $responsePost['response']['jwt'];
}





// Destruir la sesión
unset($_SESSION['jwt']);
$_SESSION = array(); // Vaciar la sesión
session_destroy();   // Destruir la sesión en el servidor

// Eliminar la cookie de sesión
if (ini_get("session.use_cookies")) {
    $params = session_get_cookie_params();
    setcookie(session_name(), '', time() - 42000,
        $params["path"], $params["domain"],
        $params["secure"], $params["httponly"]
    );
}

?>