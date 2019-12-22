package isapsw.tim43.ISCC.service;

import isapsw.tim43.ISCC.dto.ClinicAdministratorDTO;
import isapsw.tim43.ISCC.dto.UserDTO;
import isapsw.tim43.ISCC.model.Clinic;
import isapsw.tim43.ISCC.model.ClinicAdministrator;
import isapsw.tim43.ISCC.repository.ClinicAdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicAdministratorService {

    @Autowired
    ClinicAdministratorRepository clinicAdministratorRepository;

    @Autowired
    ClinicService clinicService;

    public ClinicAdministrator save(ClinicAdministrator clinicAdministrator) {
        return clinicAdministratorRepository.save(clinicAdministrator);
    }

    public void remove(Long id) {
        clinicAdministratorRepository.deleteById(id);
    }

	public ClinicAdministrator findUserByEmailAddress(String emailAddress) {
		ClinicAdministrator clinicAdmin = null;

		for (ClinicAdministrator c : clinicAdministratorRepository.findAll()) {
			if(c.getEmail().equals(emailAddress))
			{
				clinicAdmin = c;
				break;
			}
		}
		return clinicAdmin;
	}

	public ClinicAdministrator findOne(Long id){
    	return clinicAdministratorRepository.findById(id).orElseGet(null);
	}

	public ClinicAdministratorDTO findById(Long id){
		ClinicAdministrator clinicAdministrator = findOne(id);

		if (clinicAdministrator == null) {
			return null;
		}

		return new ClinicAdministratorDTO(clinicAdministrator);
	}

	public ClinicAdministratorDTO findByEmail(String email){
    	ClinicAdministrator clinicAdministrator = clinicAdministratorRepository.findClinicAdministratorByEmail(email);

    	if (clinicAdministrator == null) {
    		return null;
		}

    	return new ClinicAdministratorDTO(clinicAdministrator);
	}

	public ClinicAdministratorDTO save(ClinicAdministratorDTO clinicAdministratorDTO){
    	ClinicAdministrator clinicAdministrator = findOne(clinicAdministratorDTO.getId());
    	clinicAdministrator.setFirstName(clinicAdministratorDTO.getFirstName());
    	clinicAdministrator.setLastName(clinicAdministratorDTO.getLastName());
    	clinicAdministrator.setAddress(clinicAdministratorDTO.getAddress());
    	clinicAdministrator.setCity(clinicAdministratorDTO.getCity());
    	clinicAdministrator.setState(clinicAdministratorDTO.getState());
    	clinicAdministrator.setEmail(clinicAdministratorDTO.getEmail());
    	clinicAdministrator.setPhoneNumber(clinicAdministratorDTO.getPhoneNumber());

    	if (!clinicAdministrator.getClinic().getName().equals(clinicAdministratorDTO.getClinicName())) {
			Clinic clinic = clinicService.findByName(clinicAdministratorDTO.getClinicName());
			clinicAdministrator.setClinic(clinic);
		}

		clinicAdministrator = clinicAdministratorRepository.save(clinicAdministrator);
    	return new ClinicAdministratorDTO(clinicAdministrator);
	}

	public UserDTO changePassword(UserDTO userDTO){
    	ClinicAdministrator clinicAdministrator = clinicAdministratorRepository.
																	findClinicAdministratorByEmail(userDTO.getEmail());

    	if (clinicAdministrator == null || !userDTO.getPassword().equals(userDTO.getPasswordF())) {
    		return null;
		}

    	clinicAdministrator.setPassword(userDTO.getPassword());
    	clinicAdministrator.setFirstLogin(false);
    	clinicAdministratorRepository.save(clinicAdministrator);
    	return userDTO;
	}
}

