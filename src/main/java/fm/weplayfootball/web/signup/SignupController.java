package fm.weplayfootball.web.signup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

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
			request.setAttribute("message", new Message(MessageType.INFO, "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a WePlayFootball.fm account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
			return SignupForm.fromProviderUser(connection.fetchUserProfile());
		} else {
			return new SignupForm();
		}

	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(
			@Valid SignupForm form, 
			BindingResult formBinding, WebRequest request, 
			@RequestParam("profileFile") MultipartFile atchFile) {

		if (formBinding.hasErrors()) {
			return null;
		}
		
		// 파일 업로드 !!!!
		if(atchFile != null && !atchFile.isEmpty()){
			FileOutputStream out = null;
			try {
				byte[] fileByte = atchFile.getBytes();
				if (logger.isDebugEnabled()) {
					logger.debug("file.getOriginalFilename===" + atchFile.getOriginalFilename() + "(file.length===" + fileByte.length + ")");
				}

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


	private Member createMember(SignupForm form, BindingResult formBinding) {
		try {
			Member member = form.toMember();
			memberMapper.insert(member);
			return member;
		} catch (DuplicateKeyException e) {
			formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
			return null;
		}
	}

}
