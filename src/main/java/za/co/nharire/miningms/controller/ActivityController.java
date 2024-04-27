package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.nharire.miningms.constants.ApiConstants;
import za.co.nharire.miningms.model.activity.ActivityDTO;
import za.co.nharire.miningms.model.activity.ActivityDeleteDTO;
import za.co.nharire.miningms.model.vehicle.properties.VehicleDTO;
import za.co.nharire.miningms.model.vehicle.properties.VehicleDeleteDTO;
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

    //Getting a vehicle by vehicleid
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/activity/get/{activityid}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable Long activityId) {
        log.info("GETTING A VEHICLE IN DB ");

        ActivityDTO activityDTO = activityService.getActivityById(activityId);

        if (activityDTO != null) {
            return new ResponseEntity<>(activityDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //code to update the activity
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/activity/update/{activityid}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable Long activityID, @RequestBody ActivityDTO activityDTO) {
        log.info("UPDATING A VEHICLE IN DB ");

        ActivityDTO activityDTO1 = activityService.updateActivity(activityID, activityDTO);

        if (activityDTO1 != null) {
            return new ResponseEntity<>(activityDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //code to delete an activity
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/activity/delete/{activityid}")
    public ResponseEntity<ActivityDeleteDTO> deleteActivity(@PathVariable Long activityID) {
        log.info("DELETING A VEHICLE IN DB ");

        ActivityDeleteDTO activity = activityService.deleteActivity(activityID);

        if (activity.getMessage().equals(ApiConstants.DELETE_SUCCESS)) {
            return new ResponseEntity<>(activity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(activity, HttpStatus.NOT_FOUND);
        }
    }
}
