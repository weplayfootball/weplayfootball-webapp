package fm.weplayfootball.web.signup.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.social.connect.UserProfile;
import org.springframework.web.multipart.MultipartFile;

import fm.weplayfootball.persistence.domain.Member;

public class SignupForm {

	@NotEmpty( message = "메일 주소를 입려해야 합니다.")
	private String memail;

	@Size(min = 6, message = "암호는 6자 이상 이어야 합니다.")
	private String mpasswd;

	@NotEmpty( message = "연락처를 입력하세요.")
	private String mtel;

	@NotEmpty( message = "이름을 입력하세요.")
	private String mname;

	@NotEmpty( message = "주력 포지션을 선택하세요.")
	private String mposition;

	@NotEmpty( message = "지역 정보를 선택하세요.")
	private String mlocal;

	private String mintro;

	private String authcd;
	
	private String mimage;
	private int mimagesize;
	
	private MultipartFile atchFile;



	public MultipartFile getAtchFile() {
		return atchFile;
	}
	public void setAtchFile(MultipartFile atchFile) {
		this.atchFile = atchFile;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMpasswd() {
		return mpasswd;
	}
	public void setMpasswd(String mpasswd) {
		this.mpasswd = mpasswd;
	}
	public String getMtel() {
		return mtel;
	}
	public void setMtel(String mtel) {
		this.mtel = mtel;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMposition() {
		return mposition;
	}
	public void setMposition(String mposition) {
		this.mposition = mposition;
	}
	public String getMlocal() {
		return mlocal;
	}
	public void setMlocal(String mlocal) {
		this.mlocal = mlocal;
	}
	public String getMintro() {
		return mintro;
	}
	public void setMintro(String mintro) {
		this.mintro = mintro;
	}
	public String getMimage() {
		return mimage;
	}
	public void setMimage(String mimage) {
		this.mimage = mimage;
	}
	public int getMimagesize() {
		return mimagesize;
	}
	public void setMimagesize(int mimagesize) {
		this.mimagesize = mimagesize;
	}
	
	public String getAuthcd() {
		return authcd;
	}
	public void setAuthcd(String authcd) {
		this.authcd = authcd;
	}
	
	public Member toMember(){
		Member member = new Member();
		member.setMemail(memail);
		member.setMpasswd(mpasswd);
		member.setMtel(mtel);
		member.setMname(mname);
		member.setMposition(mposition);
		member.setMlocal(mlocal);
		member.setMintro(mintro);
		member.setMimage(mimage);
		member.setMimagesize(mimagesize);
		
		return member;
	}

	
	
	public static SignupForm fromProviderUser(UserProfile providerUser) {

		// @ TODO Form 에 추가 정의 필요!!!!
		SignupForm form = new SignupForm();
		form.setMemail(providerUser.getEmail());
		form.setMname(providerUser.getName());
		return form;

	}

}
