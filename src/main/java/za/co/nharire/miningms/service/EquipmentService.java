package za.co.nharire.miningms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import za.co.nharire.miningms.constants.ApiConstants;
import za.co.nharire.miningms.model.equipment.Equipment;
import za.co.nharire.miningms.model.equipment.EquipmentDTO;
import za.co.nharire.miningms.model.equipment.EquipmentDeleteDTO;
import za.co.nharire.miningms.model.mining.Mine;
import za.co.nharire.miningms.ropositories.equipment.EquipmentRepository;
import za.co.nharire.miningms.ropositories.mining.MiningRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final MiningRepository miningRepository;


    public EquipmentDTO saveEquipment(EquipmentDTO equipmentDTO) {
        Equipment equipment = new Equipment();
        BeanUtils.copyProperties(equipmentDTO, equipment);

        //check if mine Id exist

        Optional<Mine> mine = miningRepository.findById(equipmentDTO.getMineID());

        if (mine.isEmpty()) {
            log.error("Mine of this ID not Found");
            return null;
        } else {
            log.info("Save to db");
            Equipment equipment1 = equipmentRepository.save(equipment);

            BeanUtils.copyProperties(equipment1, equipmentDTO);

        }
        return equipmentDTO;

    }

    // get equipment
    public List<EquipmentDTO> getAllEquipment() {
        log.info("IN SERVICE ");

        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();

        List<Equipment> equipmentList = equipmentRepository.findAll();
        for (Equipment equipment : equipmentList) {
            EquipmentDTO equipmentDTO = new EquipmentDTO();
            BeanUtils.copyProperties(equipment, equipmentDTO);
            equipmentDTOList.add(equipmentDTO);
        }
        return equipmentDTOList;
    }

    // get equipment by id rest api

    public EquipmentDTO getEquipmentById(Long equipmentid) {
        log.info("IN SERVICE ");
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        Optional<Equipment> equipment = equipmentRepository.findById(equipmentid);

        if (equipment.isEmpty()) {
            return null;
        } else {
            BeanUtils.copyProperties(equipment.get(), equipmentDTO);
            return equipmentDTO;
        }

    }

    // updating the Db
    public EquipmentDTO updateEquipment(Long equipmentid, EquipmentDTO equipmentDetails) {
        log.info("IN SERVICE ");
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        Optional<Equipment> equipment = equipmentRepository.findById(equipmentid);

        if (equipment.isEmpty()) {
            return null;
        } else {

            Equipment equipment1 = equipment.get();
            equipment1.setEquipmentName(equipmentDetails.getEquipmentName());
            BeanUtils.copyProperties(equipment.get(), equipmentDTO);
            return equipmentDTO;
        }

    }

    //delete equipment by id
    public EquipmentDeleteDTO deleteEquipment(Long equipmentid) {
        log.info("IN SERVICE ");

        EquipmentDeleteDTO equipmentDeleteDTO = new EquipmentDeleteDTO();

        Optional<Equipment> equipment = equipmentRepository.findById(equipmentid);
        if (equipment.isEmpty()) {
            log.error(" No ID found for this ID " + equipmentid);

            equipmentDeleteDTO.setMessage(ApiConstants.DELETE_FAILURE + equipmentid);
            return equipmentDeleteDTO;
        } else {
            equipmentRepository.deleteById(equipmentid);
        }
        equipmentDeleteDTO.setMessage(ApiConstants.DELETE_SUCCESS);
        return equipmentDeleteDTO;
    }

}


