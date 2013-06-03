package fm.weplayfootball.web.member;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import fm.weplayfootball.persistence.domain.Member;
import fm.weplayfootball.persistence.mapper.MemberMapper;
import fm.weplayfootball.web.member.domain.MemberForm;
import fm.weplayfootball.web.signup.domain.SignupForm;

public class MemberController {

	@Autowired private MemberMapper memberMapper;	

	@RequestMapping(value="/member/info", method=RequestMethod.GET)
	public String memberInfo(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("MEMBER");
		model.addAttribute("member", memberMapper.getByMsno(member.getMsno()));
		return "member/info";
	}

	@RequestMapping(value = "/member/info/{msno}", method = RequestMethod.GET)
	public String memberInfoByMsno(@PathVariable int msno, Model model) {

		model.addAttribute("member", memberMapper.getByMsno(msno));
		return "member/info";
	}

	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public MemberForm updateMemberForm(@PathVariable int msno, Model model) {

		model.addAttribute("member", memberMapper.getByMsno(msno));
		return "member/info";
	}

	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String updateMember(
			@Valid MemberForm form, 
			BindingResult formBinding, 
			WebRequest request
			) {

		if (formBinding.hasErrors()) {
			return null;
		}

		// @ TODO !!!

		return "member/info";
	}

}
