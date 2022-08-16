package uz.abbos.market.auth;

import uz.abbos.market.service.MessageService;
import uz.abbos.market.service.PasswordService;
import uz.abbos.market.user.repository.UserTypeRepository;
import uz.abbos.market.user.repository.UserRepository;
import uz.abbos.market.configuration.JwtTokenUtil;
import uz.abbos.market.exception.ProductException;
import uz.abbos.market.auth.dto.RegisterDto;
import uz.abbos.market.user.model.UserType;
import uz.abbos.market.user.dto.UserDto;
import uz.abbos.market.user.model.User;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private MessageService messageService;
    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;

    public String register(RegisterDto dto) {
        Optional<User> optional = userRepository.findByEmailOrContactAndDeletedAtIsNull(dto.getEmail(), dto.getContact());
        if (optional.isPresent()) {
            throw new ProductException("User already exist");
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setContact(dto.getContact());
        user.setPassword(PasswordService.generateMD5(dto.getPassword()));
        user.setStatus(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUserTypeId(2);

        UserType userType = new UserType();
        userType.setName("ROLE_USER");
        userType.setCreatedAt(LocalDateTime.now());
        userType.setStatus(true);
        userTypeRepository.save(userType);
        userRepository.save(user);

        String token = jwtTokenUtil.generateAccessToken(user.getId(), user.getEmail());
        String link = "http://localhost:8080/auth/verification/" + token;
        String content = String.format("Please click %s for verification", link);
        try {
            messageService.send(user.getEmail(), "iSystem shop uz verification", content);
        } catch (Exception e) {
            userRepository.delete(user);
            e.printStackTrace();
            return "Mail not delivered";
        }
        return "Please go to " + dto.getEmail() + " and verification your data";
    }

    public UserDto verification(String token) {
        String userName = jwtTokenUtil.getUserName(token);
        Optional<User> optional = userRepository.findByEmailAndDeletedAtIsNull(userName);
        if (optional.isEmpty()) {
            throw new ProductException("User not found");
        }
        User user = optional.get();
        user.setStatus(true);
        userRepository.save(user);
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        return dto;
    }

    public AuthDto login(AuthDto dto) {
        Optional<User> optional = userRepository
                .findByEmailAndPasswordAndDeletedAtIsNull(dto.getEmail(), PasswordService.generateMD5(dto.getPassword()));
        if (optional.isEmpty()) {
            throw new ProductException("User not found");
        }
        User user = optional.get();
        String token = jwtTokenUtil.generateAccessToken(user.getId(), user.getEmail());
        dto.setToken(token);
        return dto;
    }
}