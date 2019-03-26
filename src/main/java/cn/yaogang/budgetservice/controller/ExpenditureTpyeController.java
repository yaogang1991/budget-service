package cn.yaogang.budgetservice.controller;

import cn.yaogang.budgetservice.entity.ListCommon;
import cn.yaogang.budgetservice.entity.State;
import cn.yaogang.budgetservice.model.ExpenditureType;
import cn.yaogang.budgetservice.repository.ExpenditureTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/expenditure/type")
public class ExpenditureTpyeController {
    @Autowired
    private ExpenditureTypeRepository typeRepository;

    @RequestMapping(value = "/expenditure/type", method = RequestMethod.GET, produces = "application/json")
    public ListCommon getExpenditures() {
        return typeRepository.findAll2List();
    }

    @RequestMapping(value = "/expenditure/type/{id}", method = RequestMethod.GET, produces = "application/json")
    public ExpenditureType getExpenditure(@PathVariable Long id) {
        return typeRepository.findByIdOrNullable(id);
    }

    @RequestMapping(value = "/expenditure/type", method = RequestMethod.POST, produces = "application/json")
    public State addExpenditureType(@RequestBody ExpenditureType expenditureType) {
        return typeRepository.saveNotExists(expenditureType, expenditureType.getName());
    }

    @RequestMapping(value = "/expenditure/type/{id}", method = RequestMethod.PUT, produces = "application/json")
    public State updateExpenditureType(@PathVariable Long id, @RequestBody ExpenditureType expenditureType) {
        try {
            if (!typeRepository.findById(id).isPresent()) {
                return new State("is not exsits!");
            }
            if (typeRepository.findByName(expenditureType.getName()).isPresent()) {
                return new State(expenditureType.getName() + " is exsits!");
            }
            expenditureType.setId(id);
            typeRepository.save(expenditureType);
        } catch (Exception e) {
            return new State(e);
        }
        return new State();
    }
}
