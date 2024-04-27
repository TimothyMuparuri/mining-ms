package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.constants.ApiConstants;
import za.co.nharire.miningms.model.activity.Activity;
import za.co.nharire.miningms.model.activity.ActivityDTO;
import za.co.nharire.miningms.model.humanresources.HumanResources;
import za.co.nharire.miningms.model.humanresources.HumanResourcesDTO;
import za.co.nharire.miningms.model.logbook.Logbook;
import za.co.nharire.miningms.model.logbook.LogbookDTO;
import za.co.nharire.miningms.model.logbook.LogbookDeleteDTO;
import za.co.nharire.miningms.model.schedule.Schedule;
import za.co.nharire.miningms.model.schedule.ScheduleDTO;
import za.co.nharire.miningms.model.vehicle.currentstate.CurrentStateDTO;
import za.co.nharire.miningms.repositories.activity.ActivityRepository;
import za.co.nharire.miningms.repositories.currentstate.CurrentStateRepository;
import za.co.nharire.miningms.repositories.humanresources.HumanResourcesRepository;
import za.co.nharire.miningms.repositories.logbook.LogbookRepository;
import za.co.nharire.miningms.repositories.schedule.ScheduleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogbookService {
    private final CurrentStateRepository currentStateRepository;
    private final LogbookRepository logbookRepository;
    private final HumanResourcesRepository humanResourcesRepository;
    private final HumanResourcesService humanResourcesService;
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;
    private final CurrentStateService currentStateService;
    private final ActivityRepository activityRepository;

    public LogbookDTO saveLogbook(LogbookDTO logbookDTO) {
        HumanResourcesDTO humanResourcesDTO = new HumanResourcesDTO();
        CurrentStateDTO currentState1 = new CurrentStateDTO();
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        Logbook logbook = new Logbook();
        BeanUtils.copyProperties(logbookDTO, logbook);

        ActivityDTO activityDTO = new ActivityDTO();

        Optional<Activity> activity = activityRepository.findById(logbookDTO.getActivityID());
        BeanUtils.copyProperties(activity.get(),activityDTO);

        Optional<HumanResources> humanResources = humanResourcesRepository.findById(logbookDTO.getEmployeeID());
        Optional<Schedule> schedule = scheduleRepository.findById(logbookDTO.getActivityID());

        BeanUtils.copyProperties(humanResources.get(), humanResourcesDTO);
        BeanUtils.copyProperties(logbookDTO, currentState1);
        BeanUtils.copyProperties(schedule.get(), scheduleDTO);

        humanResourcesDTO.setIsActive(false);
        scheduleDTO.setIsDone(true);
        currentState1.setRequiresMaintenance(true);

        log.info("Save to db");
        Logbook logbook1 = logbookRepository.save(logbook);
        scheduleService.saveSchedule(scheduleDTO);
        humanResourcesService.saveHumanResource(humanResourcesDTO);

        BeanUtils.copyProperties(logbook1, logbookDTO);

        Long currentStateID = currentState1.getCurrentStateID() + 1;
        currentState1.setCurrentStateID(currentStateID);
        currentState1.setMileage(activityDTO.getMileage());

        currentStateService.saveCurrentState(currentState1);

        return logbookDTO;
    }

    // get logbook
    public List<LogbookDTO> getAllLogbooks() {
        log.info("IN SERVICE ");

        List<LogbookDTO> logbookDTOList = new ArrayList<>();

        List<Logbook> logbookList = logbookRepository.findAll();
        for (Logbook logbook : logbookList) {
            LogbookDTO logbookDTO = new LogbookDTO();
            BeanUtils.copyProperties(logbook, logbookDTO);
            logbookDTOList.add(logbookDTO);
        }
        return logbookDTOList;
    }

    public LogbookDTO getLogbookById(Long id) {
        log.info("IN SERVICE ");
        LogbookDTO logbookDTO = new LogbookDTO();
        Optional<Logbook> logbook = logbookRepository.findById(id);

        if (logbook.isEmpty()) {
            return null;
        } else {
            BeanUtils.copyProperties(logbook.get(), logbookDTO);
            return logbookDTO;
        }

    }

    // updating the Db
    public LogbookDTO updateLogbook(Long id, LogbookDTO logbookDetails) {
        log.info("IN SERVICE ");
        LogbookDTO logbookDTO = new LogbookDTO();
        Optional<Logbook> logbook = logbookRepository.findById(id);

        if (logbook.isEmpty()) {
            return null;
        } else {

            Logbook logbook1 = logbook.get();
            logbookRepository.save(logbook1);
            BeanUtils.copyProperties(logbook.get(), logbookDTO);
            return logbookDTO;
        }

    }

    public LogbookDeleteDTO deleteLogbook(Long id) {
        log.info("IN SERVICE ");

        LogbookDeleteDTO deleteDTO = new LogbookDeleteDTO();

        Optional<Logbook> logbook = logbookRepository.findById(id);
        if (logbook.isEmpty()) {
            log.error(" No ID found for this ID " + id);

            deleteDTO.setMessage(ApiConstants.DELETE_FAILURE + id);
            return deleteDTO;
        } else {
            logbookRepository.deleteById(id);
        }
        deleteDTO.setMessage(ApiConstants.DELETE_SUCCESS);
        return deleteDTO;

    }

}
