package api.gateway.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {

    public static final String HTTP_HTTPBIN_ORG = "http://httpbin.org/";
    public static final String ACCEPT = "accept";
    public static final String TEXT_HTML = "text/html";

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/registry/**")
                        .uri("http://localhost:8761/"))
                .route(r -> r.path("/caminhoneiro/**")
                        .uri("lb://caminhoneiro-service"))
                .route(r -> r.path("/api/**")
                        .filters(f -> f.rewritePath("/api/", "/v1/geral/api/"))
                        .uri("lb://app-logistica")).build();
    }
}
