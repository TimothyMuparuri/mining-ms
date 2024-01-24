package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.nharire.miningms.model.vehicle.currentstate.CurrentStateDTO;
import za.co.nharire.miningms.service.CurrentStateService;

@Slf4j
@RequiredArgsConstructor
@RestController

public class CurrentStateController {

    private final CurrentStateService currentStateService;

    @PostMapping("currentstate/save")
    public ResponseEntity<CurrentStateDTO> saveCurrentState(@RequestBody CurrentStateDTO currentStateDTO){

        log.info("SAVING ACTIVITY", currentStateDTO.toString());
        CurrentStateDTO currentStateDTO1= currentStateService.saveCurrentState(currentStateDTO);

        if (currentStateDTO1 != null) {
            return new ResponseEntity<>(currentStateDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
