package cn.yaogang.budgetservice.controller;

import cn.yaogang.budgetservice.entity.ListCommon;
import cn.yaogang.budgetservice.entity.State;
import cn.yaogang.budgetservice.model.Income;
import cn.yaogang.budgetservice.repository.IncomeRepository;
import cn.yaogang.budgetservice.utils.DateUtil;
import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/income")
public class IncomeController {
    @Autowired
    private IncomeRepository incomeRepository;

    @GetMapping(value = "/income")
    public ListCommon<Income> getIncome(String startTime, String endTime) {
        if (startTime.length() != endTime.length() && startTime.length() != 14) {
            throw new RuntimeException("args have exception!");
        }
        return new ListCommon<>(Lists.newArrayList(incomeRepository.findByTimeBetween(DateUtil.parseLocalDateTime(startTime), DateUtil.parseLocalDateTime(endTime))));
    }

    @PutMapping(value = "/income")
    public State addIncome(@RequestBody Income income) {
        try {
            incomeRepository.save(income);
        } catch (Exception e) {
            return new State(e);
        }
        return new State();
    }

    @PutMapping(value = "/income/{id}")
    public State updateExpenditure(@PathVariable Long id, @RequestBody Income income) {
        if (!incomeRepository.existsById(id)) {
            return new State("is not exsits!");
        }
        try {
            income.setId(id);
            incomeRepository.save(income);
        } catch (Exception e) {
            return new State(e);
        }
        return new State();
    }

    @DeleteMapping(value = "/income/{id}")
    public State deleteExpenditure(@PathVariable Long id) {
        try {
            incomeRepository.deleteById(id);
        } catch (Exception e) {
            return new State(e);
        }
        return new State();
    }
}
