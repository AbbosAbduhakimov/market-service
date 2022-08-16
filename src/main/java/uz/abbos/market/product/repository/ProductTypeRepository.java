package uz.abbos.market.product.repository;

import org.springframework.stereotype.Repository;
import uz.abbos.market.product.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Integer>, JpaSpecificationExecutor<ProductType> {
    Optional<ProductType> findByIdAndDeletedAtIsNull(Integer id);
}
