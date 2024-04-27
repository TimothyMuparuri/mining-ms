package za.co.nharire.miningms.repositories.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.vehicle.properties.Vehicle;
@Repository

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
