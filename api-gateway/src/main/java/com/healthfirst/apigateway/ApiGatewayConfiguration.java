package com.healthfirst.apigateway;

import java.util.function.Function;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

  @Bean
  public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
    Function< PredicateSpec, Buildable<Route>> routeFunction;
    return builder.routes()
        /**
         * '/get' is being left as an example of parsing for future development
        .route(p -> p
            .path("/get")
            .filters(f -> f
                .addRequestHeader("MyHeader", "MyURI")
                .addRequestParameter("Param", "MyValue"))
            .uri("http://httpbin.org:80"))
         **/
        .route(p -> p.path("/classes/**")
            .uri("lb://classes-service")
            )
        .route(p -> p.path("/members/**")
            .uri("lb://member-service")
        )
        .build();
  }

}
