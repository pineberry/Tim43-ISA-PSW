package isapsw.tim43.ISCC.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookOperationDTO {
    private Long procedureId;
    private Long roomId;
    private List<Long> doctorsId;

    BookOperationDTO() {
    }
}
