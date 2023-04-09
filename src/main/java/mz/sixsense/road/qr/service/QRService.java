package mz.sixsense.road.qr.service;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import mz.sixsense.member.entity.Members;
import mz.sixsense.road.entity.Road;
import mz.sixsense.security.SecurityUser;

public interface QRService {

	String getGugan(String gugan, String pos);
	
	List<Road> getQRStartList(String gugan);
	
	Members getMemberQRFlag(String memberID);
	
	void QRFlagActive(@AuthenticationPrincipal SecurityUser principal, Members members, String QRFlag);
	
	void updatePoint(@AuthenticationPrincipal SecurityUser principal, Members members, String QRFlag, String rewardPoint);
	
	void updateVisit(Road road, String gugan);
}
