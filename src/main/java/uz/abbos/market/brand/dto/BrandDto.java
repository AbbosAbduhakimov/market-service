package uz.abbos.market.brand.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandDto {
    private Integer id;

    @NotBlank(message = "The name cannot be empty or null")
    private String name;

}
