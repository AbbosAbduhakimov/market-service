package uz.abbos.market.address.repository;

import org.springframework.stereotype.Repository;
import uz.abbos.market.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>, JpaSpecificationExecutor<Address> {
    Optional<Address> findByIdAndDeletedAtIsNull(Integer id);
}
