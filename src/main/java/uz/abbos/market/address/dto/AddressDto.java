package uz.abbos.market.address.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {

    private Integer id;

    @NotBlank(message = ("The region cannot be empty or null"))
    private String region;

    @NotBlank(message = ("The city cannot be empty or null"))
    private String city;

    @NotBlank(message = ("The district cannot be empty or null"))
    private String district;

    @NotBlank(message = ("The street cannot be empty or null"))
    private String street;

    private Integer home;
}
