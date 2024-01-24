package za.co.nharire.miningms.model.vehicle.properties;

import com.fasterxml.jackson.core.SerializableString;
import lombok.Data;

import java.io.Serializable;

@Data

public class VehicleDeleteDTO implements Serializable {
    private String message;
}
