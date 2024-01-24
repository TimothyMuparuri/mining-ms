package za.co.nharire.miningms.model.vehicle.currentstate;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data

public class CurrentStateDTO implements Serializable {
    private Long currentStateID;
    private Long mileage;
    private LocalDate lastService;
    private String engineStatus;
    private boolean requiresMaintenance;
    private String overallStatus;


}
