package uz.abbos.market.merchant.service;

import org.springframework.data.domain.Sort;
import uz.abbos.market.merchant.dto.MerchantDto;
import uz.abbos.market.exception.ProductException;
import uz.abbos.market.merchant.filter.MerchantFilter;
import uz.abbos.market.merchant.model.Merchant;
import uz.abbos.market.merchant.repository.MerchantRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;
    public boolean create(MerchantDto merchantDto) {
        Merchant merchant = new Merchant();
        merchantDto.setId(merchant.getId());
        convertDtoToEntity(merchantDto, merchant);
        merchantRepository.save(merchant);
        return true;
    }

    public MerchantDto get(Integer id) {
        Merchant merchant = getEntity(id);
        MerchantDto merchantDto = new MerchantDto();
        convertEntityToDto(merchantDto, merchant);
        return merchantDto;
    }

    public boolean update(Integer id, MerchantDto merchantDto) {
        Merchant update = getEntity(id);
        update.setName(merchantDto.getName());
        update.setUpdatedAt(LocalDateTime.now());
        update.setStatus(true);
        merchantRepository.save(update);
        return true;
    }

    public boolean delete(Integer id) {
        Merchant merchant = getEntity(id);
        merchant.setDeletedAt(LocalDateTime.now());
        merchantRepository.save(merchant);
        return true;
    }

    public List<MerchantDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Merchant> resultPage = merchantRepository.findAll(pageable);
        List<MerchantDto> response = new ArrayList<>();
        for (Merchant merchant : resultPage) {
            if (merchant.getDeletedAt() == null){
                MerchantDto dto = new MerchantDto();
                convertEntityToDto(dto, merchant);
                response.add(dto);
            }
        }
        return response;
    }

    public List<MerchantDto> filter(MerchantFilter dto) {
        String sortBy = dto.getSortBy();
        if (sortBy == null || sortBy.isEmpty()){
            sortBy = "createdAt";
        }

        List<Predicate> predicateList = new ArrayList<>();
        Specification<Merchant> specifications = (((root, query, criteriaBuilder) -> {
            if (dto.getName() != null){
                predicateList.add(criteriaBuilder.like(root.get("name"), ("%" + dto.getName() + "%")));
            }
            if (dto.getSurname() != null){
                predicateList.add(criteriaBuilder.like(root.get("surname"), ("%" + dto.getSurname() + "%")));
            }
            if (dto.getDirect() != null){
                predicateList.add(criteriaBuilder.equal(root.get("direction"), dto.getDirect()));
            }
            return criteriaBuilder.and(predicateList.toArray(new javax.persistence.criteria.Predicate[0]));
        }));

        Pageable pageable = PageRequest.of(
                dto.getPage(),
                dto.getSize(),
                dto.getDirection(),
                sortBy);


        List<MerchantDto> resultList = new ArrayList<>();
        Page<Merchant> merchantPage = merchantRepository.findAll(specifications, pageable);
        for (Merchant merchant : merchantPage) {
            if (merchant.getDeletedAt() == null){
                MerchantDto merchantDto = new MerchantDto();
                convertEntityToDto(merchantDto, merchant);
                resultList.add(merchantDto);
            }
        }
        return resultList;
    }

    public Merchant getEntity(Integer id){
        Optional<Merchant> optional = merchantRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ProductException("Merchant not found");
        }
        return optional.get();
    }

    private void convertDtoToEntity(MerchantDto dto, Merchant entity) {
        entity.setName(dto.getName());
        entity.setStatus(true);
        entity.setCreatedAt(LocalDateTime.now());
    }

    private void convertEntityToDto(MerchantDto dto, Merchant entity) {
        dto.setId(entity.getId());
        dto.setName(entity.getName());
    }
}
