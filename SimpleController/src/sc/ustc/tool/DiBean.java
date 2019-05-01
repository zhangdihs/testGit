package sc.ustc.tool;

import java.util.List;

public class DiBean {
	private String idname;
	private String idclass;
	private List<Dixml> dixml;
	public String getIdname() {
		return idname;
	}
	public void setIdname(String idname) {
		this.idname = idname;
	}
	public String getIdclass() {
		return idclass;
	}
	public void setIdclass(String idclass) {
		this.idclass = idclass;
	}
	public List<Dixml> getDixml() {
		return dixml;
	}
	public void setDixml(List<Dixml> dixml) {
		this.dixml = dixml;
	}
	
	

}
