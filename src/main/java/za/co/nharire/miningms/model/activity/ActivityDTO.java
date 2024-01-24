package za.co.nharire.miningms.model.activity;

import lombok.Data;
import za.co.nharire.miningms.model.humanresources.HumanResourcesDTO;
import za.co.nharire.miningms.model.vehicle.properties.VehicleDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data

public class ActivityDTO implements Serializable {
    private Long activityID;
    private Long scheduleID;
    private String activityName;
    private String startDate;
    private String endDate;
    private String description;
    private Long requirementId;
    List<HumanResourcesDTO> availableHumanResources;
    List<HumanResourcesDTO> humanResourcesDTOList;
    List<VehicleDTO> vehicleDTOList;
    private String duration;
    private Boolean isDone;
    private Long employeeID;
    private Long vehicleId;
    private Long mileage;


}
