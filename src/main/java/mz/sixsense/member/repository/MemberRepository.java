package mz.sixsense.member.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mz.sixsense.member.entity.Members;

public interface MemberRepository extends CrudRepository<Members, String>, QuerydslPredicateExecutor<Members> {

    @Query(value = " SELECT memberID FROM members WHERE memberID = :mid", nativeQuery = true)
    String checkMemberID(@Param("mid") String mid);

    @Query(value = " SELECT memberID FROM Members WHERE name = :mname AND email =:memailID ", nativeQuery = true)
    String findMemberID(@Param("mname") String mname, @Param("memailID") String memailID);

    @Query(value = " SELECT memberID FROM Members WHERE name = :mname ", nativeQuery = true)
    String findMemberID(@Param("mname") String mname);

    @Query(value = " SELECT * FROM Members WHERE memberID = :memberID ", nativeQuery = true)
    Members findMember(@Param("memberID") String memberID);

    @Query(value = " UPDATE Members set password = :tempPassword WHERE memberid = :mid AND email = :memail ", nativeQuery = true)
    String updateTempPassword(@Param("tempPassword") String tempPassword, @Param("mid") String mid, @Param("memail") String memail);

    @Query(value = " SELECT * FROM Members WHERE memberID = :mid AND email = :memail ", nativeQuery = true)
    Members findMemberPwd(@Param("mid") String mid, @Param("memail") String memail);

    @Query(value = " SELECT * FROM Members WHERE memberID = :member ", nativeQuery = true)
    Members findPassword(@Param("member") String member);
    
    Members findQRFlagBymemberID(String memberID);

}