package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.MedicalRoomDTO;
import isapsw.tim43.ISCC.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isapsw.tim43.ISCC.repository.MedicalRoomRepository;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MedicalRoomService {
	
	@Autowired
	private MedicalRoomRepository medicalRoomRepository;

	@Autowired
	private ClinicService clinicService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private ClinicAdministratorService clinicAdministratorService;
	
	public MedicalRoomDTO save(MedicalRoomDTO medicalRoomDTO, Long id) {
		if (medicalRoomDTO.getRoomName() == null || medicalRoomDTO.getRoomName().isEmpty()) {
			return null;
		}

		ClinicAdministrator clinicAdministrator = clinicAdministratorService.findOne(id);

		if (clinicAdministrator == null) {
			return null;
		}

		MedicalRoom medicalRoom = new MedicalRoom();
		medicalRoom.setRoomName(medicalRoomDTO.getRoomName());
		medicalRoom.setRoomNumber(medicalRoomDTO.getRoomNumber());
		medicalRoom.setClinic(clinicAdministrator.getClinic());

		medicalRoom = medicalRoomRepository.save(medicalRoom);

		return new MedicalRoomDTO(medicalRoom);
	}

	public MedicalRoom findOne(long id){
		return medicalRoomRepository.findById(id).orElseGet(null);
	}

	public List<MedicalRoomDTO> findAll() {
		List<MedicalRoom> medicalRooms = medicalRoomRepository.findAll();
		List<MedicalRoomDTO> medicalRoomDTOList = new ArrayList<MedicalRoomDTO>();

		for (MedicalRoom medicalRoom: medicalRooms) {
			MedicalRoomDTO medicalRoomDTO = new MedicalRoomDTO(medicalRoom);
			medicalRoomDTOList.add(medicalRoomDTO);
		}

		return medicalRoomDTOList;
	}

	public List<MedicalRoomDTO> findByClinic(Long id) {
		ClinicAdministrator clinicAdministrator = clinicAdministratorService.findOne(id);

		if (clinicAdministrator == null) {
			return null;
		}

		List<MedicalRoomDTO> medicalRoomDTOList = new ArrayList<MedicalRoomDTO>();

		for (MedicalRoom medicalRoom: clinicAdministrator.getClinic().getMedicalRooms()) {
			medicalRoomDTOList.add(new MedicalRoomDTO(medicalRoom));
		}

		return medicalRoomDTOList;
	}

	public MedicalRoomDTO update(MedicalRoomDTO medicalRoomDTO) {
		if (medicalRoomDTO.getRoomName() == null || medicalRoomDTO.getRoomName().isEmpty()) {
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
		if (medicalRoom == null || medicalRoom.getMedicalProcedures().size() != 0) {
			return false;
		}
		medicalRoomRepository.deleteById(id);
		return true;
	}

	public List<MedicalRoomDTO> searchRoomsByName(String roomName, Date searchDate, Long id) {
		List<MedicalRoom> rooms;

		ClinicAdministrator clinicAdministrator = clinicAdministratorService.findOne(id);

		if (clinicAdministrator == null) {
			return null;
		}

		if (roomName == null || roomName.isEmpty() || roomName.equals("all")) {
			rooms = medicalRoomRepository.findMedicalRoomByClinic(clinicAdministrator.getClinic());
		} else {
			rooms = medicalRoomRepository.findMedicalRoomByRoomNameAndClinic(roomName, clinicAdministrator.getClinic());
		}

		return formRoomList(rooms, searchDate);
	}

	public List<MedicalRoomDTO> searchRoomsByNumber(int roomNumber, Date searchDate, Long id) {
		List<MedicalRoom> rooms;

		ClinicAdministrator clinicAdministrator = clinicAdministratorService.findOne(id);

		if (clinicAdministrator == null) {
			return null;
		}

		if (roomNumber < 0){
			rooms = medicalRoomRepository.findMedicalRoomByClinic(clinicAdministrator.getClinic());
		} else {
			rooms = medicalRoomRepository.findMedicalRoomByRoomNumberAndClinic(roomNumber,
																					clinicAdministrator.getClinic());
		}

		return formRoomList(rooms, searchDate);
	}

	private List<MedicalRoomDTO> formRoomList(List<MedicalRoom> rooms, Date searchDate) {
		List<MedicalRoomDTO> roomsDTO = new ArrayList<MedicalRoomDTO>();
		List<Date> scheduledDates = new ArrayList<Date>();
		Map<String, List<String>> datesAndTimes = new HashMap<String, List<String>>();
		if (rooms != null || !rooms.isEmpty()) {
			for (MedicalRoom room : rooms) {
				MedicalRoomDTO medicalRoomDTO = new MedicalRoomDTO(room);
				formDateList(searchDate, scheduledDates, room, datesAndTimes);        // Formiranje liste zauzetih datuma /
				if (scheduledDates.isEmpty()) {
					medicalRoomDTO.setFirstFreeDate(searchDate);        // Ako je sala slobodna, datum pretrage odgovara /
				} else {
					medicalRoomDTO.setFirstFreeDate(findFirstFreeDate(searchDate, datesAndTimes));
				//	medicalRoomDTO.setFirstFreeDate(searchDate);
				}
				medicalRoomDTO.setScheduledDates(scheduledDates);
				roomsDTO.add(medicalRoomDTO);
			}
		}

		return roomsDTO;
	}

	/**
	 * Za svaku salu, koja odgovara trazenom broju / imenu, se formira lista zakazanih pregleda.
	 * Ukoliko se datum pregleda poklapa sa trazenim datumom pretrage ili je veci, on se dodaje
	 * u listu zakazanih datuma - scheduledDates. Takodje, formira se i mapa, sa kljucem za svaki datum,
	 * a vrednost mape je lista satnica pregleda za taj dan u formatu startTime:endTime.
	 *
	 */
	private void formDateList(Date searchDate, List<Date> scheduledDates, MedicalRoom room,
							  		Map<String, List<String>> map) {
		for (MedicalProcedure medicalProcedure: room.getMedicalProcedures()) {
			if (medicalProcedure.getDateOfProcedure().compareTo(searchDate) >= 0) {
				scheduledDates.add(medicalProcedure.getDateOfProcedure());
				String procedureTime = medicalProcedure.getStartTime() + ":" + medicalProcedure.getEndTime();
				String keyDate = applyDateFormat(medicalProcedure.getDateOfProcedure());
				if (map.containsKey(keyDate)) {
					map.get(keyDate).add(procedureTime);
				} else {
					List<String> l = new ArrayList<String>();
					l.add(procedureTime);
					map.put(keyDate, l);
				}
			}
		}
	//	Collections.sort(scheduledDates);
	}

	/**
	 * Za mapu zauzeca sale, ondnosno za svaki datum i listu zauzetih satnica tog dana,se trazi prvi slobodan termin,
	 * u vidu datuma. Datum ce se inkrementirati dok se ne dodje do dana kada sala nije zakazana ili ima makar jedan
	 * slobodan termin (proverava se u metodi hasFreeTime).
	 */
	private Date findFirstFreeDate(Date searchDate, Map<String, List<String>> map) {
		while (map.containsKey(applyDateFormat(searchDate)) && !hasFreeTime(map.get(applyDateFormat(searchDate)))) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(searchDate);
			cal.add(Calendar.DATE, 1);
			searchDate = cal.getTime();
		}

		return searchDate;
	}

	/**
	 * Algoritam za racunanje slobodnog termina v1.0
	 * Metoda prima listu satnica za odgovarajauci dan i provera da li tog dana ima slobodne satnice za pregled.
	 * Pretpostavka je da klinika radu 16h dnevno (od 7 do 23, sto je 540 minuta) i da je potrebno imati makar
	 * 60 minuta za pregled.
	 */
	private boolean hasFreeTime(List<String> stringList) {
		boolean retVal = false;
		int freeTime = 960;

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
		if (freeTime > 59 ) {
			retVal = true;
		}

		return retVal;
	}

	private String applyDateFormat(Date date) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String stringDate = simpleDateFormat.format(date);
		return stringDate;
	}

	/**
	 * Za prosledjenu listu pregleda i datum, pokupi sve satnice pregleda koji se odrzavaju tog dana
	 */
	public List<String> getTimesForChosenDate(Date date, List<MedicalProcedure> medicalProcedures) {
	//	List<MedicalProcedure> medicalProcedures = room.getMedicalProcedures();
		List<String> times = new ArrayList<String>();

		for (MedicalProcedure medicalProcedure: medicalProcedures) {
			if (medicalProcedure.getDateOfProcedure().compareTo(date) == 0) {
				times.add(medicalProcedure.getStartTime() + ":" + medicalProcedure.getEndTime());
			}
		}

		return times;
	}

	/**
	 * Za prosledjenu satnicu novog pregleda (procedureTime) u formatu startTime:endTime proveri da li se preklapa sa
	 * satnicom nekog od postojecih termina za neki odredjeni datum, neke odredjene sale
	 */
	public boolean overlapingTimes(List<String> roomTimes, String procedureTime) {
		boolean overlap = false;
		String[] timesAndMinutesNew = procedureTime.split(":"); // Satnica novog pregleda
		int startHourNew = Integer.parseInt(timesAndMinutesNew[0]);
		int startMinuteNew = Integer.parseInt(timesAndMinutesNew[1]);
		int endHourNew = Integer.parseInt(timesAndMinutesNew[2]);
		int endMinuteNew = Integer.parseInt(timesAndMinutesNew[3]);

		for (String times: roomTimes) {
			String[] timesAndMinutes = times.split(":"); // Satnice postojeceg pregleda
			int startHour = Integer.parseInt(timesAndMinutes[0]);
			int startMinute = Integer.parseInt(timesAndMinutes[1]);
			int endHour = Integer.parseInt(timesAndMinutes[2]);
			int endMinute = Integer.parseInt(timesAndMinutes[3]);

			if (startHourNew < startHour) {
				// Ako novi pregled pocinje pre pocetka postojeceg, provera da li se i zavrsava pre njega
				if (endHourNew > startHour || (endHourNew == startHour && endMinuteNew > endMinute)) {
					return true;
				}
			} else if (startHourNew > startHour) {
				// Ako satnica novog pregleda pocinje nakon pocetka postojeceg, proveri da li pocinje pre njegovog zavrsetka
				if (startHourNew < endHour || (startHourNew == endHour && startMinuteNew < endMinute)) {
					return true;
				}
			} else {
				// U slucaju da pregled pocinje u istom satu, a traje duze od pocetak postojeceg, imamo prekplapanje
				if (endHourNew != startHour || endMinuteNew > startMinute) {
					return true;
				}
			}
		}

		return overlap;
	}

	/**
	 * Prolazimo kroz sve sale, za svaku salu se, za trazeni datum, ispituje da li ima slobodnog termina u trajanju
	 * pregleda kome se treba dodeliti sala, provera se vrisi u metodi getAvailableTime
	 */
	public MedicalProcedure findAvailableAppoinment(MedicalProcedure medicalProcedure) {
		Doctor doctor = medicalProcedure.getDoctor();
		List<MedicalRoom> rooms = medicalRoomRepository.findMedicalRoomByClinic(doctor.getClinic());

		// Ako ima neka slobodna sala u trazenoj satnici izaberi tu salu
		if (bookAnyRoomAtRequestedTime(medicalProcedure, rooms)) {
			return medicalProcedure;
		}

		// Za svaku salu se proverava ima li neka satnica za koju moze da se rezervise pregled
		while (true) {
			for (MedicalRoom room: rooms) {
				if (getAvailableTime(medicalProcedure, room)) {
					return medicalProcedure;
				}
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(medicalProcedure.getDateOfProcedure());
			cal.add(Calendar.DATE, 1);
			medicalProcedure.setDateOfProcedure(cal.getTime());
		}
	}

	public boolean bookAnyRoomAtRequestedTime(MedicalProcedure medicalProcedure, List<MedicalRoom> rooms) {
		String reqeustedTime = medicalProcedure.getStartTime() + ":" + medicalProcedure.getEndTime();
		for (MedicalRoom room: rooms) {
			if (!overlapingTimes(getTimesForChosenDate(medicalProcedure.getDateOfProcedure(), room.getMedicalProcedures()),
									reqeustedTime)) {
				medicalProcedure.setMedicalRoom(room);
				return true;
			}
		}
		return false;
	}

	/**
	 * Pcevsi od podrazumevanog radnog vremena klinike, se trazi slobodan termin pregleda u prosledjenoj sali, ukoliko
	 * se termin pronadje, proverava se da li je trazeni doktor slobodan, ukoliko nije, trazi se da je slobodan neki
	 * drugi doktor, iste specijalizacije
	 */
	public boolean getAvailableTime(MedicalProcedure medicalProcedure, MedicalRoom room) {
		boolean retVal = false;
		List<String> times = getTimesForChosenDate(medicalProcedure.getDateOfProcedure(), room.getMedicalProcedures());
		String procedureTime = medicalProcedure.getStartTime() + ":" + medicalProcedure.getEndTime();

		if (times.isEmpty()) {
			medicalProcedure.setMedicalRoom(room);
			return true;
		}

		String[] timesAndMinutes = procedureTime.split(":");

		int actualStartHour = Integer.parseInt(timesAndMinutes[0]);
		int actualStartMinute = Integer.parseInt(timesAndMinutes[1]);
		int actualEndHour = Integer.parseInt(timesAndMinutes[2]);
		int actualEndMinute = Integer.parseInt(timesAndMinutes[3]);

		int startHour = 7; // Pretpostavka da klinika radi od 7h
		int startMinute = 0;
		int endHour = 7;
		int endMinute;

		// Ako je trajanje pregleda duze od sat vremena podesi satnicu i minute, inace samo minute
		if ((actualEndHour - actualStartHour) * 60 - (actualStartMinute - actualEndMinute) >= 60) {
			endHour = endHour + (actualEndHour - actualStartHour);
			endMinute = Math.abs(actualStartMinute - actualEndMinute);
		} else {
			int difference = actualEndMinute - actualStartMinute;
			endMinute = (difference > 0)? difference : 60 + difference;
		}

		while (endHour <= 23) {
			String timeForOverlaping = startHour + ":" + startMinute +
										":" + endHour + ":" + endMinute;
			if (!overlapingTimes(times, timeForOverlaping)) { // Provera da li je slobodna sala
				Doctor doctor = doctorService.getAvailableDoctor(medicalProcedure, timeForOverlaping); // Slobodan doktor
				if (doctor != null) {
					medicalProcedure.setMedicalRoom(room);
					medicalProcedure.setDoctor(doctor);
					if (startMinute < 10)
						medicalProcedure.setStartTime(startHour + ":0" + startMinute);
					else
						medicalProcedure.setStartTime(startHour + ":" + startMinute);

					if (endMinute < 10)
						medicalProcedure.setEndTime(endHour + ":0" + endMinute);
					else
						medicalProcedure.setEndTime(endHour + ":" + endMinute);

					return true;
				}
			} else {
				// Povecaj interval satnice za 20 minuta
				if (startMinute + 20 >= 60) {
					startHour += 1;
					startMinute = startMinute + 20 - 60;
				} else {
					startMinute += 20;
				}

				if (endMinute + 20 >= 60) {
					endHour += 1;
					endMinute = endMinute + 20 - 60;
				} else {
					endMinute += 20;
				}
			}
		}

		return retVal;
	}

}
