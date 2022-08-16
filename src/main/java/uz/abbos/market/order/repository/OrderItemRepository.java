package uz.abbos.market.order.repository;

import org.springframework.stereotype.Repository;
import uz.abbos.market.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>, JpaSpecificationExecutor<OrderItem> {
    Optional<OrderItem> findByIdAndDeletedAtIsNull(Integer id);
}
