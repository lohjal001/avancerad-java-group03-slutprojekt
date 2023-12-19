package com.example.finalprojectavcjava;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI {
    // API-nyckeln för att använda OpenWeatherMap API
    private static final String API_KEY = "39a9fa293da14c18d62e493441646e01";
    // API URL-sträng med plats och API-nyckel som parametrar
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    public static void main(String[] args) {
        // Stad för vilken vädret ska hämtas
        String city = "Malmö";

        try {
            // Skapar URL med hjälp av API-URL och stad
            URL url = new URL(String.format(API_URL, city, API_KEY));

            // Öppnar anslutning till API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Läser data från API-svaret
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            // Samlar ihop API-svaret rad för rad
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Stänger läsare och koppling
            reader.close();
            connection.disconnect();

            // Skriver ut vädret för den angivna staden
            System.out.println("Weather Information for " + city);
            System.out.println(parseWeatherData(response.toString()));
        } catch (Exception e) {
            // Vid fel, skriv ut felmeddelande
            e.printStackTrace();
        }
    }

    // Metodenn för att analysera och behandla vädret från API-svaret
    private static String parseWeatherData(String jsonData) {
        // Tillfällig utskrift av API-svar (kan anpassas)
        System.out.println(jsonData);

        // Returnerar hela API-svaret (kan anpassas)
        return jsonData;
    }
}
