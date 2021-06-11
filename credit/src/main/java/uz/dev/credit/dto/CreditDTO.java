package uz.dev.credit.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class CreditDTO {
    @Size(min = 2,max = 2,message = "Passport Series length 2")
    @NotEmpty
    private String passportSeries;
    @Digits(integer = 7,fraction = 0)
    @NotNull
    private Long passportNumber;
    @NotNull
    private BigDecimal salary;
    @NotNull
    private BigDecimal creditAmount;
}
