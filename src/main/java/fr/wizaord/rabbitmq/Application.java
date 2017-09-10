package fr.wizaord.rabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    @Profile({"receiver", "sender", "all"})
    @Bean
    public CommandLineRunner tutorial() {
        return new ApplicationRunner();
    }


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

}
