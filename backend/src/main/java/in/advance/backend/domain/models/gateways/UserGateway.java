package in.advance.backend.domain.models.gateways;

import in.advance.backend.domain.models.User;
import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserGateway {
    public Mono<User> save(User user);
    public Mono<User> findById(UUID id);

    Mono<User> login(User user);

    @Transactional
    Mono<Integer> updateLastLogin(User user);

    @Transactional
    Mono<Integer> updateIsActive(User user);
}
