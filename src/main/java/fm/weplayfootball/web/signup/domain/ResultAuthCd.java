package fm.weplayfootball.web.signup.domain;

public class ResultAuthCd {

	private String status;
	private String message;
	
	public ResultAuthCd(String status){
		this.status = status;
	}
	public ResultAuthCd(String status, String message){
		this.status = status;
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
