package in.advance.backend.domain.usecases;

import in.advance.backend.domain.models.User;
import in.advance.backend.domain.models.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserUseCase {
    private final UserGateway userGateway;
    public Mono<User> saveOrUpdateUser(User user){
        return userGateway.save(user);
    }
    public Mono<User> getUser(String id){
        return userGateway.findById(UUID.fromString(id));
    }

    public Mono<User> loginUser(User user){
        return userGateway.login(user)
                .doOnNext(userGateway::updateLastLogin);
    }

    public Mono<User> deleteUser(String id){
        return getUser(id)
                .doOnNext(userGateway::updateIsActive);
    }

}
