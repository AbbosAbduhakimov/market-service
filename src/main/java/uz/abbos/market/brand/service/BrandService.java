package uz.abbos.market.brand.service;

import org.springframework.data.domain.Sort;
import uz.abbos.market.brand.dto.BrandDto;
import uz.abbos.market.exception.ProductException;
import uz.abbos.market.brand.filter.BrandFilter;
import uz.abbos.market.brand.model.Brand;
import uz.abbos.market.brand.repository.BrendRepository;
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
public class BrandService {

    private BrendRepository brendRepository;
    public boolean create(BrandDto brandDto) {
        Brand brand = new Brand();
        brandDto.setId(brand.getId());
        convertDtoToEntity(brandDto, brand);
        brendRepository.save(brand);
        return true;
    }

    public BrandDto get(Integer id) {
        Brand brand = getEntity(id);
        BrandDto brandDto = new BrandDto();
        convertEntityToDto(brandDto, brand);
        return brandDto;
    }

    public boolean update(Integer id, BrandDto brandDto) {
        Brand update = getEntity(id);
        update.setName(brandDto.getName());
        update.setStatus(true);
        update.setUpdatedAt(LocalDateTime.now());
        brendRepository.save(update);
        return true;
    }

    public boolean delete(Integer id) {
        Brand brand = getEntity(id);
        brand.setDeletedAt(LocalDateTime.now());
        brendRepository.save(brand);
        return true;
    }

    public List<BrandDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Brand> resultPage = brendRepository.findAll(pageable);
        List<BrandDto> response = new ArrayList<>();
        for (Brand brand : resultPage) {
            if (brand.getDeletedAt() == null){
                BrandDto dto = new BrandDto();
                convertEntityToDto(dto, brand);
                response.add(dto);
            }
        }
        return response;
    }

    public List<BrandDto> filter(BrandFilter brandFilter) {
        String sortBy = brandFilter.getSortBy();
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "createdAt";
        }

        List<Predicate> predicateList = new ArrayList<>();
        Specification<Brand> specifications = (((root, query, criteriaBuilder) -> {
            if (brandFilter.getName() != null) {
                predicateList.add(criteriaBuilder.like(root.get("name"), ("%" + brandFilter.getName() + "%")));
            }
            if (brandFilter.getSurname() != null) {
                predicateList.add(criteriaBuilder.like(root.get("surname"), ("%" + brandFilter.getSurname() + "%")));
            }
            if (brandFilter.getDirect() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("direction"), brandFilter.getDirect()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        }));

        Pageable pageable = PageRequest.of(
                brandFilter.getSize(),
                brandFilter.getPage(),
                brandFilter.getDirection(),
                sortBy);

        List<BrandDto> resultList = new ArrayList<>();
        Page<Brand> brandPage = brendRepository.findAll(specifications, pageable);
        for (Brand brand : brandPage) {
            if (brand.getDeletedAt() == null) {
                BrandDto brandDto = new BrandDto();
                convertEntityToDto(brandDto, brand);
                resultList.add(brandDto);
            }
        }
        return resultList;
    }

    public Brand getEntity(Integer id) {
        Optional<Brand> optional = brendRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ProductException("Brand not found");
        }
        return optional.get();
    }

    private void convertDtoToEntity(BrandDto dto, Brand entity) {
        entity.setName(dto.getName());
        entity.setStatus(true);
        entity.setCreatedAt(LocalDateTime.now());
    }

    private void convertEntityToDto(BrandDto dto, Brand entity) {
        dto.setId(entity.getId());
        dto.setName(entity.getName());
    }
}
