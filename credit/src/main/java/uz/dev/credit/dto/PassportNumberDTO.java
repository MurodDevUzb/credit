package uz.dev.credit.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PassportNumberDTO {

    @Size(min = 2,max = 2,message = "Passport Series length 2")
    @NotEmpty
    private String passportSeries;
    @Size(min = 7, max = 7, message = "Passport number length 7")
    @NotEmpty
    private Long passportNumber;

}
