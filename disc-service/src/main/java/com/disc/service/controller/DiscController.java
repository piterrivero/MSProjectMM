package com.disc.service.controller;

import com.disc.service.entity.Disc;
import com.disc.service.mapper.DiscMapper;
import com.disc.service.model.DiscDTO;
import com.disc.service.service.DiscService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/disc")
public class DiscController {

    private final DiscService discService;

    private final DiscMapper discMapper;

    public DiscController(DiscService discService, DiscMapper discMapper) {
        this.discService = discService;
        this.discMapper = discMapper;
    }

    @GetMapping
    public ResponseEntity<List<DiscDTO>> listDisc() {
        log.info("Have been called the listDisc method on the DiscController class");
        List<Disc> discs = discService.getAll();
        if (discs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(discMapper.modelsToDto(discs));
    }

    @GetMapping("/live")
    public ResponseEntity<List<DiscDTO>> listLiveDiscs() {
        log.info("Have been called the listLiveDiscs method on the DiscController class");
        List<Disc> discs = discService.getLiveDiscs();
        if (discs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(discMapper.modelsToDto(discs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscDTO> getDisc(@PathVariable("id") int id) {
        log.info("Have been called the getDisc method on the DiscController class");
        Disc disc = discService.getDiscById(id);
        if (disc == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(discMapper.modelToDto(disc));
    }

    @PostMapping
    public ResponseEntity<DiscDTO> saveDisc(@RequestBody DiscDTO disc) {
        log.info("Have been called the saveDisc method on the DiscController class");
        Disc newDisc = discService.save(discMapper.dtoToModel(disc));
        return ResponseEntity.ok(discMapper.modelToDto(newDisc));
    }

    @GetMapping("/band/{idBand}")
    public ResponseEntity<List<DiscDTO>> getDiscByIdBand(@PathVariable("idBand") long idBand) {
        log.info("Have been called the getDiscByIdBand method on the DiscController class");
        List<Disc> disc = discService.getDiscsByIdBand(idBand);
        if (disc == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(discMapper.modelsToDto(disc));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscDTO> updateDisc(@PathVariable("id") int id, @RequestBody DiscDTO disc) {
        log.info("Have been called the updateDisc method on the DiscController class");
        if (disc == null) {
            return ResponseEntity.notFound().build();
        }
        Disc newDisc = discService.update(id, discMapper.dtoToModel(disc));
        return ResponseEntity.ok(discMapper.modelToDto(newDisc));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDisc(@PathVariable("id") int id) {
        log.info("Have been called the deleteDisc method on the DiscController class");
        Disc disc = discService.getDiscById(id);
        if (disc == null) {
            return ResponseEntity.notFound().build();
        }
        discService.delete(id);
        return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
    }

}
