package fm.weplayfootball.persistence.domain;

public class Grounds {
	
    private int gsno;
    private String glocaldo;
    private String glocalsi;
    private String gclass;
    private String gname;
    private String gfield;
    private String glocaladd;
    private String gtel;
    private int gwidth;
    private int gheight;
    private int garea;
    private String glat;
    private String glon;
    private String gmanager;
    private String gimage;
    private long gimagesize;
    private int gcount;
    private String homeurl;
    private String gfax;
    private int reserve;
    private String guse;
	public int getGsno() {
		return gsno;
	}
	public void setGsno(int gsno) {
		this.gsno = gsno;
	}
	public String getGlocaldo() {
		return glocaldo;
	}
	public void setGlocaldo(String glocaldo) {
		this.glocaldo = glocaldo;
	}
	public String getGlocalsi() {
		return glocalsi;
	}
	public void setGlocalsi(String glocalsi) {
		this.glocalsi = glocalsi;
	}
	public String getGclass() {
		return gclass;
	}
	public void setGclass(String gclass) {
		this.gclass = gclass;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGfield() {
		return gfield;
	}
	public void setGfield(String gfield) {
		this.gfield = gfield;
	}
	public String getGlocaladd() {
		return glocaladd;
	}
	public void setGlocaladd(String glocaladd) {
		this.glocaladd = glocaladd;
	}
	public String getGtel() {
		return gtel;
	}
	public void setGtel(String gtel) {
		this.gtel = gtel;
	}
	public int getGwidth() {
		return gwidth;
	}
	public void setGwidth(int gwidth) {
		this.gwidth = gwidth;
	}
	public int getGheight() {
		return gheight;
	}
	public void setGheight(int gheight) {
		this.gheight = gheight;
	}
	public int getGarea() {
		return garea;
	}
	public void setGarea(int garea) {
		this.garea = garea;
	}
	public String getGlat() {
		return glat;
	}
	public void setGlat(String glat) {
		this.glat = glat;
	}
	public String getGlon() {
		return glon;
	}
	public void setGlon(String glon) {
		this.glon = glon;
	}
	public String getGmanager() {
		return gmanager;
	}
	public void setGmanager(String gmanager) {
		this.gmanager = gmanager;
	}
	public String getGimage() {
		return gimage;
	}
	public void setGimage(String gimage) {
		this.gimage = gimage;
	}
	public long getGimagesize() {
		return gimagesize;
	}
	public void setGimagesize(long gimagesize) {
		this.gimagesize = gimagesize;
	}
	public int getGcount() {
		return gcount;
	}
	public void setGcount(int gcount) {
		this.gcount = gcount;
	}
	public String getHomeurl() {
		return homeurl;
	}
	public void setHomeurl(String homeurl) {
		this.homeurl = homeurl;
	}
	public String getGfax() {
		return gfax;
	}
	public void setGfax(String gfax) {
		this.gfax = gfax;
	}
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public String getGuse() {
		return guse;
	}
	public void setGuse(String guse) {
		this.guse = guse;
	}
    
    public String toString()
    {
        return (new StringBuilder("groundsDTO [gsno=")).append(gsno).append(", glocaldo=").append(glocaldo).append(", glocalsi=").append(glocalsi).append(", gclass=").append(gclass).append(", gname=").append(gname).append(", gfield=").append(gfield).append(", glocaladd=").append(glocaladd).append(", gtel=").append(gtel).append(", gwidth=").append(gwidth).append(", gheight=").append(gheight).append(", garea=").append(garea).append(", glat=").append(glat).append(", glon=").append(glon).append(", gmanager=").append(gmanager).append(", gimage=").append(gimage).append(", gimagesize=").append(gimagesize).append(", gcount=").append(gcount).append(", homeurl=").append(homeurl).append(", gfax=").append(gfax).append(", reserve=").append(reserve).append(", guse=").append(guse).append("]").toString();
    }
    
}
