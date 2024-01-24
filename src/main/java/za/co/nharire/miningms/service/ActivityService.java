package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.model.activity.Activity;
import za.co.nharire.miningms.model.activity.ActivityDTO;
import za.co.nharire.miningms.model.activity.ActivityRequirementDTO;
import za.co.nharire.miningms.model.humanresources.HumanResources;
import za.co.nharire.miningms.model.humanresources.HumanResourcesDTO;
import za.co.nharire.miningms.model.schedule.ScheduleDTO;
import za.co.nharire.miningms.model.vehicle.currentstate.CurrentStateDTO;
import za.co.nharire.miningms.model.vehicle.properties.Vehicle;
import za.co.nharire.miningms.model.vehicle.properties.VehicleDTO;
import za.co.nharire.miningms.ropositories.activity.ActivityRepository;
import za.co.nharire.miningms.ropositories.currentstate.CurrentStateRepository;
import za.co.nharire.miningms.ropositories.humanresources.HumanResourcesRepository;
import za.co.nharire.miningms.ropositories.schedule.ScheduleRepository;
import za.co.nharire.miningms.ropositories.vehicle.VehicleRepository;

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
    private final ScheduleService scheduleService;
    private final ActivityRequirementService activityRequirementService;
    private final VehicleRepository vehicleRepository;
    private final VehicleService vehicleService;



    public ActivityDTO saveActivity(ActivityDTO activityDTO) {
        Vehicle vehicle = new Vehicle();
        HumanResourcesDTO humanResourcesDTO = new HumanResourcesDTO();

        List<HumanResourcesDTO> humanResourcesDTOList2 = activityDTO.getHumanResourcesDTOList();

        ScheduleDTO schedule = new ScheduleDTO();
        BeanUtils.copyProperties(activityDTO, schedule);

        ActivityRequirementDTO activityRequirement = new ActivityRequirementDTO();
        BeanUtils.copyProperties(activityDTO, activityRequirement);

        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO, activity);

        Optional<HumanResources> humanResources = humanResourcesRepository.findById(activityDTO.getAvailableHumanResources()
                .get(0).getEmployeeID());
        BeanUtils.copyProperties(humanResources.get(), humanResourcesDTO);

        VehicleDTO vehicleDTO = new VehicleDTO();
        Optional<Vehicle> vehicleUsed = vehicleRepository.findById(activityDTO.getVehicleId());
        BeanUtils.copyProperties(vehicleUsed.get(),vehicleDTO);

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
            humanResourcesDTO.setIsActive(false);
            log.info(" Save to DB");
            Activity activity1 = activityRepository.save(activity);
            humanResourcesService.saveHumanResource(humanResourcesDTO);
            BeanUtils.copyProperties(activity1, activityDTO);
            scheduleService.saveSchedule(schedule);

            vehicleDTO.setMileage(vehicle.updateMileage(vehicleDTO));
            vehicleService.saveVehicle(vehicleDTO);

            for (HumanResourcesDTO humanResources1 : humanResourcesDTOList2) {

                ActivityRequirementDTO activityRequirement1 = new ActivityRequirementDTO();
                activityRequirement1.setActivityID(activityDTO.getActivityID());
                activityRequirement1.setRequirementId(activityDTO.getRequirementId());
                activityRequirement1.setVehicleId(activityDTO.getVehicleId());
                activityRequirement1.setEmployeeID(humanResources1.getEmployeeID());

                activityRequirementService.saveActivityRequirement(activityRequirement1);

            }

        }
        return activityDTO;
    }

    // get activities
    public List<ActivityDTO> getAllActivities() {
        log.info("IN SERVICE ");

        List<ActivityDTO> activityDTOList = new ArrayList<>();

        List<Activity> activityList = activityRepository.findAll();
        for (Activity activity : activityList) {
            ActivityDTO activityDTO = new ActivityDTO();
            BeanUtils.copyProperties(activity, activityDTO);
            activityDTOList.add(activityDTO);
        }
        return activityDTOList;
    }

    // get activity by id rest api
    public ActivityDTO getActivityById(Long activityID) {
        log.info("IN SERVICE ");
        ActivityDTO activityDTO = new ActivityDTO();
        Optional<Activity> activity = activityRepository.findById(activityID);

        if (activity.isEmpty()) {
            return null;
        } else {
            BeanUtils.copyProperties(activity.get(), activityDTO);
            return activityDTO;
        }

    }

    // updating the Db
    public ActivityDTO updateActivity(Long activityID, ActivityDTO activityDetails) {
        log.info("IN SERVICE ");
        ActivityDTO activityDTO = new ActivityDTO();
        Optional<Activity> activity = activityRepository.findById(activityID);

        if (activity.isEmpty()) {
            return null;
        } else {

            Activity activity1 = activity.get();
            activity1.setActivityName(activityDetails.getActivityName());
            BeanUtils.copyProperties(activity.get(), activityDTO);
            return activityDTO;
        }

    }
}
