/*
  2024
*/
package org.project.infrastructure.persistence.mongo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.data.mongodb")
@Getter
@Setter
public class MongoConfigurationProperties {

    private String uri;
    private String database;

}
