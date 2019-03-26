package cn.yaogang.budgetservice.repository;

import cn.yaogang.budgetservice.entity.ListCommon;
import cn.yaogang.budgetservice.entity.State;
import cn.yaogang.budgetservice.model.Model;
import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface ExpenditureCrudRepository<T extends Model> extends CrudRepository<T, Long> {

    Optional<T> findByName(String name);

    default ListCommon<T> findAll2List() {
        return new ListCommon<>(Lists.newArrayList(findAll()));
    }

    default T findByIdOrNullable(Long id) {
        if (!existsById(id)) {
            return null;
        }
        return findById(id).get();
    }

    default State saveNotExists(T model, String name) {
        if (findByName(name).isPresent()) {
            return new State(name + " is exsits!");
        }
        save(model);
        return new State();
    }
}
