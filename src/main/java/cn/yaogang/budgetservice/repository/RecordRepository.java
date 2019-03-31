package cn.yaogang.budgetservice.repository;

import cn.yaogang.budgetservice.model.Record;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;

public interface RecordRepository extends CrudRepository<Record, Id> {
}
