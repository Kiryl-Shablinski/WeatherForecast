package com.example.weatherforecast;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox<String> box;

    @FXML
    void initialize(){
        ObservableList<String> items = FXCollections.observableArrayList(initialBox());
        box.setItems(items);
    }

    @FXML
    protected void onHelloButtonClick() throws IOException {

        String cityName = box.getValue();
        String lat = "0", lon = "0";

        List<City> cityList = init();
        for(City c : cityList){
            if (cityName.equals(c.getName())){
                lat = c.getLat();
                lon = c.getLon();
                break;
            }
        }


        URL url =
                new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=d873275a4d4bfd46908304cdbfb0cad4");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String str = reader.readLine();

        JSONObject jsonObject = new JSONObject(str);
        JSONObject jsonMain  = (JSONObject) jsonObject.get("main");

        //абсолютный ноль в Кельвинах
        double absolutelyNull = 273.15;String temp = String.valueOf(jsonMain.get("temp"));

      String feelsTemp = String.valueOf(jsonMain.get("feels_like"));
      String pressure = String.valueOf(jsonMain.get("pressure"));
      String humidity = String.valueOf(jsonMain.get("humidity"));
      String wind = String.valueOf(((JSONObject)jsonObject.get("wind")).get("speed"));
      StringBuilder stb = new StringBuilder();
      stb.append("Текущая температура ").append(Math.round(Double.parseDouble(temp) - absolutelyNull)).append("\u00B0C\n");
      stb.append("Ощущается как ").append(Math.round(Double.parseDouble(feelsTemp) - absolutelyNull)).append("\u00B0C\n");
      stb.append("Давление ").append(Math.round(Double.parseDouble(pressure) / 1.333)).append(" мм.рт.ст\n");
      stb.append("Влажность ").append(humidity).append("%\n");
      stb.append("Скорость ветра ").append(Math.round(Double.parseDouble(wind) / 1000 * 3600)).append("км/ч");

        welcomeText.setText(stb.toString());
    }

    private List<City> init(){
        List<City> listCity= new ArrayList<>();
        listCity.add(new City("Брест","52.094110","23.731910"));
        listCity.add(new City("Витебск","55.184590","30.204670"));
        listCity.add(new City("Гомель","52.419130","30.972270"));
        listCity.add(new City("Гродно","53.669353","23.813131"));
        listCity.add(new City("Минск","53.893009","27.567444"));
        listCity.add(new City("Могилев","53.9090245","30.3429838"));
        listCity.add(new City("Москва","55.7504461","55.7504461"));
        listCity.add(new City("Варшава","52.2319581","21.0067249"));
        listCity.add(new City("Вильнюс","54.6870458","25.2829111"));
        listCity.add(new City("Киев","50.4500336","30.5241361"));
        listCity.add(new City("Белосток","53.127505","23.1470509"));
        return listCity;
    }

    protected List<String> initialBox(){
        return init().stream()
                .map(City::getName)
                .toList();
    }








}