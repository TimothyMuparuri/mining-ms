package za.co.nharire.miningms.model.specification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "specifications")
public class Specification {
    @Id
    @Column(name = "specificationID")
    private Long specificationID;
    @Column(name = "servicePeriod")
    private String servicePeriod;
    @Column(name = "requiredCapacity")
    private String requiredCapacity;
    @Column(name = "unitWorkingHoursPerCycle")
    private String unitWorkingHoursPerCycle;
    @Column(name = "conditions")
    private String conditions;
    @Column(name = "vehicleId")
    private Long vehicleID;

}
