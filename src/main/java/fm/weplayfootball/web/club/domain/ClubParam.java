package fm.weplayfootball.web.club.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class ClubParam {

	@NotEmpty( message = "지역정보를 선택해야 합니다.")
	private String clocal;
	
	@NotEmpty( message = "클럽 이름을 입력해야 합니다.")
	private String cname;
	
	public String getClocal() {
		return clocal;
	}
	public void setClocal(String clocal) {
		this.clocal = clocal;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
}
