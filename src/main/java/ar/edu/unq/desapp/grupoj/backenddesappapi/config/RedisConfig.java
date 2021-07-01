package ar.edu.unq.desapp.grupoj.backenddesappapi.config;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ComponentScan("ar.edu.unq.desapp.grupoj.backenddesappapi")
@EnableRedisRepositories(basePackages = "ar.edu.unq.desapp.grupoj.backenddesappapi.repository")
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Title> redisTemplate() {
        final RedisTemplate<String, Title> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        //template.setHashValueSerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericToStringSerializer<Title>(Title.class));
        return template;
    }
}
