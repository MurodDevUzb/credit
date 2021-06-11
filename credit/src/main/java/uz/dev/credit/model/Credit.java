package uz.dev.credit.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date issued;
    private Date due;
    @Digits(integer=12, fraction=4)
    private BigDecimal money;
    private float percent;
    @Digits(integer=15, fraction=5)
    private BigDecimal totalMoney;
    @ManyToOne(fetch = FetchType.EAGER)
    UserInfo userInfo;

}
