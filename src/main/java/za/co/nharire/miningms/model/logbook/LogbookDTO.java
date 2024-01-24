package za.co.nharire.miningms.model.logbook;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data

public class LogbookDTO implements Serializable {
    private Long logid;
    private Long activityID;
    private Long employeeID;
    private String duration;
    private String description;
    private String eventDate;
    private String eventType;
    private Long currentStateID;
    private Long mileage;
    private LocalDate lastService;
    private String engineStatus;
    private boolean requiresMaintenance;
    private String overallStatus;

}
