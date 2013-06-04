package fm.weplayfootball.persistence.domain.common;


public class SearchCondition extends PagingDomain {

	private String srchType;
	private String srchValue;
	private String sortName;

	public String getSrchType() {
		return srchType;
	}
	public void setSrchType(String srchType) {
		this.srchType = srchType;
	}
	public String getSrchValue() {
		return srchValue;
	}
	public void setSrchValue(String srchValue) {
		this.srchValue = srchValue;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}


}
