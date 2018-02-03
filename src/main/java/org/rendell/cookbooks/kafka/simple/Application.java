package org.rendell.cookbooks.kafka.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootApplication
public class Application {


    public static ConfigurableApplicationContext start() {
        return SpringApplication.run(Application.class);

    }

}
