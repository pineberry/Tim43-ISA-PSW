package isapsw.tim43.ISCC.dto;

import isapsw.tim43.ISCC.model.Medicine;
import lombok.Data;

@Data
public class MedicineDTO {
    private Long id;
    private String name;
    private String code;

    public MedicineDTO(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public MedicineDTO(Medicine medicine) {
        this(medicine.getId(), medicine.getName(), medicine.getCode());
    }

    public MedicineDTO() {}

}
