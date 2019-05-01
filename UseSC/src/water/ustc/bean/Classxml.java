package water.ustc.bean;

import java.util.List;

public class Classxml {
	private String name;
	private String table;
	private String idString;
	private List<Column> column;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}
	public List<Column> getColumn() {
		return column;
	}
	public void setColumn(List<Column> column) {
		this.column = column;
	}
	
	
	

}
