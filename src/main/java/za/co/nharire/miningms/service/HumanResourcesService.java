package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.model.humanresources.HumanResources;
import za.co.nharire.miningms.model.humanresources.HumanResourcesDTO;
import za.co.nharire.miningms.ropositories.human_resources.HumanResourcesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

public class HumanResourcesService {
    private final HumanResourcesRepository humanResourcesRepository;

    public HumanResourcesDTO saveHumanResource(HumanResourcesDTO humanResourcesDTO) {

        HumanResources humanResources = new HumanResources();
        BeanUtils.copyProperties(humanResourcesDTO, humanResources);

        log.info(" Save to DB");
        HumanResources humanResources1 = humanResourcesRepository.save(humanResources);

        BeanUtils.copyProperties(humanResources1, humanResourcesDTO);
        return humanResourcesDTO;

    }

    // get humanresources
    public List<HumanResourcesDTO> getAllHumanResources() {
        log.info("IN SERVICE ");

        List<HumanResourcesDTO> humanResourcesDTOList = new ArrayList<>();

        List<HumanResources> humanResourcesList = humanResourcesRepository.findAll();
        for (HumanResources humanResources : humanResourcesList) {
            HumanResourcesDTO humanResourcesDTO = new HumanResourcesDTO();
            BeanUtils.copyProperties(humanResources, humanResourcesDTO);
            humanResourcesDTOList.add(humanResourcesDTO);
        }
        return humanResourcesDTOList;
    }

    public HumanResourcesDTO getHumanResourcesById(Long employeeid) {
        log.info("IN SERVICE ");
        HumanResourcesDTO humanResourcesDTO = new HumanResourcesDTO();
        Optional<HumanResources> humanResources = humanResourcesRepository.findById(employeeid);

        if (humanResources.isEmpty()) {
            return null;
        } else {
            BeanUtils.copyProperties(humanResources.get(), humanResourcesDTO);
            return humanResourcesDTO;
        }

    }

    public HumanResourcesDTO updateHumanResources(Long employeeid, HumanResourcesDTO humanresourceDetails) {
        log.info("IN SERVICE ");
        HumanResourcesDTO humanResourcesDTO = new HumanResourcesDTO();
        Optional<HumanResources> humanResources = humanResourcesRepository.findById(employeeid);

        if (humanResources.isEmpty()) {
            return null;
        } else {

            HumanResources humanResources1 = humanResources.get();
            humanResources1.setIsActive(humanresourceDetails.getIsActive());
            humanResourcesRepository.save(humanResources1);
            BeanUtils.copyProperties(humanResources.get(), humanResourcesDTO);
            return humanResourcesDTO;
        }

    }
}
