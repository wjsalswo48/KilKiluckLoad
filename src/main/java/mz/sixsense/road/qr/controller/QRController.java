package mz.sixsense.road.qr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.service.PointHistoryService;
import mz.sixsense.member.repository.MemberRepository;
import mz.sixsense.road.entity.CodeTable;
import mz.sixsense.road.entity.Road;
import mz.sixsense.road.qr.service.QRService;
import mz.sixsense.road.repository.CodeTableRepository;
import mz.sixsense.security.SecurityUser;

@Controller
public class QRController {

	@Autowired
	private QRService qrService;

	@Autowired
	private PointHistoryService pointHistoryService;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private CodeTableRepository codeTableRepository;

	@RequestMapping("QR/QRGuide")
	public String QRGuide() {
		return "QR/QRGuide";
	}

//	@RequestMapping("QR/QRStart")
//	public String QRStart(@RequestParam(name="gugan", defaultValue="") String gugan,
//									@RequestParam(name="gmText", defaultValue="") String gmCourse, Model model) {
//		String showText = qrService.getText(gugan, gmCourse);
//		System.out.println("Start 컨트롤러======================"+showText);
//		model.addAttribute("gugan", gugan);
//		model.addAttribute("showText", showText);
//		return "QR/QRStart";
//	}

	@RequestMapping("QR/QRStart")
	public String getQRStartList(Model model, String gugan) {
		List<Road> QRStartList = qrService.getQRStartList(gugan);
		CodeTable codeTable = codeTableRepository.findById(gugan).get();
		String codeName = codeTable.getCodeName();
		System.out.println("codeTable==============="+codeName);
		model.addAttribute("guganName", codeName);
		model.addAttribute("QRStartList", QRStartList);
//		System.out.println("QRStartList=============" + QRStartList);
		
		return "QR/QRStart";
	}

	@RequestMapping("QR/QREnd")
	public String QREnd() {
		return "QR/QREnd";
	}

	@RequestMapping("QR/QRCheck")
	public String QRCheck() {
		return "QR/QRCheck";
	}

//	@RequestMapping("QR/QRLink")
//	public String QRLink() {
//		return "QR/QRLink";
//	}
	
	// 링크 정보 전송
	@RequestMapping("QR/QRLink")
	public String getGugan(@RequestParam(name = "gugan", defaultValue = "") String gugan,
			@RequestParam(name = "pos", defaultValue = "") String pos, @AuthenticationPrincipal SecurityUser principal,
			Model model) {
		qrService.getGugan(gugan, pos);
		String memberID = principal.getMember().getMemberID();
		qrService.getMemberQRFlag(memberID);
		String latlong = qrService.getGugan(gugan, pos);
		int QRFlag = qrService.getMemberQRFlag(memberID).getQrFlag();
		System.out.println("컨트롤러======================" + gugan);
		System.out.println("컨트롤러======================" + pos);
		System.out.println("컨트롤러======================" + latlong);
		System.out.println("컨트롤러======================" + QRFlag);
		model.addAttribute("latlong", latlong);
		model.addAttribute("gugan", gugan);
		model.addAttribute("pos", pos);
		model.addAttribute("QRFlag", QRFlag);
		return "QR/QRLink";
	}

	@PostMapping("QR/QRLink")
	public void QRFlagActive(@AuthenticationPrincipal SecurityUser principal, 
			@RequestParam(name = "gugan", defaultValue = "") String gugan,
			Members members, String QRFlag, String pos,
			String rewardPoint, Road road) {
		qrService.updatePoint(principal, members, QRFlag, rewardPoint);
		if (QRFlag.equals("0")) {
			Members member = memberRepository.findMember(principal.getMember().getMemberID());
			String reason = "QR인증 리워드";
			Long point = 2000L;
			pointHistoryService.insertPointHistory(member, reason, point);
			qrService.updateVisit(road, gugan);
		}
	}

	@RequestMapping("QR/QRFail")
	public String QRFail() {
		return "QR/QRFail";
	}
}
