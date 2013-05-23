package fm.weplayfootball.web;

import java.security.Principal;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fm.weplayfootball.persistence.mapper.MemberMapper;

@Controller
public class HomeController {

	private final Provider<ConnectionRepository> connectionRepositoryProvider;
	private final MemberMapper memberMapper;

	@Inject
	public HomeController(Provider<ConnectionRepository> connectionRepositoryProvider, MemberMapper memberMapper) {
		this.connectionRepositoryProvider = connectionRepositoryProvider;
		this.memberMapper = memberMapper;
	}

	@RequestMapping("/")
	public String home(Principal currentUser, Model model) {

		System.out.println(getConnectionRepository().findAllConnections().size());
		System.out.println(getConnectionRepository().findAllConnections());
		System.out.println(memberMapper.getMemberByUseremail(currentUser.getName()).getUseremail());
		
		model.addAttribute("connectionsToProviders", getConnectionRepository().findAllConnections());
		model.addAttribute(memberMapper.getMemberByUseremail(currentUser.getName()));
		return "home";
	}

	private ConnectionRepository getConnectionRepository() {
		return connectionRepositoryProvider.get();
	}
}
