package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.model.activity.ActivityRequirement;
import za.co.nharire.miningms.model.activity.ActivityRequirementDTO;
import za.co.nharire.miningms.ropositories.activityrequirements.ActivityRequirementRepository;

@Slf4j
@RequiredArgsConstructor
@Service

public class ActivityRequirementService {

    private final ActivityRequirementRepository activityRequirementRepository;

    public ActivityRequirementDTO saveActivityRequirement(ActivityRequirementDTO activityDTO) {

        ActivityRequirement activityRequirement = new ActivityRequirement();
        BeanUtils.copyProperties(activityDTO, activityRequirement);

        log.info(" Save to DB");
        ActivityRequirement activityRequirement1 = activityRequirementRepository.save(activityRequirement);

        BeanUtils.copyProperties(activityRequirement1, activityDTO);
        return activityDTO;
    }
}
