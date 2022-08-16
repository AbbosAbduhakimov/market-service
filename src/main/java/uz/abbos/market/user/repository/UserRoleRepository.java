package uz.abbos.market.user.repository;

import org.springframework.stereotype.Repository;
import uz.abbos.market.user.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> , JpaSpecificationExecutor<UserRole> {
    Optional<UserRole> findByIdAndDeletedAtIsNull(Integer id);
}
