package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.ProcedureType;
import lombok.Data;

@Data
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
}
