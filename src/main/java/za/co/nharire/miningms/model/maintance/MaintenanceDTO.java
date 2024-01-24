package za.co.nharire.miningms.model.maintance;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
@Data

public class MaintenanceDTO implements Serializable {
    private Long maintenanceID;
    private LocalDate maintenanceDate;
    private Long vehicleID;
    private String description;
    private Long cost;
    private String duration;
    private Long currentStateID;
    private Long periodTillMaintenance;

}
