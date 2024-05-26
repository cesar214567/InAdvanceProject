package in.advance.backend.entrypoints;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserRouter {
    @Bean
    public RouterFunction<ServerResponse> userRouterFunction(UserHandler userHandler) {
        return RouterFunctions.
                route(POST("/user"), userHandler::register)
                .andRoute(GET("/user/{id}"),userHandler::getUser)
                .andRoute(POST("/login"),userHandler::login)
                .andRoute(DELETE("/user/{id}"),userHandler::deleteUser)
                .andRoute(PUT("/user/{id}"),userHandler::updateUser);

    }
}
