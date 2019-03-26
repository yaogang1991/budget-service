package cn.yaogang.budgetservice.repository;

import cn.yaogang.budgetservice.model.Income;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface IncomeRepository extends CrudRepository<Income, Long> {

    Iterable<Income> findByTimeBetween(LocalDateTime start, LocalDateTime end);
}
