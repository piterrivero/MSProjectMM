package com.msproject.ksqlprocessorservice.controller;

import com.msproject.ksqlprocessorservice.service.DomainService;
import com.msproject.ksqlprocessorservice.service.MusicInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/producer")
public class ProducerController {

    private MusicInfoService musicInfoService;

    private DomainService domainService;

    public ProducerController(MusicInfoService musicInfoService, DomainService domainService) {
        this.musicInfoService = musicInfoService;
        this.domainService = domainService;
    }

    @GetMapping("/domains-data")
    public ResponseEntity<List<String>> domainsData() {
        log.info("It is called the domainsData service");

        List<String> test = new ArrayList<>();

        domainService.getDomainsInfo("facebook");

        return ResponseEntity.ok(test);
    }

    @GetMapping("/music-data")
    public ResponseEntity<List<String>> musicData() {
        log.info("It is called the musicData service");
        List<String> test = new ArrayList<>();

        //musicInfoService.getMusicInfo();

        musicInfoService.getMusicArtists();

        return ResponseEntity.ok(test);
    }
}
