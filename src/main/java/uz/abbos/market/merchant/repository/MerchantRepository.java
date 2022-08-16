package uz.abbos.market.merchant.repository;

import org.springframework.stereotype.Repository;
import uz.abbos.market.merchant.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
@Repository
public interface MerchantRepository extends JpaRepository<Merchant,Integer>, JpaSpecificationExecutor<Merchant> {
    Optional<Merchant> findByIdAndDeletedAtIsNull(Integer id);
}
