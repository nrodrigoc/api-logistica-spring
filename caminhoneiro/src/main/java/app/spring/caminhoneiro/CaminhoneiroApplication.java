package app.spring.caminhoneiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class CaminhoneiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaminhoneiroApplication.class, args);
    }

}
