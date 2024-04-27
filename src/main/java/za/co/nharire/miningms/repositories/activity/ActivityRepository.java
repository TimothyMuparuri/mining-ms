package za.co.nharire.miningms.repositories.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.activity.Activity;
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
