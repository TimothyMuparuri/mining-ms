package za.co.nharire.miningms.repositories.logbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.logbook.Logbook;

@Repository
public interface LogbookRepository extends JpaRepository<Logbook,Long> {

}
