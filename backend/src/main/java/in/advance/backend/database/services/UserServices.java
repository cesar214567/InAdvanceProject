package in.advance.backend.database.services;

import in.advance.backend.commons.TechLogger;
import in.advance.backend.database.mappers.UserDAOMapper;
import in.advance.backend.database.repositories.UserRepository;
import in.advance.backend.domain.models.User;
import in.advance.backend.domain.models.gateways.UserGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UserServices implements UserGateway {
    private UserRepository userRepository;
    private TechLogger techLogger;
    @Override
    public Mono<User> save(User user) {

        return Mono.just(UserDAOMapper.mapUserDAO(user))
                .map(userRepository::save)
                .map(userDao -> user.toBuilder()
                        .password(null)
                        .id(userDao.getId())
                        .build());
    }

    @Override
    public Mono<User> findById(UUID id) {
        return Mono.justOrEmpty(userRepository.findById(id))
                .map(UserDAOMapper::mapDAOToUser);
    }

    @Override
    public Mono<User> login(User user) {
        return Mono.justOrEmpty(userRepository.findByEmail(user.getEmail()))
                .filter(userDao -> Objects.equals(userDao.getPassword(), user.getPassword()))
                .map(UserDAOMapper::mapDAOToUser);
    }

    @Override
    @Transactional
    public Mono<Integer> updateLastLogin(User user) {
        return Mono.just(userRepository.updateLastLoginById(LocalDateTime.now(),user.getId()))
                .flatMap(updatedRows -> {
                    if (updatedRows == 0){
                        return Mono.empty();
                    }
                    return Mono.just(updatedRows);
                });
    }
    @Override
    @Transactional
    public Mono<Integer> updateIsActive(User user) {
        return Mono.just(userRepository.updateIsActiveById(Boolean.FALSE,user.getId()))
                .flatMap(updatedRows -> {
                    if (updatedRows == 0){
                        return Mono.empty();
                    }
                    return Mono.just(updatedRows);
                });
    }
}
