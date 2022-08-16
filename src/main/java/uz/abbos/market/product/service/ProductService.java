package uz.abbos.market.product.service;

import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import uz.abbos.market.product.dto.ProductDto;
import uz.abbos.market.exception.ProductException;
import uz.abbos.market.product.filter.ProductFilter;
import uz.abbos.market.product.model.Product;
import uz.abbos.market.product.repository.ProductRepository;
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
public class ProductService {
    private final ProductRepository productRepository;

    public boolean create(ProductDto productDto) {
        Product product = new Product();
        productDto.setId(productDto.getId());
        convertDtoToEntity(productDto, product);
        productRepository.save(product);
        return true;
    }

    public ProductDto get(Integer id) {
        Product product = getEntity(id);
        ProductDto productDto = new ProductDto();
        convertEntityToDto(productDto, product);
        return productDto;
    }

    public boolean update(Integer id, ProductDto productDto) {
        Product update = getEntity(id);
        update.setName(productDto.getName());
        update.setDescription(productDto.getDescription());
        update.setPrice(productDto.getPrice());
        update.setRate(productDto.getRate());
        update.setProductTypeId(productDto.getProductType());
        update.setVisible(true);
        update.setStatus(true);
        update.setUpdatedAt(LocalDateTime.now());
        productRepository.save(update);
        return true;
    }

    public boolean delete(Integer id) {
        Product product = getEntity(id);
        product.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
        return true;
    }

    public List<ProductDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> resultPage = productRepository.findAll(pageable);
        List<ProductDto> response = new ArrayList<>();
        for (Product product : resultPage) {
            if (product.getDeletedAt() == null){
                ProductDto dto = new ProductDto();
                convertEntityToDto(dto, product);
                response.add(dto);
            }
        }
        return response;
    }

    public List<ProductDto> filter(ProductFilter dto) {
        String sortBy = dto.getSortBy();
        if (sortBy == null || sortBy.isEmpty()){
            sortBy = "createdAt";
        }

        List<Predicate> predicateList = new ArrayList<>();
        Specification<Product> specifications = (((root, query, criteriaBuilder) -> {
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

        List<ProductDto> resultList = new ArrayList<>();
        Page<Product> productPage = productRepository.findAll(specifications, pageable);
        for (Product product : productPage) {
            if (product.getDeletedAt() == null){
                ProductDto productDto = new ProductDto();
                convertEntityToDto(productDto, product);
                resultList.add(productDto);
            }
        }
        return resultList;
    }



    private void convertDtoToEntity(ProductDto dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setRate(dto.getRate());
        entity.setPrice(dto.getPrice());
        entity.setProductTypeId(dto.getProductType());
        entity.setVisible(true);
        entity.setStatus(true);
        entity.setCreatedAt(LocalDateTime.now());
    }

    private void convertEntityToDto(ProductDto dto, Product entity) {
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setProductType(entity.getProductTypeId());
        dto.setPrice(entity.getPrice());
        dto.setRate(entity.getRate());
    }

    private Product getEntity(Integer id) {
        Optional<Product> optional = productRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ProductException("Product not found");
        }
        return optional.get();
    }
}
