package uz.abbos.market.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ProductDto {
    private Integer id;

    @NotBlank(message = "The name cannot empty or null")
    private String name;

    @NotBlank(message = "The description cannot empty or null")
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer rate;

    @NotNull
    private Integer productType;

    private Boolean visible;
}
