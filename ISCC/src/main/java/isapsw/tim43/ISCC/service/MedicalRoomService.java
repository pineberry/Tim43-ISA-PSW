package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.MedicalRoomDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.MedicalProcedure;
import isapsw.tim43.ISCC.repository.MedicalProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.model.MedicalRoom;
import isapsw.tim43.ISCC.repository.MedicalRoomRepository;

import java.util.*;

@Service
public class MedicalRoomService {
	
	@Autowired
	private MedicalRoomRepository medicalRoomRepository;

	@Autowired
	private ClinicService clinicService;
	
	public MedicalRoomDTO save(MedicalRoomDTO medicalRoomDTO) {
		if(medicalRoomDTO.getRoomName() == null || medicalRoomDTO.getRoomName().isEmpty()) {
			return null;
		}

		MedicalRoom medicalRoom = new MedicalRoom();
		medicalRoom.setRoomName(medicalRoomDTO.getRoomName());
		medicalRoom.setRoomNumber(medicalRoomDTO.getRoomNumber());
		Clinic clinic = clinicService.findOne(medicalRoomDTO.getClinic().getId());
		medicalRoom.setClinic(clinic);

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
		Clinic clinic = clinicService.findOne(medicalRoomDTO.getClinic().getId());
		medicalRoom.setClinic(clinic);

		medicalRoom = medicalRoomRepository.save(medicalRoom);

		return new MedicalRoomDTO(medicalRoom);
	}

	public boolean remove(long id) {
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
		if (rooms != null || !rooms.isEmpty()) {
			for (MedicalRoom room : rooms) {
				MedicalRoomDTO medicalRoomDTO = new MedicalRoomDTO(room);
				formDateList(searchDate, scheduledDates, room, datesAndTimes);        /* Formiranje liste zauzetih datuma */
				if (scheduledDates.isEmpty()) {
					medicalRoomDTO.setFirstFreeDate(searchDate);        /* Ako je sala slobodna, datum pretrage odgovara */
				} else {
					//	medicalRoomDTO.setFirstFreeDate(findFirstFreeDate(searchDate, datesAndTimes));
					medicalRoomDTO.setFirstFreeDate(searchDate);
				}
				medicalRoomDTO.setScheduledDates(scheduledDates);
				roomsDTO.add(medicalRoomDTO);
			}
		}

		return roomsDTO;
	}

	/*
	 * Za svaku salu, koja odgovara trazenom broju / imenu, se formira lista zakazanih pregleda.
	 * Ukoliko se datum pregleda poklapa sa trazenim datumom pretrage ili je veci, on se dodaje
	 * u listu zakazanih datuma - scheduledDates. Takodje, formira se i mapa, sa kljucem za svaki datum,
	 * a vrednost mape je lista satnica pregleda za taj dan u formatu startTime:endTime.
	 *
	 */
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

	/*
	 * Za mapu zauzeca sale, ondnosno za svaki datum i listu zauzetih satnica tog dana,se trazi prvi slobodan termin,
	 * u vidu datuma. Datum ce se inkrementirati dok se nedodje do dana kada sala nije zakazana, ili ima makar jedan
	 * slobodan termin (proverava se u metodi hasFreeTime).
	 */
	private Date findFirstFreeDate(Date searchDate, Map<Date, List<String>> map){

		while(!map.containsKey(searchDate) || !hasFreeTime(map.get(searchDate))) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(searchDate);
			cal.add(Calendar.DATE, 1);
			searchDate = cal.getTime();
		}

		return searchDate;
	}

	/*
	 * Algoritam za racunanje slobodnog termina v1.0
	 * Metoda prima listu satnica za odgovarajauci dan i provera da li tog dana ima slobodne satnice za pregled.
	 * Pretpostavka je da klinika radu 9h dnevno (540 minuta) i da je potrebno imati makar 60 minuta za pregled.
	 */
	private boolean hasFreeTime(List<String> stringList){
		boolean retVal = false;
		int freeTime = 540;

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
		if (freeTime > 59 ){
			retVal = true;
		}

		return retVal;
	}

}
