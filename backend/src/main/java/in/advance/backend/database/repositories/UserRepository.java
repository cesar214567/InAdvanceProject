package in.advance.backend.database.repositories;

import in.advance.backend.database.entitites.UserDao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserDao, UUID> {
    Optional<UserDao> findByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "update user set last_login = ?1 where id = ?2",nativeQuery = true)
    int updateLastLoginById(LocalDateTime lastLoginTime, UUID id);

    @Modifying
    @Transactional
    @Query(value = "update user set is_active = ?1 where id = ?2",nativeQuery = true)
    int updateIsActiveById(Boolean isActive, UUID id);
}
