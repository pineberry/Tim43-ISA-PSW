package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.MedicalRoomDTO;
import isapsw.tim43.ISCC.model.ProcedureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.repository.MedicalRoomRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRoomService {
	
	@Autowired
	private MedicalRoomRepository medicalRoomRepository;
	
	public MedicalRoomDTO save(MedicalRoomDTO medicalRoomDTO) {
		if(medicalRoomDTO.getRoomName() == null || medicalRoomDTO.getRoomName().isEmpty()) {
			return null;
		}

		MedicalRoom medicalRoom = new MedicalRoom();
		medicalRoom.setRoomName(medicalRoomDTO.getRoomName());
		medicalRoom.setRoomNumber(medicalRoomDTO.getRoomNumber());

		medicalRoom = medicalRoomRepository.save(medicalRoom);

		return new MedicalRoomDTO(medicalRoom);
	}

	public MedicalRoom findOne(long id){
		return medicalRoomRepository.findById(id).orElseGet(null);
	}

	public List<MedicalRoomDTO> findAll(){
		List<MedicalRoom> medicalRooms = medicalRoomRepository.findAll();
		List<MedicalRoomDTO> medicalRoomDTOList = new ArrayList<MedicalRoomDTO>();

		for(MedicalRoom medicalRoom: medicalRooms){
			MedicalRoomDTO medicalRoomDTO = new MedicalRoomDTO(medicalRoom);
			medicalRoomDTOList.add(medicalRoomDTO);
		}

		return medicalRoomDTOList;
	}
	
	public void remove(Long id) {
		medicalRoomRepository.deleteById(id);
	}
}
