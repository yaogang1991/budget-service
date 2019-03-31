package cn.yaogang.budgetservice.schedule;

import cn.yaogang.budgetservice.cache.DataCache;
import cn.yaogang.budgetservice.entity.Own;
import cn.yaogang.budgetservice.model.Record;
import cn.yaogang.budgetservice.model.Transaction;
import cn.yaogang.budgetservice.repository.RecordRepository;
import cn.yaogang.budgetservice.repository.TransactionRepository;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Component
public class WorkDaySchedule {
    private final static String URL = "http://fund.eastmoney.com/";
    private final static String HTML = ".html";

    @Autowired
    private DataCache cache;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Scheduled(cron = "0 23 * * 1-5")
    public void record() throws IOException, ParseException {
        System.out.println("Scheduled record");
        List<Own> owns = new ArrayList<>();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate today = LocalDate.now();
        Iterable<Transaction> transactionIterable;
        if (cache.queryId(yesterday) == null) {
            System.out.println("cache id null");
            transactionIterable = transactionRepository.findAll();
        } else {
            Long id = cache.queryId(yesterday);
            System.out.println("cache id = " + id);
            transactionIterable = transactionRepository.findByIdGreaterThan(id);
        }

        if (cache.queryOwns(yesterday) != null) {
            owns = cache.queryOwns(yesterday);
            System.out.println("cache owns = " + owns);
        }

        Iterator<Transaction> transactionIterator = transactionIterable.iterator();
        while (transactionIterator.hasNext()) {
            Transaction transaction = transactionIterator.next();
            ListIterator<Own> iterator;
            switch (transaction.getOperation()) {
                case ADD:
                    iterator = owns.listIterator();
                    boolean contains = false;
                    while (iterator.hasNext()) {
                        Own elder = iterator.next();
                        if (elder.getCode().equals(transaction.getCode())) {
                            contains = true;
                            elder.setPrincipal(elder.getPrincipal() + transaction.getTotal());
                            elder.setAmount(elder.getAmount() + transaction.getAmount());
                            iterator.set(elder);
                            break;
                        }
                    }

                    if (!contains) {
                        Own own = new Own();
                        own.setCode(transaction.getCode());
                        own.setPrincipal(transaction.getTotal());
                        own.setAmount(transaction.getAmount());
                        owns.add(own);
                    }
                    break;
                case REMOVE:
                    iterator = owns.listIterator();
                    while (iterator.hasNext()) {
                        Own elder = iterator.next();
                        if (elder.getCode().equals(transaction.getCode())) {
                            if (elder.getAmount() == transaction.getAmount()) {
                                iterator.remove();
                            } else {
                                elder.setPrincipal(Double.parseDouble(String.format("%.2f", elder.getPrincipal() * ((elder.getAmount() - transaction.getAmount()) / elder.getAmount()))));
                                elder.setAmount(elder.getAmount() - transaction.getAmount());
                                iterator.set(elder);
                            }
                            break;
                        }
                    }
                    break;
            }
            if (!transactionIterator.hasNext()) {
                cache.put(today, transaction.getId());
            }
        }

        ListIterator<Own> listIterator = owns.listIterator();
        while (listIterator.hasNext()) {
            Own own = listIterator.next();
            Record record = getRecord(own.getCode());
            own.setTotal(Double.parseDouble(String.format("%.2f", record.getValue() * own.getAmount())));
            own.setRate(Double.parseDouble(String.format("%.2f", (own.getTotal() - own.getPrincipal()) / own.getPrincipal())));
            listIterator.set(own);
            recordRepository.save(record);
        }
        cache.putOwns(today, owns);
    }

    private Record getRecord(String code) throws IOException, ParseException {
        Record record = new Record();
        Document document = Jsoup.connect(URL + code + HTML).get();
        List<Element> list = Lists.newArrayList(document.select("dl.dataItem02").first().select("dd").select("span"));
        record.setValue(Double.parseDouble(list.get(0).text()));
        record.setRate(NumberFormat.getPercentInstance().parse(list.get(1).text()).doubleValue());
        return record;
    }
}
