package fm.weplayfootball.persistence.domain;

import java.io.Serializable;

public class ClubInfo implements Serializable {

	private static final long serialVersionUID = -1234763285873853072L;
	
	private int csno;
	private String cname;
	private String clocal;
	private String ctime;
	private String cip;
	private int cmaker;
	private String cmakername;
	private String image;
	private int count;
	private String description;
	
	
	public int getCsno() {
		return csno;
	}
	public void setCsno(int csno) {
		this.csno = csno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getClocal() {
		return clocal;
	}
	public void setClocal(String clocal) {
		this.clocal = clocal;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getCip() {
		return cip;
	}
	public void setCip(String cip) {
		this.cip = cip;
	}
	public int getCmaker() {
		return cmaker;
	}
	public void setCmaker(int cmaker) {
		this.cmaker = cmaker;
	}
	public String getCmakername() {
		return cmakername;
	}
	public void setCmakername(String cmakername) {
		this.cmakername = cmakername;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
