package isapsw.tim43.ISCC.model;

public class ProcedureType {
	
	private long id;
	private String typeName;
	private String typeDescription;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public ProcedureType(String typeName, String typeDescription) {
		super();
		this.typeName = typeName;
		this.typeDescription = typeDescription;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	
	

}
