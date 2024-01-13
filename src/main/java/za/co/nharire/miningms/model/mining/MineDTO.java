package za.co.nharire.miningms.model.mining;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class MineDTO implements Serializable {
    private Long mineID;
    private String mineName;
    private String state;
    private String location;
    private String mineral;
    private Long reservesInTonnes;
    private String status;
}
