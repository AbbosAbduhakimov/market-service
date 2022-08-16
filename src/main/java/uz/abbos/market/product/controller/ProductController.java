package uz.abbos.market.product.controller;

import uz.abbos.market.product.dto.ProductDto;
import uz.abbos.market.product.filter.ProductFilter;
import uz.abbos.market.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ProductDto productDto){
        boolean result = productService.create(productDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        ProductDto result = productService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid ProductDto productDto){
        boolean result = productService.update(id, productDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = productService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> getAll(@RequestParam("size") Integer size,
                                    @RequestParam("page") Integer page){
        List<ProductDto> result = productService.findAllByPage(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody ProductFilter productFilter){
        List<ProductDto> result = productService.filter(productFilter);
        return ResponseEntity.ok(result);
    }
}
