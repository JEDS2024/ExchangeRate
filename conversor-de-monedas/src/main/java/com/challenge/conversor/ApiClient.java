package com.challenge.conversor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    // aqui ponemos el api key gratis de la API de ExchangeRate-API
    private static final String API_KEY = "065705e7e775c2006b044230";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

    public String getRates(String baseCurrency) throws IOException, InterruptedException {
        // Validamos la clave de API
        if ("TU_API_KEY".equals(API_KEY)) {
            throw new IOException("Por favor, reemplaza 'TU_API_KEY' con tu clave de API real en ApiClient.java");
        }

        // Construimos la URL para la solicitud a la API
        URI uri = URI.create(API_URL + API_KEY + "/latest/" + baseCurrency);

        // Creamos un cliente y una solicitud HTTP modernos
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        // Enviamos la solicitud y obtenemos la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verificamos si la solicitud fue exitosa
        if (response.statusCode() != 200) {
            throw new IOException("Error en la solicitud a la API: Código de estado " + response.statusCode());
        }

        return response.body();
    }
}
