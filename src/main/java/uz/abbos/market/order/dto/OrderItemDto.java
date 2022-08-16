package uz.abbos.market.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import uz.abbos.market.product.model.Product;
import uz.abbos.market.order.model.Order;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDto {

    private Integer id;

    private Product product;
    @NotBlank(message = ("The productId cannot be empty or null"))
    private Integer productId;

    private Order order;
    @NotBlank(message = ("The orderId cannot be empty or null"))
    private Integer orderId;

}
