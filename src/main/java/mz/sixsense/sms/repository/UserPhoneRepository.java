package mz.sixsense.sms.repository;

import mz.sixsense.sms.entity.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserPhoneRepository extends CrudRepository<UserPhone, Long> {

    @Query(value = " SELECT MEMBER_PHONE  FROM USER_PHONE WHERE MEMBER_PHONE = :getPhone ", nativeQuery = true)
    String findUserPhone(@Param("getPhone") String getPhone);

}
