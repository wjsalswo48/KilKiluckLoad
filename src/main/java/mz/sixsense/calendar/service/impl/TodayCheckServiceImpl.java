package mz.sixsense.calendar.service.impl;

import mz.sixsense.calendar.TodayCheckRepository;
import mz.sixsense.calendar.entity.TodayCheck;
import mz.sixsense.calendar.service.TodayCheckService;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TodayCheckServiceImpl implements TodayCheckService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TodayCheckRepository todayCheckRepository;
    @Override
    public String insertTodayCheck(Members member, String resultDate) {
        TodayCheck todayCheck = new TodayCheck();
        todayCheck.setMember(member);
        todayCheck.setCheckDate(resultDate);
        todayCheckRepository.save(todayCheck);

        return "출석체크 완료! 100포인트 지급 되었습니다.";

    }

    @Override
    public String memberTodayCheck(Members member, String resultDate) {
        String memberid = member.getMemberID();

        return todayCheckRepository.findTodayCheck(memberid, resultDate);
    }
}
