package com.objectpartners.integrationspec.stub

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class StubWeatherController {
    BigDecimal usWeather
    BigDecimal mexicoWeather

    @RequestMapping("/data/2.5/weather")
    Map weather(@RequestParam String zip) {
        return [
                main: [
                        temp: zip.endsWith('us') ? usWeather : mexicoWeather
                ]
        ]
    }
}
