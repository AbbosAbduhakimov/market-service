package uz.abbos.market.cpu.filter;

import uz.abbos.market.filter.FilterDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessorFilter extends FilterDto {
    private String name;
    private String surname;
    private String direct;
}
