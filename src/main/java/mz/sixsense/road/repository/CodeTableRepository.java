package mz.sixsense.road.repository;

import mz.sixsense.road.entity.CodeTable;
import mz.sixsense.road.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CodeTableRepository extends CrudRepository<CodeTable, String> , QuerydslPredicateExecutor<CodeTable> {
    @Query("select c from CodeTable c where c.CodeName =:codename")
    CodeTable searchNameCodeTable(String codename);
}
