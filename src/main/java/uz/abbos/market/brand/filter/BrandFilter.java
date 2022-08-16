package uz.abbos.market.brand.filter;

import uz.abbos.market.filter.FilterDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandFilter extends FilterDto {

    private String name;
    private String surname;
    private String direct;
}
