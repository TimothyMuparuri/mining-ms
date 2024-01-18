package za.co.nharire.miningms.model.logbook;

import jakarta.persistence.Column;
import lombok.Data;
import za.co.nharire.miningms.model.humanresources.HumanResourcesDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data

public class LogbookDTO implements Serializable {
    private Long logid;
    private Long activityID;
    private Long employeeID;
    private String duration;
    private String description;
    private Boolean isActive;
    private String eventDate;
    private String eventType;


}
