package isapsw.tim43.ISCC.model;

public class Diagnosis {

	private long id;
	private String name;
	private String latinName;
	private String code;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLatinName() {
		return latinName;
	}
	
	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public Diagnosis(long id, String name, String latinName, String code) {
		super();
		this.id = id;
		this.name = name;
		this.latinName = latinName;
		this.code = code;
	}

	public Diagnosis() {
		super();
	}
}
