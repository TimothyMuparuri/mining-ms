package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.constants.ApiConstants;
import za.co.nharire.miningms.model.mining.Mine;
import za.co.nharire.miningms.model.mining.MineDTO;
import za.co.nharire.miningms.model.mining.MineDeleteDTO;
import za.co.nharire.miningms.repositories.mining.MiningRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MiningService {

    private final MiningRepository miningRepository;

    public MineDTO saveMine(MineDTO mineDTO) {

        Mine mine = new Mine();
        BeanUtils.copyProperties(mineDTO, mine);

        log.info(" Save to DB");
        Mine mine1 = miningRepository.save(mine);

        BeanUtils.copyProperties(mine1, mineDTO);
        return mineDTO;

    }

    // get mine
    public List<MineDTO> getAllMines() {
        log.info("IN SERVICE ");

        List<MineDTO> mineDTOList = new ArrayList<>();

        List<Mine> mineList = miningRepository.findAll();
        for (Mine mine : mineList) {
            MineDTO mineDTO = new MineDTO();
            BeanUtils.copyProperties(mine, mineDTO);
            mineDTOList.add(mineDTO);
        }
        return mineDTOList;
    }

    // get mine by id rest api

    public MineDTO getMineById(Long mineid) {
        log.info("IN SERVICE ");
        MineDTO mineDTO = new MineDTO();
        Optional<Mine> mine = miningRepository.findById(mineid);

        if (mine.isEmpty()) {
            return null;
        } else {
            BeanUtils.copyProperties(mine.get(), mineDTO);
            return mineDTO;
        }

    }

    // updating the Db
    public MineDTO updateMine(Long mineid, MineDTO mineDetails) {
        log.info("IN SERVICE ");
        MineDTO mineDTO = new MineDTO();
        Optional<Mine> mine = miningRepository.findById(mineid);

        if (mine.isEmpty()) {
            return null;
        } else {

            Mine mine1 = mine.get();
            mine1.setMineName(mineDetails.getMineName());
            mine1.setStatus(mineDetails.getStatus());
            mine1.setLocation(mineDetails.getLocation());
            mine1.setMineral(mineDetails.getMineral());
            mine1.setReservesInTonnes(mineDetails.getReservesInTonnes());
            mine1.setState(mineDetails.getState());
            miningRepository.save(mine1);
            BeanUtils.copyProperties(mine.get(), mineDTO);
            return mineDTO;
        }

    }

    public MineDeleteDTO deleteMine(Long mineid) {
        log.info("IN SERVICE ");

        MineDeleteDTO mineDTO = new MineDeleteDTO();

        Optional<Mine> mine = miningRepository.findById(mineid);
        if (mine.isEmpty()) {
            log.error(" No ID found for this ID " + mineid);

            mineDTO.setMessage(ApiConstants.DELETE_FAILURE + mineid);
            return mineDTO;
        } else {
            miningRepository.deleteById(mineid);
        }
        mineDTO.setMessage(ApiConstants.DELETE_SUCCESS);
        return mineDTO;
    }

}
