package cn.yaogang.budgetservice.repository;

import cn.yaogang.budgetservice.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Iterable<Transaction> findByIdGreaterThan(Long id);
}
