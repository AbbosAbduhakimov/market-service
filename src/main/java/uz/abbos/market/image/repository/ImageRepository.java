package uz.abbos.market.image.repository;

import org.springframework.stereotype.Repository;
import uz.abbos.market.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
@Repository
public interface ImageRepository extends JpaRepository<Image,Integer>, JpaSpecificationExecutor<Image> {
    Optional<Image> findByIdAndDeletedAtIsNull(Integer id);
}
