package za.co.nharire.miningms.model.operation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "activities")
public class Activity {
    @Id
    @Column(name = "activityid")
    private Long activityID;
    @Column(name = "employeeid")
    private Long employeeID;
    @Column(name = "activityname")
    private String activityName;
    @Column(name = "equipment")
    private Long equipmentID;
    @Column(name = "startdate")
    private String startDate;
    @Column(name = "enddate")
    private String endDate;
    @Column(name = "description")
    private String description;

}
