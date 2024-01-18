package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.constants.ApiConstants;
import za.co.nharire.miningms.model.humanresources.HumanResources;
import za.co.nharire.miningms.model.humanresources.HumanResourcesDTO;
import za.co.nharire.miningms.model.logbook.Logbook;
import za.co.nharire.miningms.model.logbook.LogbookDTO;
import za.co.nharire.miningms.model.logbook.LogbookDeleteDTO;
import za.co.nharire.miningms.ropositories.human_resources.HumanResourcesRepository;
import za.co.nharire.miningms.ropositories.logbook.LogbookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogbookService {
    private final LogbookRepository logbookRepository;
    private final HumanResourcesRepository humanResourcesRepository;
    private final HumanResourcesService humanResourcesService;


    public LogbookDTO saveLogbook(LogbookDTO logbookDTO) {
        HumanResourcesDTO humanResourcesDTO = new HumanResourcesDTO();
        Logbook logbook = new Logbook();
        BeanUtils.copyProperties(logbookDTO, logbook);

        Optional<HumanResources> humanResources = humanResourcesRepository.findById(logbookDTO.getEmployeeID());
        BeanUtils.copyProperties(humanResources.get(), humanResourcesDTO);

        humanResourcesDTO.setIsActive(false);
        log.info("Save to db");
        Logbook logbook1 = logbookRepository.save(logbook);
        humanResourcesService.saveHumanResource(humanResourcesDTO);
        BeanUtils.copyProperties(logbook1, logbookDTO);

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
            logbook1.setIsActive(logbookDetails.getIsActive());
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
