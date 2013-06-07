package fm.weplayfootball.web.signin.domain;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ForgetPasswordForm {

	@NotEmpty
	private String memail;

	@NotEmpty
	private String authcd;

	@Size(min = 6, message = "암호는 6자 이상 이어야 합니다.")
	private String mpasswd;

	@Size(min = 6, message = "암호는 6자 이상 이어야 합니다.")
	private String mpasswdconfirm;

	
	@AssertTrue(message="입력된 암호는 암호 확인과 다릅니다.")
	public boolean isPasswordValid(){
		if (mpasswd == null) {
			return false;
		} else {
			return mpasswd.equals(mpasswdconfirm);
		}
	}

	
	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public String getAuthcd() {
		return authcd;
	}

	public void setAuthcd(String authcd) {
		this.authcd = authcd;
	}

	public String getMpasswd() {
		return mpasswd;
	}

	public void setMpasswd(String mpasswd) {
		this.mpasswd = mpasswd;
	}

	public String getMpasswdconfirm() {
		return mpasswdconfirm;
	}

	public void setMpasswdconfirm(String mpasswdconfirm) {
		this.mpasswdconfirm = mpasswdconfirm;
	}

}
