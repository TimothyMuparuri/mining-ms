package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.nharire.miningms.model.vehicle.properties.VehicleDTO;
import za.co.nharire.miningms.service.VehicleService;

@RequiredArgsConstructor
@RestController
@Slf4j

public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/vehicle/save")
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {

        log.info("SAVING MINE", vehicleDTO.toString());

        VehicleDTO vehicleDTO1 = vehicleService.saveVehicle(vehicleDTO);
        if (vehicleDTO1 != null) {
            return new ResponseEntity<>(vehicleDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
