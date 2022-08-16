package uz.abbos.market.user.model;

import lombok.Getter;
import lombok.Setter;
import uz.abbos.market.address.model.Address;
import uz.abbos.market.image.model.Image;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("users"))
public class  User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private String email;

    private String contact;

    private String password;

    private Boolean status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("image"),insertable = false,updatable = false)
    private Image image;

    @Column(name = ("image_id"))
    private Integer imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("user_role"),insertable = false,updatable = false)
    private UserRole userRole;
    @Column(name = ("user_role_id"))
    private Integer userRoleId;

    @OneToOne
    @JoinColumn(name = ("address"),insertable = false,updatable = false)
    private Address address;
    @Column(name = ("address_id"))
    private Integer addressId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("user_type"),insertable = false,updatable = false)
    private UserType userType;
    @Column(name = ("user_id"))
    private Integer userTypeId;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;

    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;

    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
