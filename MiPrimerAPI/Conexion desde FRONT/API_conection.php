<?php
class APIClient {
    private $baseUrl;
    private $token; // Variable para almacenar el token JWT

    // Constructor para inicializar la URL base y opcionalmente el token JWT
    public function __construct($baseUrl, $token = null) {
        $this->baseUrl = rtrim($baseUrl, '/') . '/';
        $this->token = $token; // Guardamos el token JWT si está disponible
    }

    // Método para hacer una solicitud GET
    public function get($endpoint, $params = []) {
        $url = $this->baseUrl . $endpoint . '?' . http_build_query($params);
        return $this->makeRequest('GET', $url);
    }

    // Método para hacer una solicitud POST
    public function post($endpoint, $data = [], $isJson = false) {
        $url = $this->baseUrl . $endpoint;
        return $this->makeRequest('POST', $url, $data, $isJson);
    }

    // Método para hacer una solicitud PUT
    public function put($endpoint, $data = [], $isJson = false) {
        $url = $this->baseUrl . $endpoint;
        return $this->makeRequest('PUT', $url, $data, $isJson);
    }

    // Método para hacer una solicitud DELETE
    public function delete($endpoint, $params = []) {
        $url = $this->baseUrl . $endpoint . '?' . http_build_query($params);
        return $this->makeRequest('DELETE', $url);
    }

    // Método genérico para hacer las solicitudes HTTP
    private function makeRequest($method, $url, $data = [], $isJson = false) {
        $ch = curl_init($url);

        // Cabeceras
        $headers = [];
        if ($this->token) {
            $headers[] = 'Authorization: Bearer ' . $this->token; // Agregamos el token JWT
        }
        if ($isJson) {
            $headers[] = 'Content-Type: application/json'; // Agregamos la cabecera JSON si es necesario
            $data = json_encode($data); // Convertimos los datos a JSON si se especifica
        }

        // Configuración de la solicitud
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $method);

        // Si hay datos en el cuerpo de la solicitud (POST o PUT)
        if (!empty($data)) {
            curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
        }

        // Si hay cabeceras, las agregamos
        if (!empty($headers)) {
            curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        }

        // Ejecutamos la solicitud
        $response = curl_exec($ch);

        // Obtener el código de estado HTTP
        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

        // Manejo de errores
        if (curl_errno($ch)) {
            throw new Exception(curl_error($ch));
        }

        curl_close($ch);

        // Devolvemos un array con el cuerpo de la respuesta y el código de estado HTTP
        return [
            'http_code' => $httpCode,             // Código de estado HTTP
            'response'  => json_decode($response, true)  // Decodificamos la respuesta si es JSON
        ];
    }
}
?>
