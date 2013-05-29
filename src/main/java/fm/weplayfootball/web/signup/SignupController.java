package fm.weplayfootball.web.signup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import fm.weplayfootball.persistence.domain.Member;
import fm.weplayfootball.persistence.mapper.GroundsMapper;
import fm.weplayfootball.persistence.mapper.MemberMapper;
import fm.weplayfootball.web.message.Message;
import fm.weplayfootball.web.message.MessageType;
import fm.weplayfootball.web.signin.SignInUtils;

@Controller
public class SignupController {


	@Autowired
	private MemberMapper memberMapper;	

	@Autowired
	private GroundsMapper groundsMapper;	

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	Environment env;

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
			request.setAttribute("message", new Message(MessageType.INFO, connection.fetchUserProfile().getUsername()+" 님 " + StringUtils.capitalize(connection.getKey().getProviderId()) + " 계정이 WePlayFootball.fm 에 아직 신규 가입되지 않았습니다."), WebRequest.SCOPE_REQUEST);
			return SignupForm.fromProviderUser(connection.fetchUserProfile());
		} else {

			if(request.getParameter("auth")!= null){
				// @ TODO check authCD is valid ?
				if(true){
					SignupForm form = new SignupForm();
					form.setMname("AA");
					form.setMemail("aa@gmail.com");
					return form;
				}else{
					request.setAttribute("message", new Message(MessageType.ERROR, "신규가입 인증번호가 만료되었거나 유효하지 않습니다. 다시 회원 가입 하십시오."), WebRequest.SCOPE_REQUEST);
					return new SignupForm();
				}
			}

			return new SignupForm();
		}

	}

	@RequestMapping(value="/mailAuthCd", method=RequestMethod.POST)
	@ResponseBody
	public String mailAuthCd(
			@RequestParam("name") String name,
			@RequestParam("email") String email ) {


		String authCd = generateAuthPassword();
		// @ TODO DB 에 저장 


		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("hur.ikhan@ninetofiveinc.com");
		mailMessage.setTo(email);
		mailMessage.setSubject(name+" 님, WePlayFootBall 에서 인증메일을 보냅니다");
		mailMessage.setText(
				name + " 님, 회원님의 회원가입 인증번호는 "+authCd + "입니다. <br><br><br>" +
						"아래 링크를 클릭하시면 회원가입 페이지로 바로 이동합니다.<br>"+
						"<a href=\"http://weplayfootball/signup?auth="+authCd+"\">http://weplayfootball/signup?auth="+authCd+"</a>");

		mailSender.send(mailMessage);

		return "OK";
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(
			@Valid SignupForm form, 
			BindingResult formBinding, WebRequest request) {

		if (formBinding.hasErrors()) {
			return null;
		}
		
		MultipartFile atchFile = form.getAtchFile();

		// 파일 업로드 !!!!
		if(atchFile != null && !atchFile.isEmpty()){
			FileOutputStream out = null;
			try {
				byte[] fileByte = atchFile.getBytes();
				
				String fileName = atchFile.getOriginalFilename();
				String fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
				String path = env.getProperty("fileupload.profile");
				File destinationDir = new File(path);
				if (!destinationDir.exists()){
					destinationDir.mkdirs();
				}

				String currTime = Long.toString(System.currentTimeMillis());
				String newFileName = currTime +"-"+form.getMemail()+ fileExt;
				out = new FileOutputStream(destinationDir + File.separator + newFileName);
				out.write(fileByte);

				form.setMimage(newFileName);
				form.setMimagesize(fileByte.length);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		Member member = createMember(form, formBinding);
		if (member != null) {
			SignInUtils.signin(member.getMemail());
			ProviderSignInUtils.handlePostSignUp(member.getMemail(), request);
			return "redirect:/";
		}
		return null;

		
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		
        logger.error(exception);
        Map<String, Object> model = new HashMap<String, Object>();
        if (exception instanceof MaxUploadSizeExceededException)
        {
        	
        	Long maxSizeInBytes = ((MaxUploadSizeExceededException) exception).getMaxUploadSize();
            System.out.println("ADSFASDFASDFASDF : "+maxSizeInBytes);
        	
        	//request.setAttribute("message", "파일 업로드는 1MB 이상 불가능합니다."); //exception.getMessage());
        	//model.put("message", "abc");
        	//request.setAttribute("message", new Message(MessageType.ERROR, "파일 업로드는 1MB 이상 불가능합니다."));
        	model.put("message", new Message(MessageType.ERROR, "파일 업로드는 1MB 이상 불가능합니다."));
        } else
        {
        	//request.setAttribute("message", "Unexpected error: " + exception.getMessage());
        	//model.put("message", "abc");
        }

        System.out.println("-----------");
        while(request.getParameterNames().hasMoreElements()){
        	  String names = (String)request.getParameterNames().nextElement();
        	  System.out.println(names + " : " + request.getParameter(names) + "<br>");
        	 }
        
        
        
        System.out.println(request.getAttribute("memail"));
        System.out.println(request.getAttribute("mname"));
        System.out.println(request.getAttribute("signForm"));
        System.out.println(request.getParameterMap());
        System.out.println(request.getParameterNames());
        
        System.out.println("MM : "+request.getParameter("memail"));
        System.out.println("MM : "+request.getParameter("mname"));
        System.out.println("---- " + handler);
        
        SignupForm form = new SignupForm();
        form.setMemail("yohany@gmail.com");
        form.setMname("asdfdfsadasf김요");
        model.put("signupForm", form);
        return new ModelAndView("signup", model);
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
