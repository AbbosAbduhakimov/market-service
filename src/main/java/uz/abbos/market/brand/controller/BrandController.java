package uz.abbos.market.brand.controller;

import uz.abbos.market.brand.dto.BrandDto;
import uz.abbos.market.brand.filter.BrandFilter;
import uz.abbos.market.brand.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1//brands")
@AllArgsConstructor
public class BrandController {
    private final BrandService brendService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid BrandDto brendDto){
        boolean result = brendService.create(brendDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        BrandDto result = brendService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid BrandDto brendDto){
        boolean result = brendService.update(id, brendDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = brendService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> getAll(@RequestParam("size") Integer size,
                                    @RequestParam("page") Integer page){
        List<BrandDto> result = brendService.findAllByPage(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody BrandFilter brendFilter){
        List<BrandDto> result = brendService.filter(brendFilter);
        return ResponseEntity.ok(result);
    }
}
