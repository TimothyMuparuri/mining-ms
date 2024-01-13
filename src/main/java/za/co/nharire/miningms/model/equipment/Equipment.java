package za.co.nharire.miningms.model.equipment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name= "Equipment")
public class Equipment {
    @Id
    @Column(name= "equipmentid")
    private Long equipmentID;
    @Column(name= "mineid")
    private Long mineID;
    @Column(name= "equipmentname")
    private String equipmentName;
    @Column(name= "equipmentyear")
    private Long equipmentYear;
    @Column(name= "manufacturer")
    private String manufacturer;
    @Column(name= "model")
    private String model;
    @Column(name= "deliverydate")
    private LocalDate deliveryDate;
    @Column(name= "equipmentfleet")
    private Long equipmentFleet;


}
