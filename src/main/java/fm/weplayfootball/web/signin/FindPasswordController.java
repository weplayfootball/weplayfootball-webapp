package fm.weplayfootball.web.signin;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import fm.weplayfootball.common.InvalidAccessException;
import fm.weplayfootball.common.utils.AuthCdGenerator;
import fm.weplayfootball.common.utils.EmailValidator;
import fm.weplayfootball.persistence.domain.Member;
import fm.weplayfootball.persistence.domain.MemberAuthCd;
import fm.weplayfootball.persistence.mapper.MemberAuthCdMapper;
import fm.weplayfootball.persistence.mapper.MemberMapper;
import fm.weplayfootball.web.message.Message;
import fm.weplayfootball.web.message.MessageType;
import fm.weplayfootball.web.signin.domain.ForgetPasswordForm;
import fm.weplayfootball.web.signin.domain.ResultChangePasswordMail;

@Controller
public class FindPasswordController {

	@Autowired private MemberAuthCdMapper 	memberAuthCdMapper;	
	@Autowired private MemberMapper 		memberMapper;	

	@Autowired private JavaMailSender 		mailSender;
	@Autowired private VelocityEngine 		velocityEngine;
	@Autowired private EmailValidator 		emailValidator;
	@Autowired private AuthCdGenerator		authCdGenerator;

	@RequestMapping(value="/signin/password", method=RequestMethod.GET)
	public ForgetPasswordForm forgetPassword(
			@RequestParam("auth") 	String authCd,
			@RequestParam("email") 	String email,
			WebRequest request) {

		if(StringUtils.hasText(authCd)){

			MemberAuthCd memberAuthCd = memberAuthCdMapper.read(email, authCd, MemberAuthCd.TYPE_PASSWORD);

			if(memberAuthCd != null && StringUtils.hasText(memberAuthCd.getMemail())){
				ForgetPasswordForm form = new ForgetPasswordForm();
				form.setAuthcd(memberAuthCd.getMauthcd());
				form.setMemail(memberAuthCd.getMemail());
				return form;
			}else{
				request.setAttribute("message", new Message(MessageType.ERROR, "암호 변경 인증번호가 만료되었거나 유효하지 않습니다. 암호 변경 신청을 새로 하셔야 합니다."), WebRequest.SCOPE_REQUEST);
			}
		}else{
			request.setAttribute("message", new Message(MessageType.ERROR, "암호 변경 요청이 잘못되었습니다. 암호 변경은 본인 메일로 발송된 링크를 통해 가능합니다."), WebRequest.SCOPE_REQUEST);
		}

		return new ForgetPasswordForm();
	}

	@RequestMapping(value="/signin/password", method=RequestMethod.POST)
	public String changeForgetPassword(
			@Valid ForgetPasswordForm form, 
			BindingResult formBinding, 
			WebRequest request) throws InvalidAccessException {

		if (formBinding.hasErrors()) {
			return null;
		}

		MemberAuthCd memberAuthCd = memberAuthCdMapper.read(form.getMemail(), form.getAuthcd(), MemberAuthCd.TYPE_PASSWORD);
		if(memberAuthCd == null || StringUtils.isEmpty(memberAuthCd.getMemail())){
			throw new InvalidAccessException();
		}

		memberMapper.updatePasswordByEmail(form.getMemail(), form.getMpasswd());

		memberAuthCdMapper.delete(form.getMemail(), MemberAuthCd.TYPE_PASSWORD);

		return "redirect:/signin";
	}

	@RequestMapping(value="/signin/forgetPassword", method=RequestMethod.POST)
	@ResponseBody
	public ResultChangePasswordMail signinPassword(@RequestParam("email") String email) {
		ResultChangePasswordMail result = new ResultChangePasswordMail("ok");
		if(!emailValidator.validate(email)){
			return new ResultChangePasswordMail("error", "메일 주소가 잘못되었습니다.");
		}

		Member member = memberMapper.getByMemail(email);
		if(member == null){
			return new ResultChangePasswordMail("error", "회원가입된 내역이 없습니다.");
		}

		String authCd = authCdGenerator.generateAuthPassword();

		MemberAuthCd memberAuthcd = new MemberAuthCd();
		memberAuthcd.setMname	(member.getMname());
		memberAuthcd.setMemail	(email);
		memberAuthcd.setMauthcd	(authCd);
		memberAuthcd.setType	(MemberAuthCd.TYPE_PASSWORD);

		try {
			memberAuthCdMapper.insert(memberAuthcd);
		} catch (DuplicateKeyException e) {
			memberAuthCdMapper.update(memberAuthcd);
			result.setMessage("duplicate");
		}

		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom		("hur.ikhan@ninetofiveinc.com");
			helper.setTo		(email);
			helper.setSubject	(member.getMname()+" 님, WePlayFootBall 에서 암호를 변경할 수 있습니다.");

			memberAuthcd.setMemail(URLEncoder.encode(email, "UTF-8"));
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("auth", memberAuthcd);

			//String textText = VelocityEngineUtils.mergeTemplateIntoString(
			//           velocityEngine, "fm/weplayfootball/web/signup/EmailAuthenticationText.vm", "UTF-8", model);

			String textHtml = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, "fm/weplayfootball/web/signin/EmailChangePasswordHtml.vm", "UTF-8", model);

			helper.setText(textHtml, true);

			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResultChangePasswordMail("error", e.getMessage());
		}

		return result;

	}

}
