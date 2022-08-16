package uz.abbos.market.user.controller;

import uz.abbos.market.user.dto.UserRoleDto;
import uz.abbos.market.user.filter.UserRoleFilter;
import uz.abbos.market.user.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user/role")
public class UserRoleController {
    UserRoleService userRoleService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid UserRoleDto dto){
        boolean result = userRoleService.create(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        UserRoleDto result = userRoleService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UserRoleDto dto,
                                    @PathVariable("id") Integer id){
        boolean result = userRoleService.update(id,dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = userRoleService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pagenation")
    public ResponseEntity<?> pagenation(@RequestParam("page") Integer page,
                                        @RequestParam("size") Integer size){
        List<UserRoleDto> result = userRoleService.findAllByPage(page,size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody UserRoleFilter filter){
        List<UserRoleDto> result = userRoleService.filter(filter);
        return ResponseEntity.ok(result);
    }
}
