package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.Diagnosis;
import lombok.Data;

@Data
public class DiagnosisDTO {

    private Long id;
    private String name;
    private String latinName;
    private String code;

    public DiagnosisDTO(Long id, String name, String latinName, String code) {
        this.id = id;
        this.name = name;
        this.latinName = latinName;
        this.code = code;
    }

    public DiagnosisDTO(Diagnosis diagnosis) {
        this(diagnosis.getId(), diagnosis.getName(), diagnosis.getLatinName(), diagnosis.getCode());
    }

    public DiagnosisDTO() {}

}
