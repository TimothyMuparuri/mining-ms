package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.nharire.miningms.model.specification.SpecificationDTO;
import za.co.nharire.miningms.service.SpecificationService;

@Slf4j
@RequiredArgsConstructor
@RestController

public class SpecificationController {

    private final SpecificationService specificationService;

    @PostMapping("specification/save")
    public ResponseEntity<SpecificationDTO> saveSpecification(@RequestBody SpecificationDTO specificationDTO) {
        log.info("SAVING SPECIFICATION", specificationDTO.toString());

        SpecificationDTO specificationDTO1 = specificationService.saveSpecification(specificationDTO);
        if (specificationDTO1 != null) {
            return new ResponseEntity<>(specificationDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
