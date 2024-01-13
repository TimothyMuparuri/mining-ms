package za.co.nharire.miningms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.nharire.miningms.constants.ApiConstants;
import za.co.nharire.miningms.model.mining.MineDTO;
import za.co.nharire.miningms.model.mining.MineDeleteDTO;
import za.co.nharire.miningms.service.MiningService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MiningController {

    private final MiningService miningService;

    @PostMapping("mine/save")
    public ResponseEntity<MineDTO> saveMine(@RequestBody MineDTO mine) {

        log.info("SAVING MINE", mine.toString());

        MineDTO mine1 = miningService.saveMine(mine);
        if (mine1 != null) {
            return new ResponseEntity<>(mine1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mine/all")
    public ResponseEntity<List<MineDTO>> getAllMines() {
        log.info("GETTING ALL MINES IN DB ");

        List<MineDTO> mineList = miningService.getAllMines();

        if (mineList != null) {
            return new ResponseEntity<>(mineList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Getting a mine by mineid
    @GetMapping("/mine/get/{mineid}")
    public ResponseEntity<MineDTO> getMineById(@PathVariable Long mineid) {
        log.info("GETTING A MINE IN DB ");

        MineDTO mine1 = miningService.getMineById(mineid);

        if (mine1 != null) {
            return new ResponseEntity<>(mine1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/mine/update/{mineid}")
    public ResponseEntity<MineDTO> updateMine(@PathVariable Long mineid, @RequestBody MineDTO mineName) {
        log.info("UPDATING A MINE IN DB ");

        MineDTO mine1 = miningService.updateMine(mineid, mineName);

        if (mine1 != null) {
            return new ResponseEntity<>(mine1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/mine/delete/{mineid}")
    public ResponseEntity<MineDeleteDTO> deleteMine(@PathVariable Long mineid) {
        log.info("DELETING A MINE IN DB ");

        MineDeleteDTO mine1 = miningService.deleteMine(mineid);

        if (mine1.getMessage().equals(ApiConstants.DELETE_SUCCESS)) {
            return new ResponseEntity<>(mine1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mine1, HttpStatus.NOT_FOUND);
        }


    }

}
