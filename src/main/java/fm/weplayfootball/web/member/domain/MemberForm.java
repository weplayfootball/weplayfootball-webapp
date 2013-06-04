package fm.weplayfootball.web.member.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import fm.weplayfootball.persistence.domain.Member;

public class MemberForm {

	@NotEmpty
	private int msno;

	@NotEmpty( message = "메일 주소를 입려해야 합니다.")
	private String memail;

	@NotEmpty( message = "연락처를 입력하세요.")
	private String mtel;

	@NotEmpty( message = "이름을 입력하세요.")
	private String mname;

	@NotEmpty( message = "주력 포지션을 선택하세요.")
	private String mposition;

	@NotEmpty( message = "지역 정보를 선택하세요.")
	private String mlocal;

	private String mintro;

	private String mimage;
	private int mimagesize;

	private MultipartFile atchFile;


	public int getMsno() {
		return msno;
	}
	public void setMsno(int msno) {
		this.msno = msno;
	}
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


	public Member toMember(){
		Member member = new Member();
		member.setMsno(msno);
		member.setMemail(memail);
		member.setMtel(mtel);
		member.setMname(mname);
		member.setMposition(mposition);
		member.setMlocal(mlocal);
		member.setMintro(mintro);

		// @ TODO 
		member.setMimage(mimage);
		member.setMimagesize(mimagesize);

		return member;
	}

	public static MemberForm fromMemberObject(Member member) {

		MemberForm form = new MemberForm();
		form.setMsno(member.getMsno());
		form.setMemail(member.getMemail());
		form.setMtel(member.getMtel());
		form.setMname(member.getMname());
		form.setMposition(member.getMposition());
		form.setMlocal(member.getMlocal());
		form.setMintro(member.getMintro());
		form.setMimage(member.getMimage());
		form.setMimagesize(member.getMimagesize());

		return form;

	}

}
