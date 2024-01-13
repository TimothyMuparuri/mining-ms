package za.co.nharire.miningms.ropositories.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.equipment.Equipment;
import za.co.nharire.miningms.model.mining.Mine;
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
}
