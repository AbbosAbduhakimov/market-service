package uz.abbos.market.product.model;

import lombok.Getter;
import lombok.Setter;
import uz.abbos.market.brand.model.Brand;
import uz.abbos.market.cpu.model.Processor;
import uz.abbos.market.merchant.model.Merchant;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("productTypes"))
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("merchant"), insertable = false, updatable = false)
    private Merchant merchant;

    @Column(name = ("merchant_id"))
    private Integer merchantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("brand"), insertable = false, updatable = false)
    private Brand brand;

    @Column(name = ("brand_id"))
    private Integer brandId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("processor"), insertable = false, updatable = false)
    private Processor processor;

    @Column(name = ("processor_id"))
    private Integer processorId;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
