# continuous-gradle

An example project for my talk, Building Continuous Delivery: Rock-solid builds with Gradle.

## Building

* JDK 7 or later
* The build uses port 8080

After cloning the project, type `./gradlew clean build` on the command line.
    
## Running local server

    ./gradlew bootRun
    # open http://localhost:8080 in your browser

It is possible that my OpenWeather API key may expire sometime in the future. If that occurs, go to [http://openweathermap.org/appid#get]() to get a new one and place it in `src/main/resources/application.properties`
