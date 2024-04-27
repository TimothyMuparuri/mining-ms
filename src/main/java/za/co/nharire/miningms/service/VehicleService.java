package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.constants.ApiConstants;
import za.co.nharire.miningms.model.specification.SpecificationDTO;
import za.co.nharire.miningms.model.vehicle.properties.Vehicle;
import za.co.nharire.miningms.model.vehicle.properties.VehicleDTO;
import za.co.nharire.miningms.model.vehicle.properties.VehicleDeleteDTO;
import za.co.nharire.miningms.repositories.vehicle.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service

public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final SpecificationService specificationService;

    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO){

        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleDTO,vehicle);

        SpecificationDTO specificationDTO = new SpecificationDTO();
        BeanUtils.copyProperties(vehicleDTO,specificationDTO);

        log.info(" Save to DB");
        Vehicle vehicle1 = vehicleRepository.save(vehicle);
        specificationService.saveSpecification(specificationDTO);

        BeanUtils.copyProperties(vehicle1,vehicleDTO);
        return vehicleDTO;
    }

    public List<VehicleDTO> getAllVehicles() {
        log.info("IN SERVICE ");

        List<VehicleDTO> vehicleDTOList = new ArrayList<>();

        List<Vehicle> vehicleList = vehicleRepository.findAll();
        for (Vehicle vehicle : vehicleList) {
            VehicleDTO vehicleDTO = new VehicleDTO();
            BeanUtils.copyProperties(vehicle, vehicleDTO);
            vehicleDTOList.add(vehicleDTO);
        }
        return vehicleDTOList;
    }

    // get vehicle by id rest api
    public VehicleDTO getVehicleById(Long vehicleid) {
        log.info("IN SERVICE ");
        VehicleDTO vehicleDTO = new VehicleDTO();
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleid);

        if (vehicle.isEmpty()) {
            return null;
        } else {
            BeanUtils.copyProperties(vehicle.get(), vehicleDTO);
            return vehicleDTO;
        }
    }

    // updating the Db
    public VehicleDTO updateVehicle(Long vehicleid, VehicleDTO vehicleDetails) {
        log.info("IN SERVICE ");
        VehicleDTO vehicleDTO = new VehicleDTO();
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleid);

        if (vehicle.isEmpty()) {
            return null;
        } else {

            Vehicle vehicle1 = vehicle.get();
            vehicle1.setMileage(vehicleDetails.getMileage());
            vehicle1.setMake(vehicleDetails.getMake());
            vehicle1.setColour(vehicleDetails.getColour());
            vehicle1.setYear(vehicleDetails.getYear());
            vehicle1.setEngineNumber(vehicleDetails.getEngineNumber());
            vehicle1.setModel(vehicleDetails.getModel());
            vehicleRepository.save(vehicle1);
            BeanUtils.copyProperties(vehicle.get(), vehicleDTO);
            return vehicleDTO;
        }

    }

    public VehicleDeleteDTO deleteVehicle(Long vehicleid) {
        log.info("IN SERVICE ");

        VehicleDeleteDTO vehicleDeleteDTO = new VehicleDeleteDTO();

        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleid);
        if (vehicle.isEmpty()) {
            log.error(" No ID found for this ID " + vehicleid);

            vehicleDeleteDTO.setMessage(ApiConstants.DELETE_FAILURE + vehicleid);
            return vehicleDeleteDTO;
        } else {
            vehicleRepository.deleteById(vehicleid);
        }
        vehicleDeleteDTO.setMessage(ApiConstants.DELETE_SUCCESS);
        return vehicleDeleteDTO;
    }
}
