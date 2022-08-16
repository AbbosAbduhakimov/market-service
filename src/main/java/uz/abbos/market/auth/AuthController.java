package uz.abbos.market.auth;

import uz.abbos.market.auth.dto.RegisterDto;
import uz.abbos.market.user.dto.UserDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto dto){
        String result = authService.register(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDto dto){
        AuthDto result = authService.login(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/verification/{token}")
    public ResponseEntity<?> verification(@PathVariable("token") String token){
        UserDto result = authService.verification(token);
        return ResponseEntity.ok(result);
    }
}
