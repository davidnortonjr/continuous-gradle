package com.objectpartners.integrationspec

import org.springframework.beans.factory.annotation.Value

class ActuatorSpec extends BaseIntegrationSpec {
    @Value('${info.name}')
    String appName
    @Value('${info.version}')
    String appVersion

    void "info endpoint includes application properties"() {
        when:
        Map result = restTemplate.getForObject("${baseUrl}/info", Map)

        then:
        result == [
                name: appName,
                version: appVersion
        ]
        result.version == '0-SNAPSHOT' || result.version ==~ /[\d\.]*/
    }
}
