package uz.abbos.market.user.dto;

import uz.abbos.market.user.model.UserRole;
import uz.abbos.market.address.model.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import uz.abbos.market.image.model.Image;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    
    private Integer id;

    @NotBlank(message = ("The name cannot be empty or null"))
    private String name;

    @NotBlank(message = ("The surname cannot be empty or null"))
    private String surname;

    @NotBlank(message = ("The email cannot be empty or null"))
    @Email
    private String email;

    @NotBlank(message = ("The password cannot be empty or null"))
    private String password;

    @NotNull
    @Pattern(regexp = "^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$")
    private String contact;

    private boolean status;

    private Image image;
    @NotNull(message = ("The imageId cannot be empty or null"))
    private Integer imageId;

    private UserRole userRole;
    @NotNull(message = ("The userRoleId cannot be empty or null"))
    private Integer userRoleId;

    private Address address;
    @NotNull(message = ("The addressId cannot be empty or null"))
    private Integer addressId;
}
