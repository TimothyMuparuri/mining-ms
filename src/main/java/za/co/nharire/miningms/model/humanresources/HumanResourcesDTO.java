package za.co.nharire.miningms.model.humanresources;

import lombok.Data;

import java.io.Serializable;

@Data

public class HumanResourcesDTO implements Serializable {
    private Long employeeID;
    private String firstName;
    private String lastName;
    private String role;
    public Boolean isActive = false;
    private String telephone;
    private String address;
}
