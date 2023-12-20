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


public class WeatherAPI {

    private static final String API_KEY = "39a9fa293da14c18d62e493441646e01";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=55.61&lon=13.00&units=metric&lang=sv&appid=39a9fa293da14c18d62e493441646e01";

    public static String iconID;

    public static String getIconID() {
        return iconID;
    }

    public static String temp;
    public String getTemp() {
        return temp;
    }

    public static String weatherDescription;
    public String getWeatherDescription() {
        return weatherDescription;
    }


    public static void weather() {
        String city = "Malmö";
        JSONObject obj = new JSONObject();


        try {
            URL url = new URL(String.format(API_URL, city, API_KEY));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            JsonObject allJsonData = Json.parse(response.toString()).asObject();

            //första väderdatan i en array
            JsonArray weatherData = allJsonData.get("weather").asArray();

            JsonObject mainObject = allJsonData.get("main").asObject();

            //plockar ut första objectet ur arrayen
            JsonObject jo2 = weatherData.get(0).asObject();

            //tar fram temperaturen som en double
            double temp = mainObject.getDouble("temp", Double.NaN);

            //tar fram beskrivningen av vädret som en string
            String weatherDescription = jo2.getString("description", "missing");

            //plockar fram ID till en image som symboliserar vädret för att hämta bilden med hjälp av URL
            iconID = jo2.getString("icon", "missing");

            //URL för att hämta bilden som symboliserar vädret
            String iconUrl = "http://openweathermap.org/img/w/" + iconID + ".png";




        } catch (ProtocolException ex) {
            throw new RuntimeException(ex);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
    private static String parseWeatherData(String jsonData) {
        System.out.println(jsonData);


        return jsonData;
    }
}