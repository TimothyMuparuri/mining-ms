package za.co.nharire.miningms.repositories.currentstate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.vehicle.currentstate.CurrentState;

@Repository

public interface CurrentStateRepository extends JpaRepository<CurrentState, Long> {

}
