package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.nharire.miningms.constants.ApiConstants;
import za.co.nharire.miningms.model.logbook.LogbookDTO;
import za.co.nharire.miningms.model.logbook.LogbookDeleteDTO;
import za.co.nharire.miningms.service.LogbookService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController

public class LogbookController {
    private final LogbookService logbookService;

    @PostMapping("logbook/save")
    public ResponseEntity<LogbookDTO> saveLogbook(@RequestBody LogbookDTO logbookDTO) {

        log.info("SAVING LOGBOOK", logbookDTO.toString());

        LogbookDTO logbookDTO1 = logbookService.saveLogbook(logbookDTO);

        if (logbookDTO1 != null) {
            return new ResponseEntity<>(logbookDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logbook/all")
    public ResponseEntity<List<LogbookDTO>> getAllLogbook() {
        log.info("GETTING ALL LOGBOOK IN DB ");

        List<LogbookDTO> logbookDTOList = logbookService.getAllLogbooks();

        if (logbookDTOList != null) {
            return new ResponseEntity<>(logbookDTOList, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Getting a logbook by id
    @GetMapping("/logbook/get/{id}")
    public ResponseEntity<LogbookDTO> getLogbookById(@PathVariable Long id) {
        log.info("GETTING A LOGBOOK IN DB ");

        LogbookDTO logbook = logbookService.getLogbookById(id);

        if (logbook != null) {
            return new ResponseEntity<>(logbook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/logbook/update/{id}")
    public ResponseEntity<LogbookDTO> updateLogbook(@PathVariable Long id, @RequestBody LogbookDTO logbookDTO) {
        log.info("UPDATING A LOGBOOK IN DB ");

        LogbookDTO logbookDTO1 = logbookService.updateLogbook(id, logbookDTO);

        if (logbookDTO != null) {
            return new ResponseEntity<>(logbookDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/logbook/delete/{id}")
    public ResponseEntity<LogbookDeleteDTO> deleteLogbook(@PathVariable Long id) {
        log.info("DELETING A LOGBOOK IN DB ");

        LogbookDeleteDTO logbookDeleteDTO = logbookService.deleteLogbook(id);

        if (logbookDeleteDTO.getMessage().equals(ApiConstants.DELETE_SUCCESS)) {
            return new ResponseEntity<>(logbookDeleteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(logbookDeleteDTO, HttpStatus.NOT_FOUND);
        }
    }
}

