package uz.abbos.market.user.filter;

import uz.abbos.market.filter.FilterDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleFilter extends FilterDto {
    private String name;
}
