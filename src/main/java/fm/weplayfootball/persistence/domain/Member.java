package fm.weplayfootball.persistence.domain;

import java.io.Serializable;

public class Member implements Serializable {

	private static final long serialVersionUID = 8751282105532159742L;
	
	private int msno;
	private String mname;
	private String memail;
	private String mpasswd;
	private String muid;
	private String mtel;
	private int mclub;
	private String mlevel;
	private String mimage;
	private int mimagesize;
	private String mdate;
	private String mip;
	private String mposition;
	private String mlocal;
	private String mintro;
	private String cmlevel;
	
	public int getMsno() {
		return msno;
	}
	public void setMsno(int msno) {
		this.msno = msno;
	}
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
	public String getMpasswd() {
		return mpasswd;
	}
	public void setMpasswd(String mpasswd) {
		this.mpasswd = mpasswd;
	}
	public String getMuid() {
		return muid;
	}
	public void setMuid(String muid) {
		this.muid = muid;
	}
	public String getMtel() {
		return mtel;
	}
	public void setMtel(String mtel) {
		this.mtel = mtel;
	}
	public int getMclub() {
		return mclub;
	}
	public void setMclub(int mclub) {
		this.mclub = mclub;
	}
	public String getMlevel() {
		return mlevel;
	}
	public void setMlevel(String mlevel) {
		this.mlevel = mlevel;
	}
	public String getMimage() {
		return mimage;
	}
	public void setMimage(String mimage) {
		this.mimage = mimage;
	}
	public int getMimagesize() {
		return mimagesize;
	}
	public void setMimagesize(int mimagesize) {
		this.mimagesize = mimagesize;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getMip() {
		return mip;
	}
	public void setMip(String mip) {
		this.mip = mip;
	}
	public String getMposition() {
		return mposition;
	}
	public void setMposition(String mposition) {
		this.mposition = mposition;
	}
	public String getMlocal() {
		return mlocal;
	}
	public void setMlocal(String mlocal) {
		this.mlocal = mlocal;
	}
	public String getMintro() {
		return mintro;
	}
	public void setMintro(String mintro) {
		this.mintro = mintro;
	}
	public String getCmlevel() {
		return cmlevel;
	}
	public void setCmlevel(String cmlevel) {
		this.cmlevel = cmlevel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    public String toString()
    {
        return (new StringBuilder("MemberDTO [msno=")).append(msno).append(", mname=").append(mname).append(", memail=").append(memail).append(", mpasswd=").append(mpasswd).append(", muid=").append(muid).append(", mtel=").append(mtel).append(", mclub=").append(mclub).append(", mlevel=").append(mlevel).append(", mimage=").append(mimage).append(", mimagesize=").append(mimagesize).append(", mdate=").append(mdate).append(", mip=").append(mip).append(", mposition=").append(mposition).append(", mlocal=").append(mlocal).append(", mintro=").append(mintro).append(", cmlevel=").append(cmlevel).append("]").toString();
    }

}
