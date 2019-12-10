package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.MedicalRoomDTO;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.repository.MedicalRoomRepository;

import java.util.*;

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

	public MedicalRoomDTO update(MedicalRoomDTO medicalRoomDTO) {
		if(medicalRoomDTO.getRoomName() == null || medicalRoomDTO.getRoomName().isEmpty()) {
			return null;
		}

		MedicalRoom medicalRoom = findOne(medicalRoomDTO.getId());

		if (medicalRoom.getMedicalProcedures().size() != 0) {
			return null;
		}

		medicalRoom.setRoomName(medicalRoomDTO.getRoomName());
		medicalRoom.setRoomNumber(medicalRoomDTO.getRoomNumber());

		medicalRoom = medicalRoomRepository.save(medicalRoom);

		return new MedicalRoomDTO(medicalRoom);
	}

	public boolean remove(Long id) {
		MedicalRoom medicalRoom = findOne(id);
		if (medicalRoom == null || medicalRoom.getMedicalProcedures().size() != 0){
			return false;
		}
		medicalRoomRepository.deleteById(id);
		return true;
	}

	public List<MedicalRoomDTO> searchRoomsByName(String roomName, Date searchDate){
		List<MedicalRoom> rooms = new ArrayList<MedicalRoom>();

		if (roomName == null || roomName.isEmpty() || roomName.equals("all")){
			rooms = medicalRoomRepository.findAll();
		}else{
			rooms = medicalRoomRepository.findMedicalRoomsByRoomName(roomName);
		}

		return formRoomList(rooms, searchDate);
	}

	public List<MedicalRoomDTO> searchRoomsByNumber(int roomNumber, Date searchDate){
		List<MedicalRoom> rooms = new ArrayList<MedicalRoom>();

		if (roomNumber < 0){
			rooms = medicalRoomRepository.findAll();
		}else{
			rooms = medicalRoomRepository.findMedicalRoomsByRoomNumber(roomNumber);
		}

		return formRoomList(rooms, searchDate);
	}

	private List<MedicalRoomDTO> formRoomList(List<MedicalRoom> rooms, Date searchDate){
		List<MedicalRoomDTO> roomsDTO = new ArrayList<MedicalRoomDTO>();
		List<Date> scheduledDates = new ArrayList<Date>();
		Map<Date, List<String>> datesAndTimes = new HashMap<Date, List<String>>();

		for (MedicalRoom room: rooms){
			MedicalRoomDTO medicalRoomDTO = new MedicalRoomDTO(room);
			formDateList(searchDate, scheduledDates, room, datesAndTimes);
			if (scheduledDates.isEmpty()){
				medicalRoomDTO.setFirstFreeDate(searchDate);
			}else {
				medicalRoomDTO.setFirstFreeDate(findFirstFreeDate(searchDate, datesAndTimes));
			}
			medicalRoomDTO.setScheduledDates(scheduledDates);
			roomsDTO.add(medicalRoomDTO);
		}

		return roomsDTO;
	}

	private void formDateList(Date searchDate, List<Date> scheduledDates, MedicalRoom room,
							  		Map<Date, List<String>> map){
		for (MedicalProcedure medicalProcedure: room.getMedicalProcedures()){
			if (medicalProcedure.getDateOfProcedure().compareTo(searchDate) >= 0){
				scheduledDates.add(medicalProcedure.getDateOfProcedure());
				String procedureTime = medicalProcedure.getStartTime() + ":" + medicalProcedure.getEndTime();
				if (map.containsKey(medicalProcedure.getDateOfProcedure())){
					map.get(medicalProcedure.getDateOfProcedure()).add(procedureTime);
				}else {
					List<String> l = new ArrayList<String>();
					l.add(procedureTime);
					map.put(medicalProcedure.getDateOfProcedure(), l);
				}
			}
		}
	//	Collections.sort(scheduledDates);
	}

	private Date findFirstFreeDate(Date searchDate, Map<Date, List<String>> map){

		while(!map.containsKey(searchDate) || !hasFreeTime(map.get(searchDate))) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(searchDate);
			cal.add(Calendar.DATE, 1);
			searchDate = cal.getTime();
		}

		return searchDate;
	}

	private boolean hasFreeTime(List<String> stringList){
		boolean retVal = false;
		int freeTime = 5400;

		for (String times: stringList) {
			String[] timesAndMinutes = times.split(":");
			int startHour = Integer.parseInt(timesAndMinutes[0]);
			int startMinute = Integer.parseInt(timesAndMinutes[1]);
			int endHour = Integer.parseInt(timesAndMinutes[2]);
			int endMinute = Integer.parseInt(timesAndMinutes[3]);

			if (endMinute > startMinute) {
				freeTime = freeTime - (endHour - startHour) * 60 - (endMinute - startMinute);
			} else {
				freeTime = freeTime - (endHour - startHour) * 60 - (startMinute - endMinute);
			}
		}
		if (freeTime > 60 ){
			retVal = true;
		}

		return retVal;
	}

}
