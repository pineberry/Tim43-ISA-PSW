package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.Diagnosis;
import lombok.Data;

@Data
public class DiagnosisDTO {

    private String name;
    private String latinName;
    private String code;

    public DiagnosisDTO(String name, String latinName, String code) {
        this.name = name;
        this.latinName = latinName;
        this.code = code;
    }

    public DiagnosisDTO(Diagnosis diagnosis) {
        this(diagnosis.getName(), diagnosis.getLatinName(), diagnosis.getCode());
    }

    public DiagnosisDTO() {}

}
