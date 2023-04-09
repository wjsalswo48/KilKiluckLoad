package mz.sixsense.road.repository;

import mz.sixsense.road.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends CrudRepository<Restaurant, String>, QuerydslPredicateExecutor<Restaurant> {
    @Query("select r from Restaurant r where r.resid =:restrantid")
    Restaurant selectRestrant(@Param("restrantid")String id);

    @Query("delete from Restaurant r where r.resid = :restrantid")
    Restaurant deleteRestaurant(@Param("restrantid")String id);
}
