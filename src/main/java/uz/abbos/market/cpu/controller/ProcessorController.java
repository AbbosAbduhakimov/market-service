package uz.abbos.market.cpu.controller;

import uz.abbos.market.cpu.dto.ProcessorDto;
import uz.abbos.market.cpu.filter.ProcessorFilter;
import uz.abbos.market.cpu.service.ProcessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/processor")
@AllArgsConstructor
public class ProcessorController {

    private final ProcessorService processorService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ProcessorDto divigatelDto){
        boolean result = processorService.create(divigatelDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        ProcessorDto result = processorService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid ProcessorDto divigatelDto){
        boolean result = processorService.update(id, divigatelDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = processorService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> getAll(@RequestParam("size") Integer size,
                                    @RequestParam("page") Integer page){
        List<ProcessorDto> result = processorService.findAllByPage(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody ProcessorFilter divigatelFilter){
        List<ProcessorDto> result = processorService.filter(divigatelFilter);
        return ResponseEntity.ok(result);
    }
}
