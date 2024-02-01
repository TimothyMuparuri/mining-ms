package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.nharire.miningms.model.activity.ActivityRequirementDTO;
import za.co.nharire.miningms.service.ActivityRequirementService;

@Slf4j
@RequiredArgsConstructor
@RestController

public class ActivityRequirementController {

    private final ActivityRequirementService activityRequirementService;

    @PostMapping("activityrequirement/save")
    public ResponseEntity<ActivityRequirementDTO> saveActivityRequirement(@RequestBody ActivityRequirementDTO activityDTO) {
        log.info("SAVING ACTIVITYREQUIREMENT", activityDTO.toString());

        ActivityRequirementDTO activityRequirementDTO1 = activityRequirementService.saveActivityRequirement(activityDTO);
        if (activityRequirementDTO1 != null) {
            return new ResponseEntity<>(activityRequirementDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
