package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.Medicine;
import lombok.Data;

@Data
public class MedicineDTO {
    private String name;
    private String code;

    public MedicineDTO(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public MedicineDTO(Medicine medicine) {
        this(medicine.getName(), medicine.getCode());
    }

    public MedicineDTO() {}

}
