package uz.abbos.market.user.service;

import lombok.NoArgsConstructor;
import uz.abbos.market.user.repository.UserTypeRepository;
import uz.abbos.market.exception.ProductException;
import uz.abbos.market.user.dto.UserTypeDto;
import uz.abbos.market.user.model.UserType;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeService {

    private UserTypeRepository userTypeRepository;

    public boolean create(UserTypeDto dto) {
        UserType userType = new UserType();
        userType.setId(dto.getId());
        userType.setName(dto.getName());
        userType.setStatus(true);
        userType.setCreatedAt(LocalDateTime.now());
        userTypeRepository.save(userType);
        return true;
    }

    public UserTypeDto get(Integer id) {
        UserType userType = getEntity(id);
        UserTypeDto userTypeDto = new UserTypeDto();
        userTypeDto.setId(userType.getId());
        userTypeDto.setName(userType.getName());
        return userTypeDto;
    }

    public boolean update(Integer id, UserTypeDto dto) {
        UserType userType = getEntity(id);
        userType.setName(dto.getName());
        userType.setUpdatedAt(LocalDateTime.now());
        userTypeRepository.save(userType);
        return true;
    }

    public boolean delete(Integer id) {
        UserType userType = getEntity(id);
        userType.setDeletedAt(LocalDateTime.now());
        userTypeRepository.save(userType);
        return true;
    }

    public UserType getEntity(Integer id) {
        Optional<UserType> optional = userTypeRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ProductException("User Type not found");
        }
        return optional.get();
    }
}
