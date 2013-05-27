package fm.weplayfootball.web.signin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import fm.weplayfootball.persistence.domain.Member;
import fm.weplayfootball.persistence.mapper.MemberMapper;

public class SigninAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws IOException,
			ServletException {

		super.onAuthenticationSuccess(request, response, authentication);
		
		HttpSession session = request.getSession(true);
		Member member = memberMapper.read(authentication.getName());

		session.setAttribute("MEMBER", member);

	}

}
