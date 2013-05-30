package fm.weplayfootball.web;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String root(Principal currentUser, Model model) {
		return "redirect:/home";
	}

	@RequestMapping("/home")
	public void home(Principal currentUser, Model model) {
	}

	@RequestMapping(value = "/public/{page}", method = RequestMethod.GET)
	public String publicPages(@PathVariable String page, Model model) {
		return "public/"+page;
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
