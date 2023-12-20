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
    private static String API_KEY = "713f3086-0827-4f03-b2e3-a41e985b12c1";

    private static String API_URL = "https://holidayapi.com/v1/holidays?pretty&key=713f3086-0827-4f03-b2e3-a41e985b12c1&country=SE&year=2022";

    public static void Holidays(String[] args) {
        JSONObject obj = new JSONObject();


        try {
            URL url = new URL(String.format(API_URL, API_KEY));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }


            JsonObject holidaysOne = Json.parse(response.toString()).asObject();


            //stoppar in alla helgdagar i en array
            JsonArray holidaysArray = holidaysOne.get("holidays").asArray();
            System.out.println(holidaysArray);

            // TODO: 2023-12-20 stoppa in if-sats som kollar om kalenderns datum matchar datum i arrayen? (kanske inte här?)

            //plockar ut första helgdagen
            JsonObject holidaysString = holidaysArray.get(0).asObject();
            System.out.println(holidaysString);

            //tar ut namn på helgdagen
            String holidayName = holidaysString.getString("name", "missing");
            System.out.println(holidayName);

            //tar ut datum på helgdagen
            String holidayDate= holidaysString.getString("date", "missing");
            System.out.println(holidayDate);




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