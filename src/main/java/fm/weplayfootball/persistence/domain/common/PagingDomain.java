package fm.weplayfootball.persistence.domain.common;

public class PagingDomain {
	
	private int pageSize = 20;
	private int pageNum;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	
}
