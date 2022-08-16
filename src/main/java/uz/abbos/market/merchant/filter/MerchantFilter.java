package uz.abbos.market.merchant.filter;

import uz.abbos.market.filter.FilterDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantFilter extends FilterDto {
    private String name;
    private String surname;
    private String direct;
}
