package uz.abbos.market.configuration;

import uz.abbos.market.exception.ProductException;
import uz.abbos.market.user.model.User;
import uz.abbos.market.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


//Menagment (foydalanuchilar boshqaruvi) uchun javob beradigan class
@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
   private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> optional = userRepository.findByEmailAndDeletedAtIsNull(username);
       if (optional.isEmpty()){
           throw new ProductException("User not found");
       }
       User user = optional.get();
       return new CustomUserDetails(user);
    }
}
