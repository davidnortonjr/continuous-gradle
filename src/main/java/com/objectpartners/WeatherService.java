package com.objectpartners;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class WeatherService {
    public Weather getWeather() {
        int minnesotaTemperature = getTemperature("55401", "us");
        int cancunTemperature = getTemperature("77516", "mx");

        Weather weather = new Weather();

        weather.setMinneapolis(minnesotaTemperature);
        weather.setCancun(cancunTemperature);
        weather.setWarmerInCancun(cancunTemperature > minnesotaTemperature);

        return weather;
    }

    private int getTemperature(String zip, String country) {
        LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
        params.put("zip", zip);
        params.put("country", country);
        params.put("apiKey", apiKey);
        Map weather = restTemplate.getForObject(url, Map.class, params);
        return fahrenheit((Double) ((Map)weather.get("main")).get("temp"));
    }

    private int fahrenheit(double kelvin) {
        double result = kelvin * 9 / 5 - 459.67;
        return (int) Math.round(result);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${weather.baseUrl}/data/2.5/weather?zip={zip},{country}&appid={apiKey}")
    private String url;
    @Value("${weather.apiKey}")
    private String apiKey;
    private RestTemplate restTemplate = new RestTemplate();
}
