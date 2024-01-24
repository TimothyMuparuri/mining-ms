package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.nharire.miningms.model.schedule.ScheduleDTO;
import za.co.nharire.miningms.service.ScheduleService;

@RequiredArgsConstructor
@Slf4j
@RestController

public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("schedule/save")
    public ResponseEntity<ScheduleDTO> saveSchedule(@RequestBody ScheduleDTO scheduleDTO){
        log.info("SAVING SCHEDULE", scheduleDTO.toString());

        ScheduleDTO scheduleDTO1 =scheduleService.saveSchedule(scheduleDTO);
        if (scheduleDTO1 != null) {
            return new ResponseEntity<>(scheduleDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
