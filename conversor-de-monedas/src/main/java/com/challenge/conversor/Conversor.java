package com.challenge.conversor;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Conversor {

    private final ApiClient apiClient;
    private final Gson gson;

    public Conversor() {
        this.apiClient = new ApiClient();
        this.gson = new Gson();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 7) {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= 6) {
                    procesarOpcion(opcion, scanner);
                } else if (opcion != 7) {
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar el buffer del scanner
            } catch (IOException | InterruptedException e) {
                System.err.println("Error al conectar con la API: " + e.getMessage());
                break; // Salir del bucle si hay un error de API
            }
        }
        System.out.println("Gracias por usar el Conversor de Monedas. ¡Hasta luego!");
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n***************************************************");
        System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
        System.out.println("1) Dólar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real brasileño");
        System.out.println("4) Real brasileño =>> Dólar");
        System.out.println("5) Dólar =>> Peso colombiano");
        System.out.println("6) Peso colombiano =>> Dólar");
        System.out.println("7) Salir");
        System.out.print("Elija una opción válida: ");
        System.out.println("***************************************************");
    }

    private void procesarOpcion(int opcion, Scanner scanner) throws IOException, InterruptedException {
        String monedaOrigen = "";
        String monedaDestino = "";

        switch (opcion) {
            case 1: monedaOrigen = "USD"; monedaDestino = "ARS"; break;
            case 2: monedaOrigen = "ARS"; monedaDestino = "USD"; break;
            case 3: monedaOrigen = "USD"; monedaDestino = "BRL"; break;
            case 4: monedaOrigen = "BRL"; monedaDestino = "USD"; break;
            case 5: monedaOrigen = "USD"; monedaDestino = "COP"; break;
            case 6: monedaOrigen = "COP"; monedaDestino = "USD"; break;
        }

        System.out.print("Ingrese el valor que desea convertir: ");
        try {
            double cantidad = scanner.nextDouble();
            realizarConversion(cantidad, monedaOrigen, monedaDestino);
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, ingrese un valor numérico válido.");
            scanner.next(); // Limpiar el buffer
        }
    }

    private void realizarConversion(double cantidad, String origen, String destino) throws IOException, InterruptedException {
        // Obtenemos los datos de la API
        String jsonResponse = apiClient.getRates(origen);
        ApiResponse apiResponse = gson.fromJson(jsonResponse, ApiResponse.class);

        // Verificamos que la respuesta de la API sea exitosa
        if (!"success".equals(apiResponse.getResult())) {
            System.out.println("Error al obtener las tasas de cambio.");
            return;
        }

        // Obtenemos el mapa de tasas y la tasa específica
        Map<String, Double> rates = apiResponse.getConversionRates();
        Double tasaConversion = rates.get(destino);

        if (tasaConversion == null) {
            System.out.println("No se encontró la tasa de conversión para " + destino);
            return;
        }

        // Calculamos y mostramos el resultado
        double resultado = cantidad * tasaConversion;
        System.out.printf("El valor de %.2f %s corresponde al valor final de =>>> %.2f %s%n",
                cantidad, origen, resultado, destino);
    }
}
