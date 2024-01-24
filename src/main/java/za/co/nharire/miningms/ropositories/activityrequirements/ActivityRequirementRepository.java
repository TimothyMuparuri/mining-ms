package za.co.nharire.miningms.ropositories.activityrequirements;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.activity.ActivityRequirement;

@Repository

public interface ActivityRequirementRepository extends JpaRepository<ActivityRequirement ,Long> {
}
