# Conversor de Monedas

Este es un simple pero funcional conversor de monedas de línea de comandos desarrollado en Java. La aplicación permite a los usuarios obtener tasas de cambio en tiempo real y convertir montos entre diferentes pares de monedas, utilizando la [ExchangeRate-API](https://www.exchangerate-api.com/) para obtener los datos más recientes.

## ✨ Características

- **Conversión en Tiempo Real**: Utiliza una API para obtener las tasas de cambio más actualizadas.
- **Interfaz de Consola Interactiva**: Un menú fácil de usar que guía al usuario a través de las opciones de conversión.
- **Pares de Monedas Predefinidos**:
  - Dólar (USD) ↔ Peso Argentino (ARS)
  - Dólar (USD) ↔ Real Brasileño (BRL)
  - Dólar (USD) ↔ Peso Colombiano (COP)
- **Manejo de Errores**: Incluye validaciones para entradas de usuario incorrectas y problemas de conexión con la API.
- **Construido con Maven**: Utiliza Maven para una gestión de dependencias y construcción del proyecto sencillas.

## 🛠️ Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- **Java Development Kit (JDK)**: Versión 11 o superior.
- **Apache Maven**: Para compilar el proyecto y gestionar las dependencias.
- **Conexión a Internet**: Necesaria para que la aplicación pueda comunicarse con la API de tasas de cambio.

## 🚀 Cómo Empezar

Sigue estos pasos para configurar y ejecutar el proyecto en tu máquina local.

### 1. Configuración de la API Key

El proyecto requiere una clave (API Key) para acceder a los datos de `ExchangeRate-API`.

1.  Regístrate para obtener una clave gratuita en [ExchangeRate-API](https://www.exchangerate-api.com/).
2.  Abre el archivo `src/main/java/com/challenge/conversor/ApiClient.java`.
3.  Reemplaza el valor de la constante `API_KEY` con tu propia clave:

    ```java
    // Reemplaza "TU_API_KEY" con la clave que obtuviste
    private static final String API_KEY = "TU_API_KEY";
    ```

### 2. Compilación y Ejecución

1.  Abre una terminal o línea de comandos.
2.  Navega hasta el directorio raíz del proyecto (`conversor-de-monedas`).
3.  Compila el proyecto utilizando Maven:
    ```sh
    mvn compile
    ```
4.  Una vez compilado, ejecuta la aplicación:
    ```sh
    mvn exec:java -Dexec.mainClass="com.challenge.conversor.Main"
    ```

Después de ejecutar el último comando, verás el menú del conversor en tu consola y podrás empezar a realizar conversiones.

## 🏗️ Estructura del Proyecto

El código fuente se organiza en las siguientes clases dentro del paquete `com.challenge.conversor`:

- **`Main.java`**: El punto de entrada de la aplicación. Crea una instancia de `Conversor` y la inicia.
- **`Conversor.java`**: Contiene la lógica principal de la aplicación, incluyendo el menú interactivo, la captura de la entrada del usuario y la orquestación del proceso de conversión.
- **`ApiClient.java`**: Gestiona toda la comunicación con la `ExchangeRate-API`. Es responsable de construir y enviar la solicitud HTTP para obtener las tasas de cambio.
- **`ApiResponse.java`**: Una clase POJO (Plain Old Java Object) que utiliza la librería Gson para mapear la respuesta JSON de la API a un objeto Java, facilitando el acceso a los datos.

## 🔧 Dependencias

El proyecto utiliza [Apache Maven](https://maven.apache.org/) para gestionar sus dependencias. La única dependencia externa principal es:

- **Gson**: Una librería de Google que convierte objetos Java a su representación JSON y viceversa. Es fundamental para procesar la respuesta de la API.

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
```

## 🔄 Flujo de Trabajo de la Aplicación

1.  **Inicio**: El método `main` en la clase `Main` crea una nueva instancia de `Conversor` y llama a su método `iniciar()`.
2.  **Bucle Principal**: El método `iniciar()` contiene un bucle `while` que mantiene la aplicación en ejecución hasta que el usuario elige la opción "Salir".
3.  **Mostrar Menú**: En cada iteración del bucle, se muestra un menú con las opciones de conversión disponibles.
4.  **Captura de Entrada**: La aplicación espera a que el usuario ingrese una opción numérica y la cantidad a convertir.
5.  **Procesar Opción**: Según la opción elegida, se determinan las monedas de origen y destino.
6.  **Llamada a la API**: Se invoca al `ApiClient` para realizar una solicitud GET a la `ExchangeRate-API` con la moneda de origen como base.
7.  **Deserialización JSON**: La respuesta JSON de la API es deserializada a un objeto `ApiResponse` utilizando Gson.
8.  **Cálculo**: Se extrae la tasa de conversión relevante del objeto `ApiResponse`.
9.  **Mostrar Resultado**: El resultado de la conversión se formatea y se muestra en la consola.
10. **Manejo de Errores**: Si ocurre un error (por ejemplo, una entrada no válida o un problema de red), se muestra un mensaje de error y la aplicación continúa o se detiene según la gravedad del error.

## 💡 planes para el futuro

algunas ideas:

### Añadir Nuevas Opciones de Conversión

### Permitir Monedas Personalizadas

Para una mayor flexibilidad, podrías modificar la aplicación para que el usuario pueda introducir cualquier código de moneda (por ejemplo, "EUR", "GBP") en lugar de seleccionarlo de una lista fija. Esto requeriría:

1.  Cambiar el menú para solicitar los códigos de moneda de origen y destino directamente.
2.  Validar que los códigos de moneda introducidos por el usuario sean válidos antes de realizar la llamada a la API.
