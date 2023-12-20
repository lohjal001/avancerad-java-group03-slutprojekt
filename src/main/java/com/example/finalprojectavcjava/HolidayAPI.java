package com.example.finalprojectavcjava;

import cn.hutool.json.JSONObject;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HolidayAPI {
    // API-nyckel och URL för att hämta helgdagar
    private static String API_KEY = "713f3086-0827-4f03-b2e3-a41e985b12c1";
    private static String API_URL = "https://holidayapi.com/v1/holidays?pretty&key=713f3086-0827-4f03-b2e3-a41e985b12c1&country=SE&language=sv&year=2022";

    // Metoden för att hämta och bearbeta helgdagar
    public static void Holidays(String[] args) {
        // Skapar en JSON-objekt för att helgdagsinformation
        JSONObject obj = new JSONObject();

        try {
            // Skapar en URL med API-nyckel och hämtar en HTTP-anslutning
            URL url = new URL(String.format(API_URL, API_KEY));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Skapar en BufferedReader för att läsa svaret från anslutningen
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            // Läser och bygger upp svaret från API-anropet
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Skapar ett JSON-objekt från API-svaret
            JsonObject holidaysOne = Json.parse(response.toString()).asObject();

            // Hämtar och skriver ut alla helgdagar som en array
            JsonArray holidaysArray = holidaysOne.get("holidays").asArray();
            System.out.println(holidaysArray);

            // TODO: 2023-12-20 Lägg till kod här för att matcha kalenderns datum med helgdagar (om det är relevant här)

            // Hämtar och skriver ut information om den första helgdagen i arrayen
            JsonObject holidaysString = holidaysArray.get(0).asObject();
            System.out.println(holidaysString);

            // Hämtar och skriver ut namnet på den första helgdagen
            String holidayName = holidaysString.getString("name", "missing");
            System.out.println(holidayName);

            // Hämtar och skriver ut datumet för den första helgdagen
            String holidayDate = holidaysString.getString("date", "missing");
            System.out.println(holidayDate);

            // Stänger BufferedReader och kopplar från anslutningen
            reader.close();
            connection.disconnect();

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
