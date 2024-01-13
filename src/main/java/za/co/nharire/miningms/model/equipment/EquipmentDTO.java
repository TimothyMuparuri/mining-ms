package za.co.nharire.miningms.model.equipment;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EquipmentDTO implements Serializable{
    private Long equipmentID;
    private Long mineID;
    private String equipmentName;
    private Long equipmentYear;
    private String manufacturer;
    private String model;
    private LocalDate deliveryDate;
    private Long equipmentFleet;
}
