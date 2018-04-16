package ftn;

import ftn.service.SQLService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebApp{

        public static void main(String[] args) {
            ConfigurableApplicationContext applicationContext = SpringApplication.run(WebApp.class, args);
            applicationContext.getBean(SQLService.class).runInitializationSQLScript();

        }
}
