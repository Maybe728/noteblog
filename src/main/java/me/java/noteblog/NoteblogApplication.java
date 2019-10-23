package me.java.noteblog;

import me.java.noteblog.annotation.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "me.java.noteblog.mapper", annotationClass = Mapper.class)
@EnableScheduling
@EnableCaching
public class NoteblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteblogApplication.class, args);
    }

}
