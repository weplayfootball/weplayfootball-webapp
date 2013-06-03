package fm.weplayfootball.persistence.domain;

public class ClubInfo {

	/**
CSNO       NOT NULL NUMBER(38)    
CNAME               VARCHAR2(50)  
CLOCAL              VARCHAR2(50)  
CTIME               CHAR(14)      
CIP                 VARCHAR2(50)  
CMAKER     NOT NULL NUMBER(38)    
CMAKERNAME          VARCHAR2(50)  
IMAGE               VARCHAR2(100) 
COUNT               NUMBER(38)  
	 */
	
	private int csno;
	private String cname;
	private String clocal;
	private String ctime;
	private String cip;
	private int cmaker;
	private String cmakername;
	private String image;
	private int count;
	
	
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
	
	
}
