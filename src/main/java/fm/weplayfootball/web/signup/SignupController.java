package fm.weplayfootball.web.signup;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import fm.weplayfootball.persistence.domain.Member;
import fm.weplayfootball.persistence.mapper.MemberMapper;
import fm.weplayfootball.web.message.Message;
import fm.weplayfootball.web.message.MessageType;
import fm.weplayfootball.web.signin.SignInUtils;

@Controller
public class SignupController {

	@Autowired
	private MemberMapper memberMapper;


	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public SignupForm signupForm(WebRequest request) {

		Connection<?> connection = ProviderSignInUtils.getConnection(request);

		if (connection != null) {
			request.setAttribute("message", new Message(MessageType.INFO, "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a WePlayFootball.fm account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
			return SignupForm.fromProviderUser(connection.fetchUserProfile());
		} else {
			return new SignupForm();
		}

	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
		if (formBinding.hasErrors()) {
			return null;
		}
		Member member = createAccount(form, formBinding);
		if (member != null) {
			SignInUtils.signin(member.getUseremail());
			ProviderSignInUtils.handlePostSignUp(member.getUseremail(), request);
			return "redirect:/";
		}
		return null;
	}

	// internal helpers

	private Member createAccount(SignupForm form, BindingResult formBinding) {
		try {
			Member member = new Member();
			member.setUseremail(form.getUseremail());
			member.setPasswd(form.getPasswd());
			member.setUsername(form.getUsername());

			System.out.println(form.getUseremail());
			System.out.println(form.getPasswd());
			System.out.println(form.getUsername());
			
			memberMapper.insertMember(member);
			return member;
		} catch (DuplicateKeyException  e) {
			formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
			return null;
		}
	}

}
