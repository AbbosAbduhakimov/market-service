package uz.abbos.market.auth;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorizationDto {
    private String userName;
    private String password;
}
