package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.ProcedureType;
import lombok.Data;

@Data
public class ProcedureTypeDTO {

    private long id;
    private String typeName;
    private String typeDescription;
    private float price;

    public ProcedureTypeDTO(){}

    public ProcedureTypeDTO(long id, String typeName, String typeDescription, float price) {
        this.id = id;
        this.typeName = typeName;
        this.typeDescription = typeDescription;
        this.price = price;
    }

    public ProcedureTypeDTO(ProcedureType procedureType){
        this(procedureType.getId(), procedureType.getTypeName(), procedureType.getTypeDescription(), procedureType.getPrice());
    }
}
