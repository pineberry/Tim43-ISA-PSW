package isapsw.tim43.ISCC.controller;

import isapsw.tim43.ISCC.dto.VacationRequestDTO;
import isapsw.tim43.ISCC.model.VacationRequest;
import isapsw.tim43.ISCC.service.VacationRequestService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vacation/request")
public class VacationRequestController {

    @Autowired
    VacationRequestService vacationRequestService;

    /**
     * Dobavlja iz baze zahtjev sa prosljedjenim id-jem
     * @param id Id trazenog zahtjeva
     * @return Trazeni zahtjev ako postoji ili null
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<VacationRequestDTO> getVacationRequest(@PathVariable Long id) {

        VacationRequestDTO vacationRequestDTO = vacationRequestService.findOne(id);

        if(vacationRequestDTO != null) {
            return new ResponseEntity<>(vacationRequestDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Dobavlja sve zahtjeve iz baze
     * @return lista svih zahtjeva u bazi
     */
    @GetMapping(value = "/all")
    public ResponseEntity<List<VacationRequestDTO>> getVacationRequests() {
        List<VacationRequestDTO> vacationRequestDTOS = vacationRequestService.allVacationRequests();

        return new ResponseEntity<>(vacationRequestDTOS, HttpStatus.OK);
    }

    /**
     * Upisivanje novog zahtjeva u bazu prosledjenog kao parametar requestDTO
     * @param requestDTO DTO objekat sa koji zelimo ubaciti u bazu
     * @return Objekat ako je uspjesno upisano ili null
     */
    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<VacationRequestDTO> addVacationRequest(@RequestBody VacationRequestDTO requestDTO) {
        VacationRequestDTO vacationRequestDTO = vacationRequestService.addVacationRequest(requestDTO);

        if(vacationRequestDTO != null) {
            return new ResponseEntity<>(vacationRequestDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Izmjena zahtjeva iz baze sa id-jem prosljedjenog unutar DTO objekta requestDTO
     * @param requestDTO DTO objekat na koji zelimo da promjenimo postojeci
     * @return promjenjen zahtjev ili null
     */
    @PutMapping(value = "/edit", consumes = "application/json")
    public ResponseEntity<VacationRequestDTO> editVacationRequest(@RequestBody VacationRequestDTO requestDTO) {
        VacationRequestDTO vacationRequestDTO = vacationRequestService.update(requestDTO);

        if (vacationRequestDTO != null) {
            return new ResponseEntity<>(vacationRequestDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Dobavlja sve zahtjeve saposlenih u klinici ciji je id prosledjen kao parametar
     * @param id klinike za koju se traze zahtjevi
     * @return lista zahtjeva za trazenu kliniku
     */
    @GetMapping(value = "/clinic/{id}")
    public ResponseEntity<List<VacationRequestDTO>> getByClinic(@PathVariable("id") Long id) {
        List<VacationRequestDTO> vacationRequestDTOS = vacationRequestService.allRequestByClinic(id);

        if(vacationRequestDTOS != null) {
            return new ResponseEntity<>(vacationRequestDTOS, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/accept", consumes = "application/json")
    public ResponseEntity<VacationRequestDTO> accept(@RequestBody VacationRequestDTO vacationRequestDTO) throws InterruptedException {
        vacationRequestDTO = vacationRequestService.accept(vacationRequestDTO);
        if (vacationRequestDTO != null) {
            return new ResponseEntity<>(vacationRequestDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/deny", consumes = "application/json")
    public ResponseEntity<VacationRequestDTO> deny(@RequestBody VacationRequestDTO vacationRequestDTO,
                                                        @RequestParam(name = "comment") String comment) throws InterruptedException {
        vacationRequestDTO = vacationRequestService.deny(vacationRequestDTO, comment);
        if (vacationRequestDTO != null) {
            return new ResponseEntity<>(vacationRequestDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
