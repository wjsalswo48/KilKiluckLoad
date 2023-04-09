package mz.sixsense.calendar;

import mz.sixsense.calendar.entity.TodayCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodayCheckRepository extends JpaRepository<TodayCheck,Long> {

    @Query(value = " SELECT CHECK_DATE FROM TODAY_CHECK WHERE memberID = :memberid AND CHECK_DATE = :resultDate", nativeQuery = true)
    String findTodayCheck(@Param("memberid")String memberid, @Param("resultDate") String resultDate);
}
