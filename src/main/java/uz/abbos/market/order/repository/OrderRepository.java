package uz.abbos.market.order.repository;

import org.springframework.stereotype.Repository;
import uz.abbos.market.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>, JpaSpecificationExecutor<Order> {

    Optional<Order> findByIdAndDeletedAtIsNull(Integer id);
}
