package cn.yaogang.budgetservice.controller;

import cn.yaogang.budgetservice.entity.ListCommon;
import cn.yaogang.budgetservice.model.Transaction;
import cn.yaogang.budgetservice.repository.TransactionRepository;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;

@RestController("/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping(value = "/transaction")
    public ListCommon<Transaction> getTransactions() {
        return new ListCommon<>(Lists.newArrayList(transactionRepository.findAll()));
    }

    @PostMapping(value = "/transaction")
    public Transaction addTransaction(@RequestBody Transaction transaction) throws IOException, ParseException {
        String url = "http://fund.eastmoney.com/" + transaction.getCode() + ".html";
        Document document = Jsoup.connect(url).get();
        double value =Double.parseDouble(document.select("dl.dataItem02").first().select("dd").select("span").first().text());
        double tax = NumberFormat.getPercentInstance().parse(document.select("span.nowPrice").first().text()).doubleValue();
        String amount = String.format("%.2f", transaction.getTotal() * (1 - tax) / value);
        if (transaction.getDate() == null) {
            transaction.setDate(LocalDate.now());
        }
        if (transaction.getValue() == null) {
            transaction.setValue(value);
        }
        if (transaction.getAmount() == null) {
            transaction.setAmount(Double.parseDouble(amount));
        }
        transactionRepository.save(transaction);
        return transaction;
    }
}
