package com.objectpartners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        final Weather weather = weatherService.getWeather();

        return "<html><body>\n<h3>Is it warmer in Cancun?</h3>\n<h2>" + (weather.isWarmerInCancun() ? "Yes" : "No?!?") + "</h2>\n<div>MINNEAPOLIS: " + String.valueOf(weather.getMinneapolis()) + "° F</div>\n<div>CANCUN: " + String.valueOf(weather.getCancun()) + "° F</div>\n<div><img src=\"http://www.millcitysnap.com/photos/i-9LMtxW7/0/6285x1424/i-9LMtxW7-6285x1424.jpg\" style=\"width:100%;\" /></div>\n<div><small>Image courtesy Jon DeJong</small></div>\n</body></html>";
    }

    @RequestMapping("/api")
    @ResponseBody
    public Weather api() {
        return weatherService.getWeather();
    }

    public WeatherService getWeatherService() {
        return weatherService;
    }

    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Autowired
    private WeatherService weatherService;
}
