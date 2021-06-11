package uz.dev.credit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dev.credit.model.Credit;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
