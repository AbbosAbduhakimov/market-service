package uz.abbos.market.order.model;

import uz.abbos.market.user.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("orders"))
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String requirement;

    private String contact;

    private String address;
    @Column(name = ("total_payment"))
    private BigDecimal totalPayment;

    private boolean status;

    @Column(name = ("delivery_date"))
    private LocalDateTime deliveryDate;

    @Column(name = ("delivered_date"))
    private LocalDateTime deliveredDate;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;

    @Column(name = ("update_at"))
    private LocalDateTime updateAt;

    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =("users"), insertable = false,updatable = false)
    private User user;
    @Column(name = ("users_id"))
    private Integer userId;






}

