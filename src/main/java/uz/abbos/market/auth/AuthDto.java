package uz.abbos.market.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthDto {
    @NotBlank(message = ("The Email cannot be empty or null"))
    @Email
    private String email;

    @NotBlank(message = ("The Password cannot be empty or null"))
    private String password;

    private String token;
}
