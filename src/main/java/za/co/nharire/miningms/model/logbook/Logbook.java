package za.co.nharire.miningms.model.logbook;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "logbook")

public class Logbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logid")
    private Long logid;
    @Column(name = "activityid")
    private Long activityID;
    @Column(name = "employeeid")
    private Long employeeID;
    @Column(name = "duration")
    private String duration;
    @Column(name = "description")
    private String description;
    @Column(name = "event_date")
    private String eventDate;
    @Column(name = "event_type")
    private String eventType;

}
