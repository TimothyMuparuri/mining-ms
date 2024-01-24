package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.nharire.miningms.service.MaintenanceService;

@Slf4j
@RequiredArgsConstructor
@RestController

public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping("maintenance/save")
    public void saveMaintenance() {
        log.info("SAVING MINE");

        maintenanceService.saveMaintenance();

    }
}
