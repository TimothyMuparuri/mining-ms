package za.co.nharire.miningms.ropositories.humanresources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.humanresources.HumanResources;

import java.util.List;

@Repository
public interface HumanResourcesRepository extends JpaRepository<HumanResources,Long> {

    List<HumanResources> findByIsActiveFalse();

    @Query(value = "SELECT * FROM humanresources WHERE firstname = ?1", nativeQuery = true)
    HumanResources findByFirstname(String firstname);


}
