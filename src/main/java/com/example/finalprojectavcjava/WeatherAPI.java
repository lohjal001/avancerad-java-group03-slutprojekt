
import cn.hutool.json.JSONObject;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WeatherAPI {

    private static final String API_KEY = "39a9fa293da14c18d62e493441646e01";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public static void main(String[] args) {
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

            JsonObject jo = Json.parse(response.toString()).asObject();

            JsonArray ja = jo.get("weather").asArray();

            JsonObject jo2 = ja.get(0).asObject();

            String s = jo2.getString("description", "missing");
            System.out.println(s);

            String iconID = jo2.getString("icon", "missing");
            System.out.println(iconID);

            String iconUrl = "http://openweathermap.org/img/w/" + iconID + ".png";



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static String parseWeatherData(String jsonData) {
        System.out.println(jsonData);


        return jsonData;
    }
}