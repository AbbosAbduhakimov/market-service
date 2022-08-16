package uz.abbos.market.product.service;

import lombok.NoArgsConstructor;
import uz.abbos.market.brand.service.BrandService;
import uz.abbos.market.cpu.service.ProcessorService;
import uz.abbos.market.exception.OrderException;
import uz.abbos.market.exception.ProductException;
import uz.abbos.market.order.model.Order;
import uz.abbos.market.product.dto.ProductTypeDto;
import uz.abbos.market.merchant.service.MerchantService;
import uz.abbos.market.product.model.ProductType;
import uz.abbos.market.product.repository.ProductTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeService {

    private ProductTypeRepository productTypeRepository;

    private BrandService brandService;

    private MerchantService merchantService;

    private ProcessorService processorService;

    public boolean create(ProductTypeDto productTypeDto) {
        ProductType productType = new ProductType();
        brandService.getEntity(productTypeDto.getBrandId());
        productType.setBrandId(productType.getBrandId());

        merchantService.get(productTypeDto.getMerchantID());
        productType.setMerchantId(productTypeDto.getMerchantID());

        processorService.get(productTypeDto.getProcessorId());
        productType.setBrandId(productTypeDto.getProcessorId());

        productTypeDto.setId(productType.getId());

        convertDtoToEntity(productTypeDto, productType);
        productTypeRepository.save(productType);
        return true;
    }

    public ProductTypeDto get(Integer id) {
        ProductType productType = getEntity(id);
        ProductTypeDto productTypeDto = new ProductTypeDto();
        convertEntityToDto(productTypeDto, productType);
        return productTypeDto;
    }

    public boolean update(Integer id, ProductTypeDto productTypeDto) {
        ProductType update = getEntity(id);
        brandService.getEntity(update.getBrandId());
        update.setBrandId(productTypeDto.getBrandId());

        processorService.getEntity(update.getProcessorId());
        update.setProcessorId(productTypeDto.getProcessorId());

        merchantService.getEntity(update.getMerchantId());
        update.setMerchantId(productTypeDto.getMerchantID());

        update.setName(productTypeDto.getName());
        update.setUpdatedAt(LocalDateTime.now());
        return false;
    }

    public boolean delete(Integer id) {
        ProductType productType = getEntity(id);
        productType.setDeletedAt(LocalDateTime.now());
        productTypeRepository.save(productType);
        return true;
    }

    public List<ProductTypeDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<ProductType> resultPage = productTypeRepository.findAll(pageable);
        List<ProductTypeDto> response = new ArrayList<>();
        for (ProductType productType : resultPage) {
            if (productType.getDeletedAt() == null){
                ProductTypeDto dto = new ProductTypeDto();
                dto.setId(productType.getId());
                response.add(dto);
            }
        }
        return response;
    }

    private void convertDtoToEntity(ProductTypeDto productTypeDto, ProductType productType) {
        productType.setProcessorId(productTypeDto.getProcessorId());
        productType.setName(productTypeDto.getName());
        productType.setBrandId(productTypeDto.getBrandId());
        productType.setMerchantId(productTypeDto.getMerchantID());
        productType.setStatus(true);
    }

    private void convertEntityToDto(ProductTypeDto productTypeDto, ProductType productType) {
        productTypeDto.setProductTypeId(productType.getProcessorId());
        productTypeDto.setName(productType.getName());
        productTypeDto.setBrandId(productType.getBrandId());
        productTypeDto.setMerchantID(productType.getMerchantId());
        productType.setStatus(true);
    }

    private ProductType getEntity(Integer id) {
        Optional<ProductType> optional = productTypeRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            throw new ProductException("ProductType not found");
        }
        return optional.get();
    }
}
