package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.ProcedureType;

public class ProcedureTypeDTO {

    private long id;
    private String typeName;
    private String typeDescription;

    public ProcedureTypeDTO(){}

    public ProcedureTypeDTO(long id, String typeName, String typeDescription) {
        this.id = id;
        this.typeName = typeName;
        this.typeDescription = typeDescription;
    }

    public ProcedureTypeDTO(ProcedureType procedureType){
        this(procedureType.getId(), procedureType.getTypeName(), procedureType.getTypeDescription());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
