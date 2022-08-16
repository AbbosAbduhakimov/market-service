package uz.abbos.market.cpu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessorDto {
    private Integer id;

   @NotNull(message = ("The size cannot be empty or null"))
   private Double size;
}
