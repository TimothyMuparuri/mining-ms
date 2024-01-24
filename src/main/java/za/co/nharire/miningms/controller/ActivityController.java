package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.nharire.miningms.model.activity.ActivityDTO;
import za.co.nharire.miningms.service.ActivityService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor

public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("activity/save")
    public ResponseEntity<ActivityDTO> saveActivity(@RequestBody ActivityDTO activityDTO) {
        log.info("SAVING ACTIVITY", activityDTO.toString());

        ActivityDTO activityDTO1 = activityService.saveActivity(activityDTO);
        if (activityDTO1 != null) {
            return new ResponseEntity<>(activityDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //get all activities
    @GetMapping("activity/all")
    public ResponseEntity<List<ActivityDTO>> getAllEquipment() {
        log.info("GETTING ALL EQUIPMENT IN DB ");

        List<ActivityDTO> activityDTOList = activityService.getAllActivities();

        if (activityDTOList != null) {
            return new ResponseEntity<>(activityDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
