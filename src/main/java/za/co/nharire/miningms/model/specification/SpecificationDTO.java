package za.co.nharire.miningms.model.specification;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;
@Data

public class SpecificationDTO implements Serializable {

    private Long specificationID;
    private String servicePeriod;
    private String requiredCapacity;
    private String unitWorkingHoursPerCycle;
    private String conditions;
    private Long vehicleID;

}
