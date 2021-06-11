package uz.dev.credit.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(length = 2)
    private String passportSeries;
    @Column(length = 7)
    private Long passportNumber;
    private Date dateOfBirth;
    private Boolean gender;
    @Digits(integer=12, fraction=4)
    private BigDecimal salary;

}
