package com.msproject.relationaldb.controller;

import com.msproject.relationaldb.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/music")
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping
    public ResponseEntity<HttpStatus> getMusic(){
        musicService.getMusic();
        return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
    }

}
