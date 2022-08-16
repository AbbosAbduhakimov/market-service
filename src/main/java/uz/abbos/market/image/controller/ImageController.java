package uz.abbos.market.image.controller;

import uz.abbos.market.image.dto.ImageDto;
import uz.abbos.market.image.filter.ImageFilter;
import uz.abbos.market.image.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {
    private ImageService imageService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ImageDto dto){
        boolean result = imageService.create(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        ImageDto result = imageService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid ImageDto dto){
        boolean result = imageService.update(id,dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = imageService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> pagenation(@RequestParam("page") Integer page,
                                        @RequestParam("size") Integer size){
        List<ImageDto> result = imageService.findAllByPage(page,size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody ImageFilter imageFilter){
        List<ImageDto> result = imageService.filter(imageFilter);
        return ResponseEntity.ok(result);
    }
}

