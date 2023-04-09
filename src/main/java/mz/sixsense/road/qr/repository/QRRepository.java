package mz.sixsense.road.qr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mz.sixsense.road.entity.Road;


public interface QRRepository extends JpaRepository<Road, String>, QuerydslPredicateExecutor<Road>{

@Query("SELECT r.start_lat, r.start_long, r.end_lat, r.end_long FROM Road r where guganNm=:gugan")
String getGugan(@Param("gugan") String gugan);

//@Query("SELECT r.gmCourse, r.start_lat, r.start_long FROM Road r where guganNm=:gugan")
//String getText(@Param("gugan") String gugan);

List<Road> findAllByguganNm(String guganNm);

}
