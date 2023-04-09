package mz.sixsense.calendar.service;

import mz.sixsense.member.entity.Members;

public interface TodayCheckService {

    String insertTodayCheck(Members member, String resultDate);

    String memberTodayCheck(Members member, String resultDate);

}
