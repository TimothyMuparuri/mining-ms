package za.co.nharire.miningms.model.vehicle.properties;

import lombok.Data;

import java.io.Serializable;

@Data

public class VehicleDTO implements Serializable {
    private Long vehicleID;
    private String engineNumber;
    private String make;
    private String model;
    private String colour;
    private Long year;
    private Long mileage;
    private Long specificationID;
    private String servicePeriod;
    private String requiredCapacity;
    private String unitWorkingHoursPerCycle;
    private String conditions;


}
