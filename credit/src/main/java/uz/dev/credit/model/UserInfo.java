package uz.dev.credit.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "userInfo",orphanRemoval = true)
    private List<Credit> credits;
}
