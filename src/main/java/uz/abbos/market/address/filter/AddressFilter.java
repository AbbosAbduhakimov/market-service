package uz.abbos.market.address.filter;

import uz.abbos.market.filter.FilterDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressFilter extends FilterDto {
    private String region;
    private String city;
    private String district;
    private String street;
    private Integer home;
}
