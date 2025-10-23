package br.com.bradesco.cnsg.persisteposvenda.application.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "br.com.bradesco.cnsg.persisteposvenda.adapter.database.repository.secondary",
        mongoTemplateRef = "secondaryMongoTemplate"
)
public class SecondaryMongoConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
    public MongoProperties secondaryMongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "secondaryMongoClient")
    public MongoClient secondaryMongoClient() {
        return MongoClients.create(secondaryMongoProperties().getUri());
    }

    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() {
        MongoTemplate template = new MongoTemplate(secondaryMongoClient(), secondaryMongoProperties().getDatabase());
        MappingMongoConverter converter = (MappingMongoConverter) template.getConverter();
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return template;
    }

    @Bean(name = "secondaryMappingMongoConverter")
    public MappingMongoConverter secondaryMappingMongoConverter() {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(secondaryMongoDatabaseFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, secondaryMongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    @Bean(name = "secondaryMongoDatabaseFactory")
    public MongoDatabaseFactory secondaryMongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(secondaryMongoClient(), secondaryMongoProperties().getDatabase());
    }

    @Bean
    public MongoMappingContext secondaryMongoMappingContext() {
        MongoMappingContext context = new MongoMappingContext();
        context.setAutoIndexCreation(true);
        return context;
    }
}
