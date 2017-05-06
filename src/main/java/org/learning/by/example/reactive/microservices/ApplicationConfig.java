package org.learning.by.example.reactive.microservices;

import org.learning.by.example.reactive.microservices.handlers.ErrorHandler;
import org.learning.by.example.reactive.microservices.handlers.ApiHandler;
import org.learning.by.example.reactive.microservices.routers.MainRouter;
import org.learning.by.example.reactive.microservices.services.HelloService;
import org.learning.by.example.reactive.microservices.services.HelloServiceImpl;
import org.learning.by.example.reactive.microservices.services.QuoteService;
import org.learning.by.example.reactive.microservices.services.QuoteServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
@EnableWebFlux
public class ApplicationConfig {

    @Bean
    QuoteService quoteService(){ return new QuoteServiceImpl();}

    @Bean
    HelloService helloService() {
        return new HelloServiceImpl();
    }

    @Bean
    ApiHandler apiHandler(final HelloService helloService, final ErrorHandler errorHandler) {
        return new ApiHandler(helloService, errorHandler);
    }

    @Bean
    ErrorHandler errorHandler() {
        return new ErrorHandler();
    }

    @Bean
    RouterFunction<?> mainRouterFunction(final ApiHandler apiHandler, final ErrorHandler errorHandler) {
        return MainRouter.doRoute(apiHandler, errorHandler);
    }
}