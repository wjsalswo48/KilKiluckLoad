package mz.sixsense.road.qr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import mz.sixsense.member.entity.Members;
import mz.sixsense.member.repository.MemberRepository;
import mz.sixsense.road.entity.Road;
import mz.sixsense.road.qr.repository.QRRepository;
import mz.sixsense.road.qr.service.QRService;
import mz.sixsense.road.repository.RoadRepository;
import mz.sixsense.security.SecurityUser;

@Service
public class QRServiceImpl implements QRService{
	
	@Autowired
	private QRRepository qrRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private RoadRepository roadRepo;
	
	public String getGugan(String gugan, String pos){
		return qrRepo.getGugan(gugan);
	}
	
	public Members getMemberQRFlag(String memberID) {
		return memberRepo.findQRFlagBymemberID(memberID);
	}
	
	public List<Road> getQRStartList(String gugan){
		return qrRepo.findAllByguganNm(gugan);
	}
	
//    @Override
//    public void updateFreeBoard(FreeBoard freeBoard) {
//        FreeBoard findFreeBoard = freeBoardRepo.findById(freeBoard.getFreeBID()).get();
//        findFreeBoard.setTitle(freeBoard.getTitle());
//        findFreeBoard.setContent(freeBoard.getContent());
//
//        freeBoardRepo.save(findFreeBoard);
//        
//    }   //updateFreeBoard
	
	public void QRFlagActive(@AuthenticationPrincipal SecurityUser principal, Members members, String QRFlag) {
		members = principal.getMember();
		members.setQrFlag(Integer.parseInt(QRFlag));
		
		memberRepo.save(members);
	}
	
    public void updatePoint(@AuthenticationPrincipal SecurityUser principal, Members members, String QRFlag, String rewardPoint) {
    	members = principal.getMember();
    	members.setQrFlag(Integer.parseInt(QRFlag));
    	members.setTotalPoint(members.getTotalPoint() + Long.parseLong(rewardPoint));
    	
        memberRepo.save(members);
    }
    
    public void updateVisit(Road road, String gugan) {
    	Road roadVisit = roadRepo.findById(gugan).get();
    	System.out.println("updateVisit guganNm==========="+roadVisit.getGuganNm());
    	roadVisit.setVisits(roadVisit.getVisits() + 1L);
    	
    	roadRepo.save(roadVisit);
    }
}
