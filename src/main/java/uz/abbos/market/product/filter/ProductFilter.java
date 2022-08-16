package uz.abbos.market.product.filter;

import uz.abbos.market.filter.FilterDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilter extends FilterDto {
    private String name;
    private String surname;
    private String direct;
}
