package com.example.springbootactuator;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

@EnableSwagger2Doc
@SpringBootApplication
public class SpringBootRedisApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringBootRedisApplication.class, args);
        printLoadResource();
    }

    private static void printLoadResource() throws IOException {
        Enumeration<URL> urls = ClassLoader.getSystemResources("META-INF/spring.factories");
//        System.out.println(Collections.list(urls));
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url.getPath());
            UrlResource resource = new UrlResource(url);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            for (Map.Entry<?, ?> entry : properties.entrySet()) {
                String factoryClassName = ((String) entry.getKey()).trim();
                System.out.println(factoryClassName);
                for (String factoryName : StringUtils.commaDelimitedListToStringArray((String) entry.getValue())) {
                    System.out.println(factoryName.trim());
                }
            }
        }
    }

}
