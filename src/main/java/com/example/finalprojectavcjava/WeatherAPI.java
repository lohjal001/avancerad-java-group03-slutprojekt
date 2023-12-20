package com.example.finalprojectavcjava;

import cn.hutool.json.JSONObject;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class WeatherAPI {

    private static final String API_KEY = "39a9fa293da14c18d62e493441646e01";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=55.61&lon=13.00&appid=39a9fa293da14c18d62e493441646e01&units=metric";

    public String iconID;

    public String getIconID() {
        return iconID;
    }

    public String temp;
    public String getTemp() {
        return temp;
    }

    public String weatherDescription;
    public String getWeatherDescription() {
        return weatherDescription;
    }


    public void Weather(String[] args) {
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
            System.out.println(allJsonData);


            JsonArray weatherData = allJsonData.get("weather").asArray();
            System.out.println(weatherData);

            JsonObject mainObject = allJsonData.get("main").asObject();

            JsonObject jo2 = weatherData.get(0).asObject();
            System.out.println(jo2);

            double temp = mainObject.getDouble("temp", Double.NaN);
            System.out.println(temp);

            String weatherDescription = jo2.getString("description", "missing");
            System.out.println(weatherDescription);

            iconID = jo2.getString("icon", "missing");
            System.out.println(iconID);

            String iconUrl = "http://openweathermap.org/img/w/" + iconID + ".png";


            TextField weatherTextField = new TextField();
            weatherTextField.setText(weatherDescription + temp);

            // load the image
            Image image = new Image("http://openweathermap.org/img/w/" + iconID + ".png");

            ImageView weatherImageView = new ImageView();
            weatherImageView.setImage(image);

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