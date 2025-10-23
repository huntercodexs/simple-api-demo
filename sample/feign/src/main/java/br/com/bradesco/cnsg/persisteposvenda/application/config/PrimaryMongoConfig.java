package br.com.bradesco.cnsg.persisteposvenda.application.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = "br.com.bradesco.cnsg.persisteposvenda.adapter.database.repository.primary",
        mongoTemplateRef = "primaryMongoTemplate"
)
public class PrimaryMongoConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.data.mongodb.primary")
    public MongoProperties primaryMongoProperties() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "primaryMongoClient")
    public MongoClient primaryMongoClient() {
        return MongoClients.create(primaryMongoProperties().getUri());
    }

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() {
        MongoTemplate template = new MongoTemplate(primaryMongoClient(), primaryMongoProperties().getDatabase());
        MappingMongoConverter converter = (MappingMongoConverter) template.getConverter();
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return template;
    }

    @Primary
    @Bean(name = "primaryMappingMongoConverter")
    public MappingMongoConverter primaryMappingMongoConverter() {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(primaryMongoDatabaseFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, primaryMongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    @Primary
    @Bean
    public MongoDatabaseFactory primaryMongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(primaryMongoClient(), primaryMongoProperties().getDatabase());
    }

    @Primary
    @Bean
    public MongoMappingContext primaryMongoMappingContext() {
        MongoMappingContext context = new MongoMappingContext();
        context.setAutoIndexCreation(true);
        return context;
    }
}
