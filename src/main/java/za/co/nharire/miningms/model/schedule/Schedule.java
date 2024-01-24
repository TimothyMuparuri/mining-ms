package za.co.nharire.miningms.model.schedule;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Schedule {
    @Id
    @Column(name = "scheduleid")
    private Long scheduleID;
    @Column(name = "activity_id")
    private Long activityID;
    @Column(name = "start_Date")
    private String startDate;
    @Column(name = "duration")
    private String duration;
    @Column(name = "endDate")
    private String endDate;
    @Column(name = "isDone")
    private Boolean isDone;
    @Column(name = "requirementID")
    private Long requirementId;
}
