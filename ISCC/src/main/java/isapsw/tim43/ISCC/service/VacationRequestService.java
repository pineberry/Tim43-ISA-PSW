package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.DoctorDTO;
import isapsw.tim43.ISCC.dto.VacationRequestDTO;
import isapsw.tim43.ISCC.model.VacationRequest;
import isapsw.tim43.ISCC.repository.VacationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacationRequestService {

    @Autowired
    VacationRequestRepository vacationRequestRepository;

    @Autowired
    DoctorService doctorService;

    @Autowired
    NurseService nurseService;

    @Autowired
    EmailService emailService;

    public VacationRequest save(VacationRequest vacationRequest) { return vacationRequestRepository.save(vacationRequest); }
    public VacationRequest findById(Long id) { return vacationRequestRepository.findById(id).orElse(null); }
    public List<VacationRequest> findAll() {return vacationRequestRepository.findAll(); }

    public VacationRequestDTO addVacationRequest(VacationRequestDTO vacationRequestDTO) {
        VacationRequest vacationRequest = dtoToModel(vacationRequestDTO);

        return modelToDto(save(vacationRequest));
    }

    public VacationRequestDTO findOne(Long id) {
        VacationRequest vacationRequest = findById(id);

        if(vacationRequest == null) {
            return  null;
        }

        return modelToDto(vacationRequest);
    }

    public List<VacationRequestDTO> allVacationRequests() {
        List<VacationRequest> vacationRequests = findAll();

        List<VacationRequestDTO> vacationRequestDTOS = new ArrayList<>();

        for (VacationRequest vacReq : vacationRequests) {
            VacationRequestDTO vacationRequestDTO = modelToDto(vacReq);
            vacationRequestDTOS.add(vacationRequestDTO);
        }

        return vacationRequestDTOS;
    }

    public List<VacationRequestDTO> allRequestByClinic(Long id) {
        List<VacationRequest> vacationRequests = findAll();

        List<VacationRequestDTO> vacationRequestDTOS = new ArrayList<>();
        for (VacationRequest vacReq : vacationRequests) {
            if(vacReq.getDoctor() != null) {
                if(vacReq.getDoctor().getClinic().getId() == id) {
                    vacationRequestDTOS.add(modelToDto(vacReq));
                }
            }
            if(vacReq.getNurse() != null ){
                if(vacReq.getNurse().getClinic().getId() == id) {
                    vacationRequestDTOS.add(modelToDto(vacReq));
                }
            }
        }
        return vacationRequestDTOS;
    }

    public VacationRequestDTO update(VacationRequestDTO vacationRequestDTO) {
        VacationRequest vacationRequest = findById(vacationRequestDTO.getId());

        vacationRequest.setStartingDate(vacationRequestDTO.getStartingDate());
        vacationRequest.setEndingDate(vacationRequestDTO.getEndingDate());
        vacationRequest.setStatus(vacationRequestDTO.getStatus());

        return modelToDto(save(vacationRequest));
    }

    public VacationRequestDTO accept(VacationRequestDTO vacationRequestDTO) throws InterruptedException {
        VacationRequest vacationRequest = findById(vacationRequestDTO.getId());

        if (vacationRequest.getStatus() != 0) {
            return null;
        }

        // Status 1 -> zahtev prihvacen
        vacationRequest.setStatus(1);

        String emailContent;

        if (vacationRequest.getDoctor() != null) {
            emailContent = "Dear " + vacationRequest.getDoctor().getFirstName()
                                + " " + vacationRequest.getDoctor().getLastName()
                                + ", your vacation request has been accepted!\n\n"
                                + "Vacation starts on " + vacationRequest.getStartingDate() + " and ends on "
                                + vacationRequest.getEndingDate();
        } else {
            emailContent = "Dear " + vacationRequest.getNurse().getFirstName()
                    + " " + vacationRequest.getNurse().getLastName()
                    + ", your vacation request has been accepted!\n\n"
                    + "Vacation starts on " + vacationRequest.getStartingDate() + " and ends on "
                    + vacationRequest.getEndingDate();
        }

        emailService.sendNotificationAsync("isa.pws43@gmail.com", emailContent);
        vacationRequestRepository.save(vacationRequest);
        return new VacationRequestDTO(vacationRequest);
    }

    public VacationRequestDTO deny(VacationRequestDTO vacationRequestDTO, String comment) throws InterruptedException {
        VacationRequest vacationRequest = findById(vacationRequestDTO.getId());

        if (vacationRequest == null || comment == null || comment.isEmpty()){
            return null;
        }

        if (vacationRequest.getStatus() != 0) {
            return null;
        }

        // Status 2 -> zahtev odbijen
        vacationRequest.setStatus(2);

        String emailContent;

        if (vacationRequest.getDoctor() != null) {
            emailContent = "Dear " + vacationRequest.getDoctor().getFirstName()
                    + " " + vacationRequest.getDoctor().getLastName()
                    + ", Your vacation request has been denied!\n\n"
                    + "Reason for such decision: \n\n" + comment;
        } else {
            emailContent = "Dear " + vacationRequest.getNurse().getFirstName()
                    + " " + vacationRequest.getNurse().getLastName()
                    + ", Your vacation request has been denied!\n\n"
                    + "Reason for such decision: \n\n" + comment;
        }

        emailService.sendNotificationAsync("isa.pws43@gmail.com", emailContent);
        vacationRequestRepository.save(vacationRequest);
        return new VacationRequestDTO(vacationRequest);
    }

    public VacationRequestDTO modelToDto(VacationRequest vacationRequest) {
        VacationRequestDTO vacationRequestDTO = new VacationRequestDTO();

        vacationRequestDTO.setId(vacationRequest.getId());
        if(vacationRequest.getDoctor() != null) {
            vacationRequestDTO.setDoctorDTO(new DoctorDTO(vacationRequest.getDoctor()));
        } else
        {
            vacationRequestDTO.setDoctorDTO(null);
        }
        if(vacationRequest.getNurse() != null) {
            vacationRequestDTO.setNurseDTO(nurseService.modelToDto(vacationRequest.getNurse()));
        } else {
            vacationRequestDTO.setNurseDTO(null);
        }
        vacationRequestDTO.setStartingDate(vacationRequest.getStartingDate());
        vacationRequestDTO.setEndingDate(vacationRequest.getEndingDate());
        vacationRequestDTO.setStatus(vacationRequest.getStatus());

        return vacationRequestDTO;
    }

    public VacationRequest dtoToModel(VacationRequestDTO vacationRequestDTO) {
        VacationRequest vacationRequest = new VacationRequest();

        if(vacationRequestDTO.getDoctorDTO() != null) {
            vacationRequest.setDoctor(doctorService.findOne(vacationRequestDTO.getDoctorDTO().getId()));
        } else {
            vacationRequest.setDoctor(null);
        }
        if(vacationRequestDTO.getNurseDTO() != null) {
            vacationRequest.setNurse(nurseService.findById(vacationRequestDTO.getNurseDTO().getId()));
        } else {
            vacationRequest.setNurse(null);
        }
        vacationRequest.setStartingDate(vacationRequestDTO.getStartingDate());
        vacationRequest.setEndingDate(vacationRequestDTO.getEndingDate());
        vacationRequest.setStatus(vacationRequestDTO.getStatus());

        return vacationRequest;
    }
}
