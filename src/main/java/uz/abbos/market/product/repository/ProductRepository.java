package uz.abbos.market.product.repository;

import org.springframework.stereotype.Repository;
import uz.abbos.market.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByIdAndDeletedAtIsNull(Integer id);
}
