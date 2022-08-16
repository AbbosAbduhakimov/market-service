package uz.abbos.market.image.filter;

import uz.abbos.market.filter.FilterDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageFilter extends FilterDto {
    private String path;
    private String type;
    private Long imageSize;
    private String token;
}
