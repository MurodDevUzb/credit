package uz.dev.credit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.dev.credit.dto.CreditDTO;
import uz.dev.credit.dto.PassportNumberDTO;
import uz.dev.credit.dto.ResponseDTO;
import uz.dev.credit.exception.UserNotFoundException;
import uz.dev.credit.model.UserInfo;
import uz.dev.credit.repository.UserInfoRepository;
import uz.dev.credit.security.ResponseErrorValidation;
import uz.dev.credit.service.CreditService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/credit")
public class RestController {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private CreditService creditService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('developer:read')")
    public String index(){
        return "Hello";
    }

    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('developer:read')")
    public ResponseEntity<UserInfo> getUserInfo(@RequestBody PassportNumberDTO passportNumberDTO){
        UserInfo userInfo = userInfoRepository
                .findByPassportSeriesAndPassportNumber(passportNumberDTO.getPassportSeries()
                        ,passportNumberDTO.getPassportNumber()).orElseThrow(
                        ()-> new UserNotFoundException("User not found")
                );
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @PostMapping("/create_credit")
    @PreAuthorize("hasAnyAuthority('developer:read')")
    public ResponseEntity<Object> createCredit(@Valid @RequestBody CreditDTO creditDTO, BindingResult bindingResult, Principal principal){

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        ResponseDTO responseDTO = creditService.createCredit(creditDTO,principal);


        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
