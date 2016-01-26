package com.objectpartners

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class WeatherService {
    @Value('${weather.baseUrl}/data/2.5/weather?zip={zip},{country}&appid={apiKey}')
    String url

    @Value('${weather.apiKey}')
    String apiKey

    RestTemplate restTemplate = new RestTemplate()

    Weather getWeather() {
        int minnesotaTemperature = getTemperature('55401', 'us')
        int cancunTemperature = getTemperature('77516', 'mx')

        return new Weather(
                minneapolis: minnesotaTemperature,
                cancun: cancunTemperature,
                warmerInCancun: cancunTemperature > minnesotaTemperature
        )
    }

    private int getTemperature(String zip, String country) {
        Map weather = restTemplate.getForObject(url, Map, [zip: zip, country: country, apiKey: apiKey])
        return fahrenheit(weather.main.temp)
    }

    private int fahrenheit(double kelvin) {
        return kelvin * 9 / 5 - 459.67
    }
}
