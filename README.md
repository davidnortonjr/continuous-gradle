# continuous-gradle

An example project for my talk, Building Continuous Delivery: Rock-solid builds with Gradle.

## Building

* JDK 7 or later
* The build uses port 8080

After cloning the project, type the following on the command line:

    ./gradlew clean build

    ls -l build/libs/*.jar

## Building a Docker image

1. Make sure you're running Docker and that the `DOCKER_HOST` environment variable is set.
2. Uncomment the Docker-related lines at the end of `build.gradle`
3. Type the following on the command line:

    ```
    ./gradlew clean build

    # view the image
    docker images | grep continuous-gradle
    
    #run the image!
    docker run -p 8080:8080 -d continuous-gradle:1.0-SNAPSHOT
    
    #if you're running on a Mac, you'll need to run the following from the Docker host, not from your Mac:
    curl http://localhost:8080/
    ```

## Running local server

    ./gradlew bootRun
    # open http://localhost:8080 in your browser

The included OpenWeatherMap API key may expire sometime after this example was created. If that occurs, go to [http://openweathermap.org/appid#get]() to get a new one and place it in `src/main/resources/application.properties`
