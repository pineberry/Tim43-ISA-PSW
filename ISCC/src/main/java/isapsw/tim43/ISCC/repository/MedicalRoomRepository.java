package isapsw.tim43.ISCC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isapsw.tim43.ISCC.model.MedicalRoom;
import java.util.List;

public interface MedicalRoomRepository extends JpaRepository<MedicalRoom, Long> {

    List<MedicalRoom> findMedicalRoomsByRoomName(String roomName);

    List<MedicalRoom> findMedicalRoomsByRoomNumber(int number);

}
