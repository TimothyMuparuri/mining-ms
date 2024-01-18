package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.nharire.miningms.model.humanresources.HumanResourcesDTO;
import za.co.nharire.miningms.service.HumanResourcesService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor

public class HumanResourceController {
    private final HumanResourcesService humanResourcesService;

    @PostMapping("humanresource/save")
    public ResponseEntity<HumanResourcesDTO> saveHumanResource(@RequestBody HumanResourcesDTO humanResourcesDTO) {

        log.info("SAVING HUMANRESOURCES", humanResourcesDTO.toString());

        HumanResourcesDTO humanResourcesDTO1 = humanResourcesService.saveHumanResource(humanResourcesDTO);
        if (humanResourcesDTO1 != null) {
            return new ResponseEntity<>(humanResourcesDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/humanresources/all")
    public ResponseEntity<List<HumanResourcesDTO>> getAllHumanResources() {
        log.info("GETTING ALL HUMANRESOURCES IN DB ");

        List<HumanResourcesDTO> humanResourcesDTOList = humanResourcesService.getAllHumanResources();

        if (humanResourcesDTOList != null) {
            return new ResponseEntity<>(humanResourcesDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/humanresources/update/{employeeid}")
    public ResponseEntity<HumanResourcesDTO> updateHumanResources(@PathVariable Long employeeid, @RequestBody HumanResourcesDTO isActive) {
        log.info("UPDATING A HUMANRESOURCE IN DB ");

        HumanResourcesDTO humanResourcesDTO1 = humanResourcesService.updateHumanResources(employeeid, isActive);

        if (humanResourcesDTO1 != null) {
            return new ResponseEntity<>(humanResourcesDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
