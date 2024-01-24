package za.co.nharire.miningms.model.schedule;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data

public class ScheduleDTO implements Serializable {
    private Long scheduleID;
    private Long activityID;
    private String startDate;
    private String duration;
    private String endDate;
    private Boolean isDone;
    private Long requirementId;
}
