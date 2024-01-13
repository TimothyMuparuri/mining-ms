package za.co.nharire.miningms.model.mining;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "mine")
public class Mine {
    @Id
    @Column(name= "mineid")
    private Long mineID;
    @Column(name= "minename",length = 255)
    private String mineName;
    @Column(name= "state",length = 40)
    private String state;
    @Column(name= "location",length = 255)
    private String location;
    @Column(name= "mineral",length =30)
    private String mineral;
    @Column(name= "reservesintonnes",length = 255)
    private Long reservesInTonnes;
    @Column(name= "status")
    private String status;

}
