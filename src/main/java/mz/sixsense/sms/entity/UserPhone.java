package mz.sixsense.sms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
public class UserPhone {

    @Id
    @GeneratedValue
    @Column(name = "member_seq")
    private Long seq;

    private String memberPhone;

}
