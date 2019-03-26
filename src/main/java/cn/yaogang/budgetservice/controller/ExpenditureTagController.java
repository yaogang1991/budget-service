package cn.yaogang.budgetservice.controller;

import cn.yaogang.budgetservice.entity.ListCommon;
import cn.yaogang.budgetservice.entity.State;
import cn.yaogang.budgetservice.model.ExpenditureTag;
import cn.yaogang.budgetservice.repository.ExpenditureTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/expenditure/tag")
public class ExpenditureTagController {

    @Autowired
    private ExpenditureTagRepository tagRepository;

    @RequestMapping(value = "/expenditure/tag", method = RequestMethod.GET, produces = "application/json")
    public ListCommon<ExpenditureTag> getExpenditureTags() {
        return tagRepository.findAll2List();
    }

    @RequestMapping(value = "/expenditure/tag/{id}", method = RequestMethod.GET, produces = "application/json")
    public ExpenditureTag getExpenditureTag(@PathVariable Long id) {
        return tagRepository.findByIdOrNullable(id);
    }

    @RequestMapping(value = "/expenditure/tag", method = RequestMethod.POST, produces = "application/json")
    public State addExpenditureType(@RequestBody ExpenditureTag expenditureTag) {
        return tagRepository.saveNotExists(expenditureTag, expenditureTag.getName());
    }
}
