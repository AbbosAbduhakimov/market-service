package uz.abbos.market.user.repository;

import org.springframework.stereotype.Repository;
import uz.abbos.market.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    Optional<User> findByIdAndDeletedAtIsNull(Integer id);
    Optional<User> findByEmailAndDeletedAtIsNull(String username);
    Optional<User> findByEmailOrContactAndDeletedAtIsNull(String email, String contact);
    Optional<User> findByEmailAndPasswordAndDeletedAtIsNull(String email, String password);
}
