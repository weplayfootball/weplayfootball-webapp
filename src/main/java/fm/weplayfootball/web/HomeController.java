package fm.weplayfootball.web;

import java.security.Principal;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.http.HttpStatus;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	public String root(Principal currentUser, Model model) {
		return "redirect:/home";
	}

	@RequestMapping("/home")
	public void home(Principal currentUser, Model model) {
		model.addAttribute("connectionsToProviders", getConnectionRepository().findAllConnections());
		if(currentUser != null){
		    model.addAttribute(memberMapper.getMemberByUseremail(currentUser.getName()));
		}
	}
	
	@RequestMapping(value = "/public/{page}", method = RequestMethod.GET)
	public String publicPages(@PathVariable String page, Model model) {
        return "public/"+page;
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
