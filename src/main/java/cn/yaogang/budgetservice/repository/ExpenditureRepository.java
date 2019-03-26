package cn.yaogang.budgetservice.repository;

import cn.yaogang.budgetservice.model.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

    Iterable<Expenditure> findByTimeBetween(LocalDateTime start, LocalDateTime end);
}
