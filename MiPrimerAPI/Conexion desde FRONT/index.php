<?php
require 'API_conection.php';


$jwtToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...';
$client = new APIClient('https://pokeapi.co/api/v2/', $jwtToken);



$response = $client->get('pokemon/pikachu');
echo 'HTTP Status Code: ' . $response['http_code'] . PHP_EOL;
print_r($response['response']);


//Ejemplo de JSON Basico
/*
    {
        "name": "Pikachu",
        "type": "Electric",
        "height": 4
    }
 */
foreach ($response['response'] as $key => $value) {
    echo $key . ': ' . $value . PHP_EOL;
}

//Ejemplo de JSON con datos que son ObjectId o Arryas dentro
/*
    {
        "name": "Pikachu",
        "types": [
            {"tip": {"name": "Electric"}},
            {"tip": {"name": "Fairy"}}
        ]
    }
*/
foreach ($array['types'] as $typeEntry) {
    echo 'Type: ' . $typeEntry['tip']['name'] . PHP_EOL;
}



// 2. Hacer una solicitud POST (Ejemplo genérico, PokéAPI no acepta POST)
// Construimos los datos en un array PHP
$data = [
    'name' => 'Ash Ketchum',
    'age' => 10,
    'favorite_pokemon' => 'Pikachu'
];
// Llamamos al método POST y enviamos el array convertido a JSON
$responsePost = $client->post('create_trainer', $data, true); // true para enviar como JSON
// Mostramos el código de estado HTTP y la respuesta
echo 'HTTP Status Code: ' . $responsePost['http_code'] . PHP_EOL;
print_r($responsePost['response']);




// 3. Hacer una solicitud PUT (Ejemplo genérico)
// Construimos los datos para actualizar la altura y el peso de Pikachu
$putData = [
    'height' => 50,    // Altura actualizada en decímetros
    'weight' => 100    // Peso actualizado en hectogramos
];
// Realizamos la solicitud PUT a la PokéAPI para actualizar los datos de Pikachu
$responsePut = $client->put('pokemon/25', $putData, true); // true para enviar como JSON
// Mostramos la respuesta de la API
echo 'HTTP Status Code: ' . $responsePut['http_code'] . PHP_EOL;
print_r($responsePut['response']);




// 4. Hacer una solicitud DELETE (Ejemplo genérico)
// Realizamos la solicitud DELETE para eliminar a Pikachu (ID 25)
$responseDelete = $client->delete('pokemon/25');
// Mostramos la respuesta de la APIç
echo 'HTTP Status Code: ' . $responseDelete['http_code'] . PHP_EOL;
print_r($responseDelete['response']);


?>
