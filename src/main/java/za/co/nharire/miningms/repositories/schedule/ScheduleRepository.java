package za.co.nharire.miningms.repositories.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.nharire.miningms.model.schedule.Schedule;
@Repository

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
