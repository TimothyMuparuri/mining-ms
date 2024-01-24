package za.co.nharire.miningms.model.activity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "activityrequirement")
public class ActivityRequirement {

    @Id
    @Column(name = "requirementId")
    private Long requirementId;
    @Column(name = "activityID")
    private Long activityID;
    @Column(name = "employee_id")
    private Long employeeID;
    @Column(name = "vehicle_id")
    private Long vehicleId;
}
