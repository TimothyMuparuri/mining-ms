package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.nharire.miningms.constants.ApiConstants;
import za.co.nharire.miningms.model.equipment.EquipmentDTO;
import za.co.nharire.miningms.model.equipment.EquipmentDeleteDTO;
import za.co.nharire.miningms.service.EquipmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class  EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping("equipment/save")
    public ResponseEntity<EquipmentDTO> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {


        log.info("SAVING MINE", equipmentDTO.toString());

        EquipmentDTO equipmentDTO1 = equipmentService.saveEquipment(equipmentDTO);

        if (equipmentDTO1 != null) {
            return new ResponseEntity<>(equipmentDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/equipment/all")
    public ResponseEntity<List<EquipmentDTO>> getAllEquipment() {
        log.info("GETTING ALL EQUIPMENT IN DB ");

        List<EquipmentDTO> equipmentDTOListList = equipmentService.getAllEquipment();

        if (equipmentDTOListList != null) {
            return new ResponseEntity<>(equipmentDTOListList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Getting a equipment by equipmentid
    @GetMapping("/equipment/get/{equipmentid}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable Long equipmentid) {
        log.info("GETTING A EQUIPMENT IN DB ");

        EquipmentDTO equipmentDTO1 = equipmentService.getEquipmentById(equipmentid);

        if (equipmentDTO1 != null) {
            return new ResponseEntity<>(equipmentDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/equipment/update/{equipmentid}")
    public ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable Long equipmentid, @RequestBody EquipmentDTO equipmentDetails) {
        log.info("UPDATING A EQUIPMENT IN DB ");

        EquipmentDTO equipmentDTO1 = equipmentService.updateEquipment(equipmentid, equipmentDetails);

        if (equipmentDTO1 != null) {
            return new ResponseEntity<>(equipmentDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/equipment/delete/{equipmentid}")
    public ResponseEntity<EquipmentDeleteDTO> deleteEquipment(@PathVariable Long equipmentid) {
        log.info("DELETING A EQUIPMENT IN DB ");

        EquipmentDeleteDTO equipmentDeleteDTO = equipmentService.deleteEquipment(equipmentid);

        if (equipmentDeleteDTO.getMessage().equals(ApiConstants.DELETE_SUCCESS)) {
            return new ResponseEntity<>(equipmentDeleteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(equipmentDeleteDTO, HttpStatus.NOT_FOUND);
        }


    }

}


