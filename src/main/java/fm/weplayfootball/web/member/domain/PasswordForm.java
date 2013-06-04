package fm.weplayfootball.web.member.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import fm.weplayfootball.persistence.domain.Member;

public class PasswordForm {

	@NotEmpty
	private int msno;

	@Size(min = 6, message = "암호는 6자 이상 이어야 합니다.")
	private String mpasswd;

	public String getMpasswd() {
		return mpasswd;
	}
	public void setMpasswd(String mpasswd) {
		this.mpasswd = mpasswd;
	}
	public int getMsno() {
		return msno;
	}
	public void setMsno(int msno) {
		this.msno = msno;
	}

	public static PasswordForm fromMemberObject(Member member) {

		PasswordForm form = new PasswordForm();
		form.setMsno(member.getMsno());
		form.setMpasswd(member.getMpasswd());

		return form;
	}

}
