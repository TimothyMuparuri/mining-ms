package za.co.nharire.miningms.service;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.model.maintance.Maintenance;
import za.co.nharire.miningms.model.vehicle.properties.Vehicle;
import za.co.nharire.miningms.model.vehicle.properties.VehicleDTO;
import za.co.nharire.miningms.ropositories.maintenance.MaintenanceRepository;
import za.co.nharire.miningms.ropositories.vehicle.VehicleRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Component

public class MaintenanceService {

    private static final Logger log = LoggerFactory.getLogger(MaintenanceService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long maintenanceID = 0L;

    @Scheduled(fixedRate = 2628002880L)
    public void saveMaintenance() {

        log.info("The time is now {}", dateFormat.format(new Date()));

        Maintenance maintenance = new Maintenance();

        List<VehicleDTO> putVehicleList = new ArrayList<>();

        List<Vehicle> getVehicleList = vehicleRepository.findAll();

        for (Vehicle vehicle : getVehicleList) {
            if (vehicle.getMileage() % 200 == 0) {
                VehicleDTO vehicleDTO = new VehicleDTO();
                BeanUtils.copyProperties(vehicle, vehicleDTO);
                putVehicleList.add(vehicleDTO);
            } else {
                log.error("VEHICLE IS NOT READY FOR MAINTENANCE");

            }
        }

        for (VehicleDTO vehicleDTO : putVehicleList) {

            log.info(" Save to DB");
            maintenance.setMaintenanceID(maintenanceID);
            maintenance.setDescription("periodic maintenance");
            maintenance.setDuration("50 seconds");
            maintenance.setVehicleID(vehicleDTO.getVehicleID());
            maintenance.setMaintenanceDate(LocalDate.now());
            maintenanceID++;
            maintenanceRepository.save(maintenance);

        }
    }
}
