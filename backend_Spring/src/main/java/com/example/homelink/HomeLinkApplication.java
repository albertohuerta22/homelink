package com.example.homelink;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

@SpringBootApplication
@EnableCaching
public class HomeLinkApplication {
    public static void main(String[] args) {

        Map<String, Object> env = Dotenv.load()
                .entries()
                .stream()
                .collect(
                        Collectors.toMap(DotenvEntry::getKey, DotenvEntry::getValue));
        new SpringApplicationBuilder(HomeLinkApplication.class)
                .environment(new StandardEnvironment() {
                    @SuppressWarnings("null")
                    @Override
                    protected void customizePropertySources(MutablePropertySources propertySources) {
                        super.customizePropertySources(propertySources);
                        propertySources.addLast(new MapPropertySource("dotenvProperties", env));
                    }
                }).run(args);
    }
}
