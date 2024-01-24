package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.model.specification.SpecificationDTO;
import za.co.nharire.miningms.model.vehicle.properties.Vehicle;
import za.co.nharire.miningms.model.vehicle.properties.VehicleDTO;
import za.co.nharire.miningms.ropositories.vehicle.VehicleRepository;

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
}
