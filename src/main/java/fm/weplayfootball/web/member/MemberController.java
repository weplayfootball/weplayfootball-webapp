package fm.weplayfootball.web.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import fm.weplayfootball.common.InvalidAccessException;
import fm.weplayfootball.persistence.domain.Member;
import fm.weplayfootball.persistence.mapper.MemberMapper;
import fm.weplayfootball.web.member.domain.MemberForm;
import fm.weplayfootball.web.member.domain.PasswordForm;

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
	public MemberForm updateMemberForm(HttpSession session) {
		Member member = (Member) session.getAttribute("MEMBER");
		member = memberMapper.getByMsno(member.getMsno());
		return MemberForm.fromMemberObject(member);
	}

	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String updateMember(
			@Valid MemberForm form, 
			BindingResult formBinding, 
			HttpServletRequest request,
			HttpSession session
			) throws InvalidAccessException {

		if (formBinding.hasErrors()) {
			return null;
		}

		Member member = (Member) session.getAttribute("MEMBER");

		if(form.getMsno() != member.getMsno()){
			throw new InvalidAccessException();
		}
		
		member.setMip(request.getRemoteAddr());
		
		// @ TODO 파일 업로드... 추가 되어야 함. 

		memberMapper.update(form.toMember());

		return "member/info";
	}

	@RequestMapping(value = "/member/update/password", method = RequestMethod.GET)
	public PasswordForm updateMemberPasswordForm(HttpSession session) {
		Member member = (Member) session.getAttribute("MEMBER");
		return PasswordForm.fromMemberObject(member);
	}
	
	@RequestMapping(value = "/member/update/password", method = RequestMethod.POST)
	public String updateMemberPassword(
			@Valid PasswordForm form, 
			BindingResult formBinding, 
			HttpSession session
			) throws InvalidAccessException {

		if (formBinding.hasErrors()) {
			return null;
		}

		Member member = (Member) session.getAttribute("MEMBER");

		if(form.getMsno() != member.getMsno()){
			throw new InvalidAccessException();
		}

		memberMapper.updatePassword(form.getMsno(), form.getMpasswd());

		return "member/info";
	}

}
