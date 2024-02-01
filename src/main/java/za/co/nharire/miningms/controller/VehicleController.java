package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.nharire.miningms.constants.ApiConstants;
import za.co.nharire.miningms.model.vehicle.properties.VehicleDTO;
import za.co.nharire.miningms.model.vehicle.properties.VehicleDeleteDTO;
import za.co.nharire.miningms.service.VehicleService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j


public class VehicleController {

    private final VehicleService vehicleService;

    @CrossOrigin(origins = "http://localhost:5500")
    @PostMapping("/vehicle/save")
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {

        log.info("SAVING VEHICLE", vehicleDTO.toString());

        VehicleDTO vehicleDTO1 = vehicleService.saveVehicle(vehicleDTO);
        if (vehicleDTO1 != null) {
            return new ResponseEntity<>(vehicleDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5500")
    @GetMapping("/vehicle/all")
    public ResponseEntity<List<VehicleDTO>> getAllMines() {
        log.info("GETTING ALL VEHICLES IN DB ");

        List<VehicleDTO> vehicleList = vehicleService.getAllVehicles();

        if (vehicleList != null) {
            return new ResponseEntity<>(vehicleList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Getting a vehicle by vehicleid
    @GetMapping("/vehicle/get/{vehicleid}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long vehicleid) {
        log.info("GETTING A VEHICLE IN DB ");

        VehicleDTO vehicle1 = vehicleService.getVehicleById(vehicleid);

        if (vehicle1 != null) {
            return new ResponseEntity<>(vehicle1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/vehicle/update/{vehicleid}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long vehicleid, @RequestBody VehicleDTO vehicleDTOName) {
        log.info("UPDATING A VEHICLE IN DB ");

        VehicleDTO vehicle1 = vehicleService.updateVehicle(vehicleid, vehicleDTOName);

        if (vehicle1 != null) {
            return new ResponseEntity<>(vehicle1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/vehicle/delete/{vehicleid}")
    public ResponseEntity<VehicleDeleteDTO> deleteVehicle(@PathVariable Long vehicleid) {
        log.info("DELETING A VEHICLE IN DB ");

        VehicleDeleteDTO vehicle1 = vehicleService.deleteVehicle(vehicleid);

        if (vehicle1.getMessage().equals(ApiConstants.DELETE_SUCCESS)) {
            return new ResponseEntity<>(vehicle1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(vehicle1, HttpStatus.NOT_FOUND);
        }
    }
}
