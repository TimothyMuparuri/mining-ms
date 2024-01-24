package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.model.specification.Specification;
import za.co.nharire.miningms.model.specification.SpecificationDTO;
import za.co.nharire.miningms.ropositories.specifications.SpecificationRepository;

@Slf4j
@RequiredArgsConstructor
@Service

public class SpecificationService {

    private final SpecificationRepository specificationRepository;

    public SpecificationDTO saveSpecification(SpecificationDTO specificationDTO){
        Specification specification = new Specification();
        BeanUtils.copyProperties(specificationDTO,specification);

        log.info(" Save to DB");
        Specification specification1 = specificationRepository.save(specification);

        BeanUtils.copyProperties(specificationDTO,specification);
        return specificationDTO;
    }
}
