package fm.weplayfootball.web.club.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class ClubForm {
	
	@NotEmpty( message = "클럽 이름을 입력해야 합니다.")
	private String cname;

	private String clocalDo;
	
	@NotEmpty( message = "지역정보를 선택해야 합니다.")
	private String clocalSi;
	
	private String description;

	private String image;
	private MultipartFile emblemImage;
	
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public MultipartFile getEmblemImage() {
		return emblemImage;
	}
	public void setEmblemImage(MultipartFile emblemImage) {
		this.emblemImage = emblemImage;
	}
	public String getClocalDo() {
		return clocalDo;
	}
	public void setClocalDo(String clocalDo) {
		this.clocalDo = clocalDo;
	}
	public String getClocalSi() {
		return clocalSi;
	}
	public void setClocalSi(String clocalSi) {
		this.clocalSi = clocalSi;
	}
	
	
}
