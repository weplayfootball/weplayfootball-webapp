package fm.weplayfootball.persistence.domain;

import fm.weplayfootball.persistence.domain.common.PagingDomain;

public class ClubInfoList extends PagingDomain{
	
	private int csno;
	private String cname;
	private String clocal;
	private int month;
	private int year;
	private int total;
	private int members;
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
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getMembers() {
		return members;
	}
	public void setMembers(int members) {
		this.members = members;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
