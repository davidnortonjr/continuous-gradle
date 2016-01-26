package com.objectpartners

import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Unroll

class WeatherServiceSpec extends Specification {
    public static final String FAKE_URL = 'some url'
    public static final String FAKE_API_KEY = 'some key'

    WeatherService weatherService = new WeatherService(restTemplate: Mock(RestTemplate), url: FAKE_URL, apiKey: FAKE_API_KEY)

    @Unroll
    def "getWeather: cancun #mxFahrenheit minneapolis #usFahrenheit"() {
        when:
        Weather result = weatherService.getWeather()

        then:
        1 * weatherService.restTemplate.getForObject(FAKE_URL, Map, [zip: '55401', country: 'us', apiKey: FAKE_API_KEY]) >> [main: [temp: usKelvin]]
        1 * weatherService.restTemplate.getForObject(FAKE_URL, Map, [zip: '77516', country: 'mx', apiKey: FAKE_API_KEY]) >> [main: [temp: mxKelvin]]
        0 * _

        result.warmerInCancun == expectedWarmer
        result.minneapolis == usFahrenheit
        result.cancun == mxFahrenheit

        where:
        usKelvin | mxKelvin | usFahrenheit | mxFahrenheit | expectedWarmer
        300      | 301      | 80           | 82           | true
        301      | 300      | 82           | 80           | false
        249.81   | 301      | -10          | 82           | true
    }
}
