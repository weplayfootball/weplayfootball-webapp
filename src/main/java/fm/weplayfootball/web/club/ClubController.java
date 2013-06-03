package fm.weplayfootball.web.club;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import fm.weplayfootball.persistence.domain.ClubInfo;
import fm.weplayfootball.persistence.domain.ClubInfoList;
import fm.weplayfootball.persistence.domain.Member;
import fm.weplayfootball.persistence.domain.common.SearchCondition;
import fm.weplayfootball.persistence.mapper.ClubInfoMapper;
import fm.weplayfootball.web.club.domain.ClubParam;

public class ClubController {

	@Autowired private ClubInfoMapper 		clubInfoMapper;	

	@RequestMapping(value="/club/list", method=RequestMethod.GET)
	@ResponseBody
	public List<ClubInfoList> listClubInfo(
			@RequestParam("srchType") 	String 	srchType,
			@RequestParam("srchValue") 	String 	srchValue,
			@RequestParam("pageNum") 	int 	pageNum
			) {

		SearchCondition searchCond = new SearchCondition();
		searchCond.setSrchType(srchType);
		searchCond.setSrchValue(srchValue);
		searchCond.setPageNum(pageNum);

		List<ClubInfoList> list = clubInfoMapper.listClubInfo(searchCond);

		return list;
	}

	@RequestMapping(value="/club/new", method=RequestMethod.GET)
	public ClubParam newClubForm(WebRequest request) {
		return new ClubParam();
	}

	@RequestMapping(value="/club/new", method=RequestMethod.POST)
	public String newClubInfo(
			@Valid ClubParam param, 
			BindingResult formBinding, 
			HttpServletRequest request,
			HttpSession session) {

		if (formBinding.hasErrors()) {
			return null;
		}

		ClubInfo clubInfo = clubInfoMapper.getByCname(param.getCname());
		if(clubInfo != null){
			ObjectError error = new ObjectError("cname","이미 사용중인 클럼 이름입니다.");
			formBinding.addError(error);
			return null;
		}

		int csno = clubInfoMapper.getCsno();
		Member member = (Member) session.getAttribute("MEMBER");

		clubInfo = new ClubInfo();
		clubInfo.setCsno(csno);
		clubInfo.setCmaker(member.getMsno());
		clubInfo.setCmakername(member.getMname());
		clubInfo.setCip(request.getRemoteAddr());
		clubInfo.setCname(param.getCname());
		clubInfo.setClocal(param.getClocal());

		clubInfoMapper.insert(clubInfo);

		return "redirect:/club/info/"+csno;
	}

	@RequestMapping(value = "/club/info/{csno}", method = RequestMethod.GET)
	public String clubInfoByCsno(@PathVariable int csno, Model model) {

		ClubInfo clubInfo = clubInfoMapper.getByCsno(csno);
		model.addAttribute("club", clubInfo);

		return "club/info";
	}


}
