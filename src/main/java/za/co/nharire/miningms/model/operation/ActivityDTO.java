package za.co.nharire.miningms.model.operation;

import lombok.Data;
import za.co.nharire.miningms.model.humanresources.HumanResourcesDTO;

import java.io.Serializable;
import java.util.List;

@Data

public class ActivityDTO implements Serializable {
    private Long activityID;
    private Long employeeID;
    private String activityName;
    private Long equipmentID;
    private String startDate;
    private String endDate;
    private String description;
    List<HumanResourcesDTO> availableHumanResources;

}
