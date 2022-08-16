package uz.abbos.market.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ProductTypeDto {
    private Integer id;

    @NotBlank(message = "The name cannot empty")
    private String name;

    @NotNull
    private Integer brandId;

    @NotNull
    private Integer merchantID;

    @NotNull
    private Integer processorId;

    private ProductDto productDto;

    @NotNull
    private Integer productTypeId;


}
