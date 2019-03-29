package cn.yaogang.budgetservice;

import cn.yaogang.budgetservice.model.Expenditure;
import cn.yaogang.budgetservice.model.ExpenditureTag;
import cn.yaogang.budgetservice.model.ExpenditureType;
import cn.yaogang.budgetservice.repository.ExpenditureRepository;
import cn.yaogang.budgetservice.repository.ExpenditureTypeRepository;
import cn.yaogang.budgetservice.repository.ExpenditureTagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BudgetServiceApplicationTests {
    @Test
    public void test() {
        
    }

//    @Autowired
//    private ExpenditureTagRepository tagRepository;
//
//    @Autowired
//    private ExpenditureTypeRepository typeRepository;
//
//    @Autowired
//    private ExpenditureRepository expenditureRepository;
//
//    @Test
//    public void testTag() {
//        tagRepository.save(new ExpenditureTag("AAA"));
//        tagRepository.save(new ExpenditureTag("BBB"));
//
//        System.out.println("tags:" + tagRepository.findAll());
//        System.out.println("count:" + tagRepository.count());
//    }
//
//    @Test
//    public void testExpenditureType() {
//        typeRepository.save(new ExpenditureType("QQQ"));
//        typeRepository.save(new ExpenditureType("WWW"));
//        typeRepository.save(new ExpenditureType("EEE"));
//
//        System.out.println("type:" + typeRepository.findAll());
//        System.out.println("count:" + typeRepository.count());
//    }
//
//    @Test
//    public void testExpenditrue() {
//        Expenditure expenditure = new Expenditure();
//        expenditure.setExpenditureType(typeRepository.findByName("QQQ").get());
//        expenditure.setValue(15.0);
//        Set<ExpenditureTag> tags = new HashSet<>();
//        for(ExpenditureTag tag : tagRepository.findAll()) {
//            tags.add(tag);
//        }
//        expenditure.setTags(tags);
//        expenditure.setTime(LocalDateTime.now());
//        System.out.println("expenditure = " + expenditure);
//        expenditureRepository.save(expenditure);
////        List<Expenditure> list = expenditureRepository.findAll();
////        for (Expenditure e : list) {
////            System.out.println("e.value = " + e.getValue());
////            System.out.println("e.type = " + e.getExpenditureType());
////            System.out.println(e.getTags().size());
////        }
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//        LocalDateTime start = LocalDateTime.parse("20190325000000", df);
//        LocalDateTime end = LocalDateTime.parse("20190326000000", df);
//        Iterable<Expenditure> list = expenditureRepository.findByTimeBetween(start, end);
//        list.forEach(e ->System.out.println(e));
//    }
}
