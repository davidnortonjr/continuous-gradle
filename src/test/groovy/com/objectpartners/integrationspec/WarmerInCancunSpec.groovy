package com.objectpartners.integrationspec

import com.objectpartners.integrationspec.stub.StubWeatherController
import org.springframework.beans.factory.annotation.Autowired

class WarmerInCancunSpec extends BaseIntegrationSpec {
    @Autowired
    StubWeatherController testWeatherController

    void "HTML - normal life"() {
        given:
        testWeatherController.mexicoWeather = 310.93 //kelvin
        testWeatherController.usWeather = 255.37

        when:
        String result = restTemplate.getForObject("${baseUrl}/", String)

        then:
        result.contains('<html>')
        result.contains('<h2>Yes</h2>')
        result.contains('MINNEAPOLIS: 0° F')
        result.contains('CANCUN: 100° F')
    }

    void "HTML - colder in cancun!?!?!"() {
        given:
        testWeatherController.mexicoWeather = 255.37
        testWeatherController.usWeather = 310.93

        when:
        String result = restTemplate.getForObject("${baseUrl}/", String)

        then:
        result.contains('<html>')
        result.contains('<h2>No?!?</h2>')
        result.contains('MINNEAPOLIS: 100° F')
        result.contains('CANCUN: 0° F')
    }

    void "API: it's warmer in Cancun"() {
        given:
        testWeatherController.mexicoWeather = 310.93
        testWeatherController.usWeather = 255.37

        when:
        Map result = restTemplate.getForObject("${baseUrl}/api", Map)

        then:
        result == [
                warmerInCancun: true,
                minneapolis   : 0,
                cancun        : 100
        ]
    }

    void "API: it's colder in Cancun"() {
        given:
        testWeatherController.mexicoWeather = 255.37
        testWeatherController.usWeather = 310.93

        when:
        Map result = restTemplate.getForObject("${baseUrl}/api", Map)

        then:
        result == [
                warmerInCancun: false,
                minneapolis   : 100,
                cancun        : 0
        ]
    }
}
