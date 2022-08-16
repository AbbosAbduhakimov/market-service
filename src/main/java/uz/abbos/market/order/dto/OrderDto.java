package uz.abbos.market.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import uz.abbos.market.user.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private Integer id;

    @NotBlank(message = ("The requirement cannot be empty or null"))
    private String requirement;

    @NotBlank(message = ("The contact cannot be empty or null"))
    @Pattern(regexp = "^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$")
    private String contact;

    @NotBlank(message = ("The address cannot be empty or null"))
    private String address;

    private BigDecimal totalPayment;

    @NotBlank(message = ("The userId cannot be empty or null"))
    private Integer userId;

    private LocalDateTime deliveryDate;

    private LocalDateTime deliveredDate;

}
