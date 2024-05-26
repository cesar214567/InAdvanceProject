package in.advance.backend.entrypoints;

import in.advance.backend.commons.ExceptionHandler;
import in.advance.backend.commons.TechLogger;
import in.advance.backend.domain.models.User;
import in.advance.backend.domain.usecases.UserUseCase;
import in.advance.backend.entrypoints.dtos.LoginDTO;
import in.advance.backend.entrypoints.dtos.RegisterDTO;
import in.advance.backend.entrypoints.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserHandler {
    private final UserUseCase userUseCase;
    private final TechLogger techLogger;
    private final ExceptionHandler exceptionHandler;
    public Mono<ServerResponse> register(ServerRequest serverRequest){
        return serverRequest.bodyToMono(RegisterDTO.class)
                .doOnNext(techLogger::logInfo)
                .map(UserMapper::mapRegisterDTOToUser)
                .doOnNext(techLogger::logInfo)
                .flatMap(userUseCase::saveOrUpdateUser)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .onErrorResume(Exception.class, exceptionHandler::handleException);
    }
    public Mono<ServerResponse> getUser(ServerRequest serverRequest){
        return Mono.just(serverRequest.pathVariable("id"))
                .flatMap(userUseCase::getUser)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .switchIfEmpty(exceptionHandler.handleNotFound(User.class.getSimpleName()))
                .onErrorResume(Exception.class, exceptionHandler::handleException);
    }

    public Mono<ServerResponse> login(ServerRequest serverRequest){
        return serverRequest.bodyToMono(LoginDTO.class)
                .doOnNext(techLogger::logInfo)
                .map(UserMapper::mapLoginDTOToUser)
                .doOnNext(techLogger::logInfo)
                .flatMap(userUseCase::loginUser)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .switchIfEmpty(exceptionHandler.handleNotFound(User.class.getSimpleName()));
    }

    public Mono<ServerResponse> deleteUser(ServerRequest serverRequest){
        return Mono.just(serverRequest.pathVariable("id"))
                .flatMap(userUseCase::deleteUser)
                .flatMap(user -> ServerResponse.ok().build())
                .switchIfEmpty(exceptionHandler.handleNotFound(User.class.getSimpleName()))
                .onErrorResume(Exception.class, exceptionHandler::handleException);
    }
    public Mono<ServerResponse> updateUser(ServerRequest serverRequest){
        return serverRequest.bodyToMono(RegisterDTO.class)
                .doOnNext(techLogger::logInfo)
                .map(UserMapper::mapRegisterDTOToUser)
                .map(user -> user.toBuilder()
                        .id(UUID.fromString(serverRequest.pathVariable("id"))).build())
                .doOnNext(techLogger::logInfo)
                .flatMap(userUseCase::saveOrUpdateUser)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .onErrorResume(Exception.class, exceptionHandler::handleException);
    }
}
