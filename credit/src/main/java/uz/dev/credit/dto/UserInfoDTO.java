package uz.dev.credit.dto;

import uz.dev.credit.model.Credit;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

public class UserInfoDTO {


    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String passportSeries;
    private Long passportNumber;
    private Date dateOfBirth;
    private Boolean gender;
    private List<Credit> credits;


}
