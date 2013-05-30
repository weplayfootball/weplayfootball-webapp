package fm.weplayfootball.persistence.domain;

import java.io.Serializable;

public class MemberAuthCd implements Serializable {
	
	private static final long serialVersionUID = -6959024182111859148L;

	private String mname;
	private String memail;
	private String mauthcd;
	
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMauthcd() {
		return mauthcd;
	}
	public void setMauthcd(String mauthcd) {
		this.mauthcd = mauthcd;
	}

	
}
