package cn.yaogang.budgetservice.cache;

import cn.yaogang.budgetservice.entity.Own;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataCache {
    private Map<LocalDate, List<Own>> ownsCache = new HashMap<>();
    private Map<LocalDate, Long> idCache = new HashMap<>();

    @Cacheable(value = "own", key = "#date + 'Owns'")
    public List<Own> queryOwns(LocalDate date) {
        return ownsCache.get(date);
    }

    @CachePut(value = "own", key = "#date + 'Owns'")
    public List<Own> putOwns(LocalDate date, List<Own> owns) {
        ownsCache.put(date, owns);
        return owns;
    }

    @CacheEvict
    public void removeOwns(LocalDate date) {
        ownsCache.remove(date);
    }

    @Cacheable(value = "own", key = "#date + 'Id'")
    public Long queryId(LocalDate date) {
        return idCache.get(date);
    }

    @CachePut(value = "own", key = "#date + 'Id'")
    public Long put(LocalDate date, Long id) {
        idCache.put(date, id);
        return id;
    }

    @CacheEvict
    public void remove(LocalDate date) {
        idCache.remove(date);
    }
}
