package fm.weplayfootball.persistence.domain.common;


public class SearchCondition extends PagingDomain {

	private String srchType;
	private String srchValue;

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


}
