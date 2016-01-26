package com.objectpartners.integrationspec
import com.objectpartners.SampleApplication
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.TestRestTemplate
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringApplicationConfiguration(classes = SampleApplication)
@ActiveProfiles('build')
@WebIntegrationTest
class BaseIntegrationSpec extends Specification {

    TestRestTemplate restTemplate = new TestRestTemplate()

    @Value('${local.server.port}')
    int port

    protected String getBaseUrl() {
        return "http://localhost:${port}"
    }
}
