package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.model.vehicle.currentstate.CurrentState;
import za.co.nharire.miningms.model.vehicle.currentstate.CurrentStateDTO;
import za.co.nharire.miningms.ropositories.currentstate.CurrentStateRepository;
@Slf4j
@RequiredArgsConstructor
@Service

public class CurrentStateService {

    private final CurrentStateRepository currentStateRepository;

    public CurrentStateDTO saveCurrentState(CurrentStateDTO currentStateDTO){
        CurrentState currentState = new CurrentState();
        BeanUtils.copyProperties(currentStateDTO,currentState);

        log.info(" Save to DB");
        CurrentState currentState1= currentStateRepository.save(currentState);
        BeanUtils.copyProperties(currentState1,currentStateDTO);

        return currentStateDTO;

    }

}
