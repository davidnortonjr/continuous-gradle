package com.objectpartners
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class WeatherController {
    @Autowired
    WeatherService weatherService

    @RequestMapping("/")
    @ResponseBody
    String home() {
        Weather weather = weatherService.getWeather()

        return """<html><body>
<h3>Is it warmer in Cancun?</h3>
<h2>${weather.warmerInCancun ? 'Yes' : 'No?!?'}</h2>
<div>MINNEAPOLIS: ${weather.minneapolis}° F</div>
<div>CANCUN: ${weather.cancun}° F</div>
<div><img src="http://www.millcitysnap.com/photos/i-9LMtxW7/0/6285x1424/i-9LMtxW7-6285x1424.jpg" style="width:100%;" /></div>
<div><small>Image courtesy Jon DeJong</small></div>
</body></html>"""
    }


    @RequestMapping("/api")
    @ResponseBody
    Weather api() {
        return weatherService.getWeather()
    }

}
