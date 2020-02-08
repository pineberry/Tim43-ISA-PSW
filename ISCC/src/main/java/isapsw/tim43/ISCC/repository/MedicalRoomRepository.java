package isapsw.tim43.ISCC.repository;

import isapsw.tim43.ISCC.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import isapsw.tim43.ISCC.model.MedicalRoom;
import java.util.List;

public interface MedicalRoomRepository extends JpaRepository<MedicalRoom, Long> {

    List<MedicalRoom> findMedicalRoomsByRoomName(String roomName);

    List<MedicalRoom> findMedicalRoomsByRoomNumber(int number);

    List<MedicalRoom> findMedicalRoomByRoomNameAndClinic(String roomName, Clinic clinic);

    List<MedicalRoom> findMedicalRoomByRoomNumberAndClinic(int number, Clinic clinic);

    List<MedicalRoom> findMedicalRoomByClinic(Clinic clinic);

}
