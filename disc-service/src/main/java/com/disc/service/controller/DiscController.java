package com.disc.service.controller;

import com.disc.service.entity.Disc;
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

    public DiscController(DiscService discService) {
        this.discService = discService;
    }

    @GetMapping
    public ResponseEntity<List<Disc>> listDisc() {
        log.info("Have been called the listDisc method on the DiscController class");
        List<Disc> discs = discService.getAll();
        if (discs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(discs);
    }

    @GetMapping("/live")
    public ResponseEntity<List<Disc>> listLiveDiscs() {
        log.info("Have been called the listLiveDiscs method on the DiscController class");
        List<Disc> discs = discService.getLiveDiscs();
        if (discs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(discs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disc> getDisc(@PathVariable("id") int id) {
        log.info("Have been called the getDisc method on the DiscController class");
        Disc disc = discService.getDiscById(id);
        if (disc == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(disc);
    }

    @PostMapping
    public ResponseEntity<Disc> saveDisc(@RequestBody Disc disc) {
        log.info("Have been called the saveDisc method on the DiscController class");
        Disc newDisc = discService.save(disc);
        return ResponseEntity.ok(newDisc);
    }

    @GetMapping("/band/{idBand}")
    public ResponseEntity<List<Disc>> getDiscByIdBand(@PathVariable("idBand") long idBand) {
        log.info("Have been called the getDiscByIdBand method on the DiscController class");
        List<Disc> disc = discService.getDiscsByIdBand(idBand);
        if (disc == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(disc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disc> updateDisc(@PathVariable("id") int id, @RequestBody Disc disc) {
        log.info("Have been called the updateDisc method on the DiscController class");
        if (disc == null) {
            return ResponseEntity.notFound().build();
        }
        Disc newDisc = discService.update(id, disc);
        return ResponseEntity.ok(newDisc);
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
