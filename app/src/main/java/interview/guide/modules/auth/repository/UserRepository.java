package interview.guide.modules.auth.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import interview.guide.modules.auth.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
