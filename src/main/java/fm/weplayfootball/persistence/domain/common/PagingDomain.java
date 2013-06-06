package fm.weplayfootball.persistence.domain.common;

import java.io.Serializable;

public class PagingDomain implements Serializable {
	
	private static final long serialVersionUID = -1337665389942207640L;

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
