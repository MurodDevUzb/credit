package uz.dev.credit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dev.credit.model.UserInfo;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByPassportSeriesAndPassportNumber(String series,Long number);

}
