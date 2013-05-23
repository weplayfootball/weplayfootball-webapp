package fm.weplayfootball.web.signup;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.social.connect.UserProfile;

public class SignupForm {

	@NotEmpty
	private String useremail;

	@Size(min = 6, message = "must be at least 6 characters")
	private String passwd;

	@NotEmpty
	private String username;

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static SignupForm fromProviderUser(UserProfile providerUser) {
		
		// @ TODO Form 에 추가 정의 필요!!!!
		SignupForm form = new SignupForm();
		form.setUseremail(providerUser.getEmail());
		form.setUsername(providerUser.getUsername());
		return form;
		
	}
	
}
