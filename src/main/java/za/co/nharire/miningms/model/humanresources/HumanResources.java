package za.co.nharire.miningms.model.humanresources;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "humanresources")
public class HumanResources {
    @Id
    @Column(name= "employeeid")
    private Long employeeID;
    @Column(name= "firstname",length = 255)
    private String firstName;
    @Column(name= "lastname",length = 40)
    private String lastName;
    @Column(name= "role",length = 255)
    private String role;
    @Column(name= "isActive",length =30)
    private Boolean isActive = false;
    @Column(name= "telephone",length = 255)
    private String telephone;
    @Column(name= "address")
    private String address;
}
