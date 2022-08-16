package uz.abbos.market.user.repository;

import org.springframework.stereotype.Repository;
import uz.abbos.market.user.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Integer> {
    Optional<UserType> findByIdAndDeletedAtIsNull(Integer id);

}
