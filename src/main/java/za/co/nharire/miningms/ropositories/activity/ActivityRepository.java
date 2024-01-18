package za.co.nharire.miningms.ropositories.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.nharire.miningms.model.operation.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
