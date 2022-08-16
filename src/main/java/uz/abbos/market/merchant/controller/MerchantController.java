package uz.abbos.market.merchant.controller;

import uz.abbos.market.merchant.dto.MerchantDto;
import uz.abbos.market.merchant.filter.MerchantFilter;
import uz.abbos.market.merchant.service.MerchantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1//merchants")
@AllArgsConstructor
public class MerchantController {

    private final MerchantService menrchantService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid MerchantDto merchantDto){
        boolean result = menrchantService.create(merchantDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        MerchantDto result = menrchantService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid MerchantDto merchantDto){
        boolean result = menrchantService.update(id, merchantDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = menrchantService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> getAll(@RequestParam("size") Integer size,
                                    @RequestParam("page") Integer page){
        List<MerchantDto> result = menrchantService.findAllByPage(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody MerchantFilter marchantFilter){
        List<MerchantDto> result = menrchantService.filter(marchantFilter);
        return ResponseEntity.ok(result);
    }
}
