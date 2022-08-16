package uz.abbos.market.cpu.repository;

import org.springframework.stereotype.Repository;
import uz.abbos.market.cpu.model.Processor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
@Repository
public interface ProcessorRepository extends JpaRepository<Processor,Integer>, JpaSpecificationExecutor<Processor> {
    Optional<Processor> findByIdAndDeletedAtIsNull(Integer id);
}
