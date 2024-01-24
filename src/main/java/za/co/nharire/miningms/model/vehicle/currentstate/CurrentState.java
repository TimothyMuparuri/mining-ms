package za.co.nharire.miningms.model.vehicle.currentstate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name ="current_state")

public class CurrentState {

    @Id
    @Column(name = "current_state_id")
    private Long currentStateID;
    @Column(name = "mileage")
    private Long mileage;
    @Column(name = "lastService")
    private LocalDate lastService;
    @Column(name = "engineStatus")
    private String engineStatus;
    @Column(name ="requires_Maintenance")
    private boolean requiresMaintenance;
    @Column(name = "overallStatus")
    private String overallStatus;

}
