package za.co.nharire.miningms.ropositories.human_resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.humanresources.HumanResources;

import java.util.List;

@Repository
public interface HumanResourcesRepository extends JpaRepository<HumanResources,Long> {

    List<HumanResources> findByIsActiveFalse();

}
