package uz.abbos.market.cpu.service;

import org.springframework.data.domain.Sort;
import uz.abbos.market.cpu.dto.ProcessorDto;
import uz.abbos.market.exception.ProductException;
import uz.abbos.market.cpu.filter.ProcessorFilter;
import uz.abbos.market.cpu.model.Processor;
import uz.abbos.market.cpu.repository.ProcessorRepository;
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
public class ProcessorService {
    private final ProcessorRepository processorRepository;

    public boolean create(ProcessorDto processorDto) {
        Processor processor = new Processor();
        processorDto.setId(processor.getId());
        convertDtoToEntity(processorDto, processor);
        processorRepository.save(processor);
        return true;
    }

    public ProcessorDto get(Integer id) {
        Processor processor = getEntity(id);
        ProcessorDto processorDto = new ProcessorDto();
        convertEntityToDto(processorDto, processor);
        return processorDto;
    }

    public boolean update(Integer id, ProcessorDto divigatelDto) {
        Processor update = getEntity(id);
        update.setSize(divigatelDto.getSize());
        update.setStatus(true);
        update.setUpdatedAt(LocalDateTime.now());
        processorRepository.save(update);
        return true;
    }

    public boolean delete(Integer id) {
        Processor processor = getEntity(id);
        processor.setDeletedAt(LocalDateTime.now());
        processorRepository.save(processor);
        return true;
    }

    public List<ProcessorDto> findAllByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Processor> resultPage = processorRepository.findAll(pageable);
        List<ProcessorDto> response = new ArrayList<>();
        for (Processor processor : resultPage) {
            if (processor.getDeletedAt() == null){
                ProcessorDto processorDto = new ProcessorDto();
                convertEntityToDto(processorDto, processor);
                response.add(processorDto);
            }
        }
        return response;
    }

    public List<ProcessorDto> filter(ProcessorFilter processorFilter) {
       String sortBy = processorFilter.getSortBy();
       if (sortBy == null || sortBy.isEmpty()){
           sortBy = "createdAt";
       }

        List<Predicate> predicateList = new ArrayList<>();
        Specification<Processor> specifications = (((root, query, criteriaBuilder) -> {
            if (processorFilter.getName() != null) {
                predicateList.add(criteriaBuilder.like(root.get("name"), ("%" + processorFilter.getName() + "%")));
            }
            if (processorFilter.getSurname() != null) {
                predicateList.add(criteriaBuilder.like(root.get("surname"), ("%" + processorFilter.getSurname() + "%")));
            }
            if (processorFilter.getDirect() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("direction"), processorFilter.getDirection()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        }));

        Pageable pageable = PageRequest.of(
                processorFilter.getPage(),
                processorFilter.getSize(),
                processorFilter.getDirection(),
                sortBy);

        List<ProcessorDto> resultList = new ArrayList<>();
        Page<Processor> processorPage = processorRepository.findAll(specifications, pageable);
        for (Processor processor : processorPage) {
            if (processor.getDeletedAt() == null) {
                ProcessorDto processorDto = new ProcessorDto();
                convertEntityToDto(processorDto, processor);
                resultList.add(processorDto);
            }
        }
        return resultList;
    }

    public Processor getEntity(Integer id) {
        Optional<Processor> optional = processorRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ProductException("Processor not found");
        }
        return optional.get();
    }

    private void convertDtoToEntity(ProcessorDto processorDto, Processor entity) {
        entity.setSize(processorDto.getSize());
        entity.setStatus(true);
        entity.setCreatedAt(LocalDateTime.now());
    }

    private void convertEntityToDto(ProcessorDto processorDto, Processor entity) {
        processorDto.setId(entity.getId());
        processorDto.setSize(entity.getSize());
    }
}
