package mz.sixsense.board.file.repository;

import mz.sixsense.board.file.entity.FreeBoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FreeBoardFileRepository extends JpaRepository<FreeBoardFile, Long> {

    @Query(value = " select * from FREE_BOARD_FILE where freeBID = :fileID ", nativeQuery = true)
    List<FreeBoardFile> selectImageAllViewQuery(@Param("fileID") Long fileID);
}