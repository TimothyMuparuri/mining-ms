package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.model.humanresources.HumanResources;
import za.co.nharire.miningms.model.humanresources.HumanResourcesDTO;
import za.co.nharire.miningms.model.operation.Activity;
import za.co.nharire.miningms.model.operation.ActivityDTO;
import za.co.nharire.miningms.ropositories.activity.ActivityRepository;
import za.co.nharire.miningms.ropositories.human_resources.HumanResourcesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service

public class ActivityService {

    private final ActivityRepository activityRepository;
    private final HumanResourcesRepository humanResourcesRepository;
    private final HumanResourcesService humanResourcesService;


    public ActivityDTO saveActivity(ActivityDTO activityDTO) {
        HumanResourcesDTO humanResourcesDTO = new HumanResourcesDTO();
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO, activity);

        Optional<HumanResources> humanResources = humanResourcesRepository.findById(activityDTO.getAvailableHumanResources()
                .get(0).getEmployeeID());

        BeanUtils.copyProperties(humanResources.get(), humanResourcesDTO);

        if (humanResources.isEmpty()) {
            log.error("Employee of this ID not Found");

        } else if (humanResources.get().getIsActive()) {
            log.error("Employee of this ID isActive");

            List<HumanResourcesDTO> humanResourcesDTOList = new ArrayList<>();

            List<HumanResources> availableHR = humanResourcesRepository.findByIsActiveFalse();


            if (!availableHR.isEmpty()) {

                for (HumanResources humanResource : availableHR) {

                    HumanResourcesDTO availableHRDTO = new HumanResourcesDTO();
                    BeanUtils.copyProperties(humanResource, availableHRDTO);
                    humanResourcesDTOList.add(availableHRDTO);
                }

                activityDTO.setAvailableHumanResources(humanResourcesDTOList);
                return activityDTO;
            }
        } else {
            humanResourcesDTO.setIsActive(true);
            log.info(" Save to DB");
            Activity activity1 = activityRepository.save(activity);
            humanResourcesService.saveHumanResource(humanResourcesDTO);
            BeanUtils.copyProperties(activity1, activityDTO);
        }
        return activityDTO;
    }
}
