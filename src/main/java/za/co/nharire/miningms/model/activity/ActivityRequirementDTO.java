package za.co.nharire.miningms.model.activity;

import lombok.Data;
import za.co.nharire.miningms.model.humanresources.HumanResources;
import za.co.nharire.miningms.model.vehicle.properties.Vehicle;

import java.io.Serializable;
import java.util.List;

@Data

public class ActivityRequirementDTO implements Serializable {

    private Long requirementId;
    private Long employeeID;
    private Long activityID;
    private Long vehicleId;
    private List<HumanResources> humanResourcesList;
    private List<Vehicle> vehicleList;
}
