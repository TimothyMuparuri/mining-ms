package za.co.nharire.miningms.model.maintance;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "maintenance")

public class Maintenance {

    @Id
    @Column(name = "maintenceid")
    private Long maintenanceID;
    @Column(name = "maintenance_date")
    private LocalDate maintenanceDate;
    @Column(name = "vehicleid")
    private Long vehicleID;
    @Column(name = "description")
    private String description;
    @Column(name = "duration")
    private String duration;

}
