package za.co.nharire.miningms.repositories.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.equipment.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
}
