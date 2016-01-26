package com.objectpartners
import spock.lang.Specification

class WeatherControllerSpec extends Specification {
    WeatherController weatherController = new WeatherController(weatherService: Mock(WeatherService))

    def "api" () {
        given:
        Weather weather = new Weather(warmerInCancun: true, minneapolis: -20, cancun: 80)

        when:
        Weather result = weatherController.api()

        then:
        1 * weatherController.weatherService.getWeather() >> weather
        0 * _

        result.is(weather)
    }

    def "html page" () {
        given:
        Weather weather = new Weather(
                warmerInCancun: false,
                minneapolis: 100,
                cancun: 99
        )

        when:
        String html = weatherController.home()

        then:
        1 * weatherController.weatherService.getWeather() >> weather
        0 * _

        html.contains('No?!?')
        html.contains('MINNEAPOLIS: 100° F')
        html.contains('CANCUN: 99° F')
    }

}
