package isapsw.tim43.ISCC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.repository.MedicalRoomRepository;

@Service
public class MedicalRoomService {
	
	@Autowired
	private MedicalRoomRepository medicalRoomRepository;
	
	public MedicalRoom save(MedicalRoom medicalRoom) {
		return medicalRoomRepository.save(medicalRoom);
	}
	
	public void remove(Long id) {
		medicalRoomRepository.deleteById(id);
	}
}
