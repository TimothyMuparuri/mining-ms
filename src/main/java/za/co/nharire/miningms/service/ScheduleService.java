package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.model.schedule.Schedule;
import za.co.nharire.miningms.model.schedule.ScheduleDTO;
import za.co.nharire.miningms.ropositories.schedule.ScheduleRepository;

@Slf4j
@Service
@RequiredArgsConstructor

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO,schedule);

        log.info(" Save to DB");
        Schedule schedule1= scheduleRepository.save(schedule);

        BeanUtils.copyProperties(schedule1,scheduleDTO);
        return scheduleDTO;
    }
}
