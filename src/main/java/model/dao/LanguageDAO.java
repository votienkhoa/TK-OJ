package model.dao;

import model.bean.Language;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LanguageDAO {
    private static final String API_KEY = "57864d02f2mshd430afe70b66696p1c3b26jsnaf534ded1efc";
    private static final String API_URL = "https://judge0-ce.p.rapidapi.com/languages/";
    private static final String API_HOST = "judge0-ce.p.rapidapi.com";
    public HashMap<Integer, String> getLanguages() {
        HashMap<Integer, String> languagesMap = new HashMap<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("X-RapidAPI-Host", API_HOST)
                .header("X-RapidAPI-Key", API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200){
                JSONArray languagesArray = new JSONArray(response.body());
                for (int i = 0; i < languagesArray.length(); i++){
                    JSONObject language = languagesArray.getJSONObject(i);
                    int id = language.getInt("id");
                    String name = language.getString("name");

                    languagesMap.put(id, name);
                }
            }
            return languagesMap;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public String getLanguageByID(int id){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + id))
                .header("X-RapidAPI-Host", API_HOST)
                .header("X-RapidAPI-Key", API_KEY)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200){
                JSONObject resp = new JSONObject(response.body());
                return resp.getString("name");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
