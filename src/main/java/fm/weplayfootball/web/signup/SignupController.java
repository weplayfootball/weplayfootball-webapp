package fm.weplayfootball.web.signup;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import fm.weplayfootball.persistence.domain.Member;
import fm.weplayfootball.persistence.domain.MemberAuthCd;
import fm.weplayfootball.persistence.mapper.GroundsMapper;
import fm.weplayfootball.persistence.mapper.MemberAuthCdMapper;
import fm.weplayfootball.persistence.mapper.MemberMapper;
import fm.weplayfootball.web.common.utils.EmailValidator;
import fm.weplayfootball.web.message.Message;
import fm.weplayfootball.web.message.MessageType;
import fm.weplayfootball.web.signin.SignInUtils;
import fm.weplayfootball.web.signup.domain.ResultAuthCd;
import fm.weplayfootball.web.signup.domain.SignupForm;

@Controller
public class SignupController {

	@Autowired private MemberMapper 		memberMapper;	
	@Autowired private MemberAuthCdMapper 	memberAuthCdMapper;	
	@Autowired private GroundsMapper 		groundsMapper;	

	@Autowired private JavaMailSender mailSender;
	@Autowired private VelocityEngine velocityEngine;

	@Autowired Environment env;
	@Autowired EmailValidator emailValidator;

	static final Logger logger = Logger.getLogger(SignupController.class); 

	@RequestMapping(value="/signup/glocaldo", method=RequestMethod.GET)
	@ResponseBody
	public List<String> listGlocaldo(){
		return groundsMapper.listGlocaldo();
	}

	@RequestMapping(value="/signup/glocalsi/{glocaldo}", method=RequestMethod.GET)
	@ResponseBody
	public List<String> listGlocalsi(@PathVariable String glocaldo){
		return groundsMapper.listGlocalsi(glocaldo);
	}

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public SignupForm signupForm(WebRequest request) {

		Connection<?> connection = ProviderSignInUtils.getConnection(request);

		if (connection != null) {
			request.setAttribute("message", new Message(MessageType.INFO, connection.fetchUserProfile().getUsername()+" 님 " + StringUtils.capitalize(connection.getKey().getProviderId()) + " 과 연결되었으나, 아직 WePlayFootball.fm 에 가입되지 않았습니다. <br/ > 계속 신규 가입하시거나, <a href='/signout'><strong>여기</strong></a> 를 클릭하여 다른 방법으로 로그인/신규가입 하세요."), WebRequest.SCOPE_REQUEST);
			return SignupForm.fromProviderUser(connection.fetchUserProfile());

		} else {

			String email = request.getParameter("email");
			String authCd = request.getParameter("auth");
			if(StringUtils.hasText(authCd)){
				
				MemberAuthCd memberAuthCd = memberAuthCdMapper.read(email, authCd);

				if(memberAuthCd != null && StringUtils.hasText(memberAuthCd.getMemail())){
					SignupForm form = new SignupForm();
					form.setMname(	memberAuthCd.getMname()		);
					form.setMemail(	memberAuthCd.getMemail()	);
					form.setAuthcd(	memberAuthCd.getMauthcd()	);
					return form;
				}else{
					request.setAttribute("message", new Message(MessageType.ERROR, "신규가입 인증번호가 만료되었거나 유효하지 않습니다. 다시 회원 가입 하십시오."), WebRequest.SCOPE_REQUEST);

					// 회원가입 1단계 페이지 !!!
					return new SignupForm();
				}
			}else{

				// 회원가입 1단계 페이지 !!!
				return new SignupForm();
			}
		}

	}

	@RequestMapping(value="/signup/auth", method=RequestMethod.POST)
	@ResponseBody
	public ResultAuthCd mailAuthCd(
			@RequestParam("name") String name,
			@RequestParam("email") String email ) {

		ResultAuthCd resultAuthCd = new ResultAuthCd("ok");

		if(!emailValidator.validate(email)){
			return new ResultAuthCd("error", "메일 주소가 잘못되었습니다.");
		}
		
		
		String authCd = generateAuthPassword();

		MemberAuthCd memberAuthcd = new MemberAuthCd();
		memberAuthcd.setMname(name);
		memberAuthcd.setMemail(email);
		memberAuthcd.setMauthcd(authCd);

		try {
			memberAuthCdMapper.insert(memberAuthcd);
		} catch (DuplicateKeyException e) {
			memberAuthCdMapper.update(memberAuthcd);
			resultAuthCd.setMessage("duplicate");
		}
		
		try {
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom(		"hur.ikhan@ninetofiveinc.com");
			helper.setTo(		email);
			helper.setSubject(	name+" 님, WePlayFootBall 에서 인증메일을 보냅니다");
			
			memberAuthcd.setMemail(URLEncoder.encode(email, "UTF-8"));
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("auth", memberAuthcd);
			
			//String textText = VelocityEngineUtils.mergeTemplateIntoString(
			//           velocityEngine, "fm/weplayfootball/web/signup/EmailAuthenticationText.vm", "UTF-8", model);
			
			String textHtml = VelocityEngineUtils.mergeTemplateIntoString(
			           velocityEngine, "fm/weplayfootball/web/signup/EmailAuthenticationHtml.vm", "UTF-8", model);
			
			logger.debug(textHtml);
			
			helper.setText(textHtml, true);
			
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultAuthCd("error", e.getMessage());
		}

		return resultAuthCd;
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(
			@Valid SignupForm form, 
			BindingResult formBinding, WebRequest request) {

		if (formBinding.hasErrors()) {
			return null;
		}
		
		if ( StringUtils.isEmpty(form.getAuthcd())){
			Connection<?> connection = ProviderSignInUtils.getConnection(request);
			if(StringUtils.isEmpty(connection.fetchUserProfile().getEmail())){
				return null;
			}
			form.setMemail(connection.fetchUserProfile().getEmail());
		}else{
			MemberAuthCd memberAuthCd = memberAuthCdMapper.read(form.getMemail(), form.getAuthcd());

			if(memberAuthCd == null || StringUtils.isEmpty(memberAuthCd.getMemail())){
				return null;
			}
		}

		ProfileImageUploader.upload( env.getProperty("fileupload.profile"), form );

		Member member = createMember(form, formBinding);
		if (member != null) {
			SignInUtils.signin(member.getMemail());
			ProviderSignInUtils.handlePostSignUp(member.getMemail(), request);
			return "redirect:/";
		}

		return null;
	}

	private Member createMember(SignupForm form, BindingResult formBinding) {
		try {
			Member member = form.toMember();
			System.out.println(member);
			memberMapper.insert(member);
			return member;
		} catch (DuplicateKeyException e) {
			formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
			return null;
		}
	}

	private String generateAuthPassword(){
		Random rand = new Random(System.currentTimeMillis());  
		int randomindex =0;
		int r = 0;
		String rr = "";
		String fullpass = "";

		for(randomindex=0;randomindex<12;randomindex++){
			r = 0; 
			rr = ""; 
			r = rand.nextInt(9)+1; //1이상, 9이하
			rr = Integer.toString(r);
			fullpass += rr;
		}

		return fullpass;
	}

}
