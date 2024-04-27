package za.co.nharire.miningms.repositories.maintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.maintance.Maintenance;
@Repository

public interface MaintenanceRepository extends JpaRepository<Maintenance,Long> {
}
