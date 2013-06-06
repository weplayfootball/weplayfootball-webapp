package fm.weplayfootball.web.club;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import fm.weplayfootball.common.NotImageFilesException;
import fm.weplayfootball.common.utils.ImageUtil;
import fm.weplayfootball.persistence.domain.ClubInfo;
import fm.weplayfootball.persistence.domain.ClubInfoList;
import fm.weplayfootball.persistence.domain.Member;
import fm.weplayfootball.persistence.domain.common.SearchCondition;
import fm.weplayfootball.persistence.mapper.ClubInfoMapper;
import fm.weplayfootball.web.club.domain.ClubForm;

@Controller
public class ClubController {

	@Autowired private ClubInfoMapper 		clubInfoMapper;	

	@RequestMapping(value="/club", method=RequestMethod.GET)
	public String club() {
		return "club";
	}

	@RequestMapping(value="/club/list", method=RequestMethod.GET)
	@ResponseBody
	public List<ClubInfoList> listClubInfo(
			@RequestParam(value="clocal",required=false) 	String 	clocal,
			@RequestParam(value="cname",required=false) 	String 	cname,
			@RequestParam(value="pageNum",	required=false, defaultValue="1") int pageNum
			) {


		SearchCondition searchCond = new SearchCondition();
		if(!StringUtils.isEmpty(clocal) || !StringUtils.isEmpty(cname)){
			searchCond.setSrchType	(StringUtils.isEmpty(clocal)?"cname":"clocal");
			searchCond.setSrchValue	(StringUtils.isEmpty(clocal)? cname : clocal);
		}
		searchCond.setPageNum	(pageNum);

		List<ClubInfoList> list = clubInfoMapper.listClubInfo(searchCond);

		return list;
	}

	@RequestMapping(value="/club/new", method=RequestMethod.GET)
	public ClubForm newClubForm(WebRequest request) {
		return new ClubForm();
	}

	@RequestMapping(value="/club/new", method=RequestMethod.POST)
	public String newClub(
			@Valid ClubForm param, 
			BindingResult formBinding, 
			HttpServletRequest request,
			HttpSession session) {
		
		ClubInfo clubInfo = clubInfoMapper.getByCname(param.getCname());
		if(clubInfo != null) formBinding.addError(new ObjectError("cname","이미 사용중인 클럼 이름입니다."));
		
		if (formBinding.hasErrors()) return null;
		
		MultipartFile img = param.getEmblemImage();

		int csno = clubInfoMapper.getCsno();
		try {
			System.out.println(request.getSession().getServletContext().getRealPath("/resources/club"));
			ImageUtil.save(img, 
					request.getSession().getServletContext().getRealPath("/resources/club"), 
					csno+"."+FilenameUtils.getExtension(img.getOriginalFilename()));

			ImageUtil.save(img, 
					request.getSession().getServletContext().getRealPath("/resources/club"), 
					"T_"+csno+"."+FilenameUtils.getExtension(img.getOriginalFilename()),
					200, 200);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Member member = (Member) session.getAttribute("MEMBER");

		clubInfo = new ClubInfo();
		clubInfo.setCsno		(csno);
		clubInfo.setCmaker		(member.getMsno());
		clubInfo.setCmakername	(member.getMname());
		clubInfo.setCip			(request.getRemoteAddr());
		clubInfo.setCname		(param.getCname());
		clubInfo.setImage		(param.getImage());
		clubInfo.setClocal		(param.getClocalSi());

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
