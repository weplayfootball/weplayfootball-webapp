package fm.weplayfootball.web;

import java.util.Enumeration;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fm.weplayfootball.persistence.domain.Member;
import fm.weplayfootball.persistence.mapper.MemberMapper;

@Controller
public class DebugController {

	private final Provider<ConnectionRepository> connectionRepositoryProvider;
	private final MemberMapper memberMapper;

	@Inject
	public DebugController(Provider<ConnectionRepository> connectionRepositoryProvider, MemberMapper memberMapper) {
		this.connectionRepositoryProvider = connectionRepositoryProvider;
		this.memberMapper = memberMapper;
	}

	@RequestMapping("/debug/member/{memail}")
	@ResponseBody
	public Member jsontest(@PathVariable String memail, Model model) {
		Member member = memberMapper.getByMemail(memail);
		System.out.println(member);
		return member;
	}

	@RequestMapping("/debug/session")
	@ResponseBody
	public User sessiontest(HttpServletRequest request) {

		User retrnP = null;

		HttpSession session = request.getSession();
		Enumeration attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {

			String name = (String) attributeNames.nextElement();
			System.out.println(name);
			if (name.equals("SPRING_SECURITY_CONTEXT")) {
				SecurityContext value = 
						(SecurityContext) session.getAttribute(name);
				Authentication authentication = value.getAuthentication();
				User principal = (User) authentication.getPrincipal();
				WebAuthenticationDetails details = 
						(WebAuthenticationDetails) authentication.getDetails();
				String username = authentication.getName();
				String password = (String) authentication.getCredentials();

				System.out.println("--------------------------------------------");
				System.out.println("name = " + name + " , value = " + value.toString());
				System.out.println("authentication : " + authentication.toString());
				System.out.println("principal : " + principal);
				System.out.println("details : " + details.toString());
				System.out.println("username : " + username);
				System.out.println("password : " + password);
				System.out.println();

				retrnP = principal;
			}
		}

		return retrnP;
	}


	private ConnectionRepository getConnectionRepository() {
		return connectionRepositoryProvider.get();
	}

	@ExceptionHandler(Exception.class)
	public void handleIOException(Exception ex) {
		ex.printStackTrace();
		System.out.println(" ---------------------^ ^--------------------- ");
		throw new ResourceNotFoundException(); 
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	class ResourceNotFoundException extends RuntimeException {
		private static final long serialVersionUID = -5544614874419545084L;
	}
}
