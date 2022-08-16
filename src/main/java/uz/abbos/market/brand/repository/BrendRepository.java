package uz.abbos.market.brand.repository;

import org.springframework.stereotype.Repository;
import uz.abbos.market.brand.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
@Repository
public interface BrendRepository extends JpaRepository<Brand,Integer>, JpaSpecificationExecutor<Brand> {
    Optional<Brand> findByIdAndDeletedAtIsNull(Integer id);
}
