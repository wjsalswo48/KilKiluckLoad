package mz.sixsense.board.file.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mz.sixsense.board.file.entity.ReportBoardFile;

public interface ReportBoardFileRepository extends CrudRepository<ReportBoardFile, Long>
, QuerydslPredicateExecutor<ReportBoardFile>{
	//ReportBoardFile save(ReportBoardFile reportBoardFile);
	
	@Query(value = " select * from REPORT_BOARD_FILE where reportID = :reportFileID ", nativeQuery = true)
	List<ReportBoardFile> selectImageAllViewQuery(@Param("reportFileID") Long reportFileID);

//	@Query("DELETE FROM REPORT_BOARD_FILE F Where reportID = :reportFileID ")
//	@Modifying
//	@Transactional
//	void deleteFile(@Param("reportFileID") String keyword);
}

