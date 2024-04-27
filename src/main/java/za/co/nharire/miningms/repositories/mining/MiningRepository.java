package za.co.nharire.miningms.repositories.mining;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.mining.Mine;
@Repository
public interface MiningRepository extends JpaRepository<Mine,Long> {
}
