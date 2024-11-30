/*
  2024
*/
package org.project.infrastructure.persistence.mongo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.lang.NonNull;

@Configuration
@EnableMongoAuditing
@Slf4j
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Bean
    public MongoConfigurationProperties mongoProps() {
        return new MongoConfigurationProperties();
    }

    @NonNull
    @SneakyThrows
    @Bean
    @Override
    public MongoClient mongoClient() {
        var mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(this.mongoProps().getUri())).build();
        return MongoClients.create(mongoClientSettings);
    }

    @NonNull
    @Bean
    @Override
    public SimpleMongoClientDatabaseFactory mongoDbFactory() {
        return new SimpleMongoClientDatabaseFactory(mongoClient(), this.mongoProps().getDatabase());
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @NonNull
    @Override
    protected String getDatabaseName() {
        return mongoProps().getDatabase();
    }

}
