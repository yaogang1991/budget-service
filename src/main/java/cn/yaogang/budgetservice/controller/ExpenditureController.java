package cn.yaogang.budgetservice.controller;

import cn.yaogang.budgetservice.entity.ListCommon;
import cn.yaogang.budgetservice.entity.State;
import cn.yaogang.budgetservice.model.Expenditure;
import cn.yaogang.budgetservice.repository.ExpenditureRepository;
import cn.yaogang.budgetservice.utils.DateUtil;
import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/expenditure")
public class ExpenditureController {

    @Autowired
    private ExpenditureRepository expenditureRepository;

    @GetMapping(value = "/expenditure", produces = "application/json")
    public ListCommon<Expenditure> getExpenditure(String startTime, String endTime) {
        if (startTime.length() != endTime.length() && startTime.length() != 14) {
            throw new RuntimeException("args have exception!");
        }
        return new ListCommon<>(Lists.newArrayList(expenditureRepository.findByTimeBetween(DateUtil.parseLocalDateTime(startTime), DateUtil.parseLocalDateTime(endTime))));
    }

    @PostMapping(value = "/expenditure")
    public State addExpenditure(@RequestBody Expenditure expenditure) {
        try {
            expenditureRepository.save(expenditure);
        } catch (Exception e) {
            return new State(e);
        }
        return new State();
    }


    @PutMapping(value = "/expenditure/{id}")
    public State updateExpenditure(@PathVariable Long id, @RequestBody Expenditure expenditure) {
        if (!expenditureRepository.existsById(id)) {
            return new State("is not exsits!");
        }
        try {
            expenditure.setId(id);
            expenditureRepository.save(expenditure);
        } catch (Exception e) {
            return new State(e);
        }
        return new State();
    }

    @DeleteMapping(value = "/expenditure/{id}")
    public State deleteExpenditure(@PathVariable Long id) {
        try {
            expenditureRepository.deleteById(id);
        } catch (Exception e) {
            return new State(e);
        }
        return new State();
    }
}
