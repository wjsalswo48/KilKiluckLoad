package mz.sixsense.calendar.entity;

import lombok.*;
import mz.sixsense.member.entity.Members;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"member"})
public class TodayCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long todayCheckID;

    private String checkDate;

    @ManyToOne
    @JoinColumn(name = "memberID")
    private Members memberID;

    public void setMember(Members members) {
        this.memberID = members;
        members.getTodayCheckList().add(this);
    }

}
