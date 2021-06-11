package uz.dev.credit.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.dev.credit.dto.CreditDTO;
import uz.dev.credit.dto.ResponseDTO;
import uz.dev.credit.exception.UserNotFoundException;
import uz.dev.credit.model.Credit;
import uz.dev.credit.model.User;
import uz.dev.credit.model.UserInfo;
import uz.dev.credit.repository.CreditRepository;
import uz.dev.credit.repository.UserInfoRepository;
import uz.dev.credit.repository.UserRepository;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.util.Calendar;

@Service
public class CreditService {
    private final CreditRepository creditRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;

    public CreditService(CreditRepository creditRepository, UserInfoRepository userInfoRepository, UserRepository userRepository) {
        this.creditRepository = creditRepository;
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
    }

    public ResponseDTO createCredit(CreditDTO creditDTO, Principal principal){
        UserInfo userInfo = userInfoRepository.
                findByPassportSeriesAndPassportNumber(creditDTO.getPassportSeries(),
                        creditDTO.getPassportNumber()).orElseThrow(()-> new UserNotFoundException("User not found"));

        ResponseDTO responseDTO = new ResponseDTO();
        User user = getUserByPrincipal(principal);
        user.setAddress(userInfo.getAddress());
        user.setDateOfBirth(userInfo.getDateOfBirth());
        user.setGender(userInfo.getGender());
        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        user.setPassportSeries(userInfo.getPassportSeries());
        user.setPassportNumber(userInfo.getPassportNumber());
        user.setSalary(creditDTO.getSalary());
        userRepository.save(user);
        BigDecimal salary = creditDTO.getSalary().multiply(new BigDecimal(12));
        BigDecimal percent = salary.multiply(new BigDecimal(20)).divide(new BigDecimal(100));
        BigDecimal totalSalary = salary.subtract(percent);
        BigDecimal creditAmount = creditDTO.getCreditAmount();
        BigDecimal creditPercent = creditAmount.multiply(new BigDecimal(26)).divide(new BigDecimal(100));
        BigDecimal totalCredit = creditAmount.add(creditPercent);
        if(totalSalary.compareTo(totalCredit)==-1){
            responseDTO.setStatus("FAILED");
            responseDTO.setMessage("you will not be given more than " + totalSalary + " sum credits");
            return responseDTO;
        }
        Credit credit = new Credit();
        credit.setUserInfo(userInfo);
        credit.setMoney(creditDTO.getCreditAmount());
        credit.setPercent(Float.parseFloat(creditPercent.toString()));
        credit.setIssued(new Date(Calendar.getInstance().getTime().getTime()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 365);
        credit.setDue(new Date(calendar.getTime().getTime()));
        credit.setTotalMoney(totalCredit);
        creditRepository.save(credit);
        responseDTO.setStatus("SUCCESS");
        responseDTO.setMessage("You were provided with a loan for " + creditAmount + " sums");

        return responseDTO;
    }

    private User getUserByPrincipal(Principal principal){
        String username = principal.getName();

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }

}
