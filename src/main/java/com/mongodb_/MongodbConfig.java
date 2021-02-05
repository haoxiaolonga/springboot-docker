package com.mongodb_;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * @author haoxl
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/5 14:43
 */
@Configuration
@Slf4j
public class MongodbConfig {

    /**
     * @description: When using spring data mongo it by default adds a _class key to your collection to be able to handle inheritance.
     * But if your domain model is simple and flat, you can remove it by overriding the default MappingMongoConverter
     */
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext context, BeanFactory beanFactory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        try {
            mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
        } catch (NoSuchBeanDefinitionException ignore) {
            log.error("mongoDb setCustomConversion exception:{}", ignore.getMessage());
        }
        // Don't save _class to mongo
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingConverter;
    }

}