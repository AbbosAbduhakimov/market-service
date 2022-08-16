package uz.abbos.market.merchant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MerchantDto {
    private Integer id;

    @NotBlank(message = "The name cannot empty")
    private String name;
}
