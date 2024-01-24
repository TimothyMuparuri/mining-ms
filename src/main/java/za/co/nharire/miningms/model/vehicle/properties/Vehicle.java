package za.co.nharire.miningms.model.vehicle.properties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import za.co.nharire.miningms.model.activity.ActivityDTO;

@Entity
@Data
@Table(name = "vehicles")

public class Vehicle {
    @Id
    @Column(name= "vehicleid")
    private Long vehicleID;
    @Column(name= "engine_number")
    private String engineNumber;
    @Column(name= "make")
    private String make;
    @Column(name= "model")
    private String model;
    @Column(name= "colour")
    private String colour;
    @Column(name= "year")
    private Long year;
    @Column(name= "mileage")
    private Long mileage;

    public Long updateMileage(VehicleDTO vehicleDTO){
        ActivityDTO activityDTO = new ActivityDTO();

        Long newMileage =vehicleDTO.getMileage() + activityDTO.getMileage();
        vehicleDTO.setMileage(newMileage);

        return newMileage;
    }

}
