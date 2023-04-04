package com.msproject.relationaldb.service;

import com.msproject.relationaldb.configuration.KafkaTopicConfig;
import com.msproject.relationaldb.domain.*;
import com.msproject.relationaldb.kafka.KafkaSender;
import com.msproject.relationaldb.dto.BandDTO;
import com.msproject.relationaldb.dto.DiscDTO;
import com.msproject.relationaldb.dto.GenreDTO;
import com.msproject.relationaldb.dto.MusicDTO;
import com.msproject.relationaldb.repository.BandRepository;
import com.msproject.relationaldb.repository.DiscRepository;
import com.msproject.relationaldb.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MusicService {

    private final DiscRepository discRepository;

    private final BandRepository bandRepository;

    private final GenreRepository genreRepository;

    private final KafkaSender kafkaSender;

    public MusicService(DiscRepository discRepository, BandRepository bandRepository, GenreRepository genreRepository, KafkaSender kafkaSender) {
        this.discRepository = discRepository;
        this.bandRepository = bandRepository;
        this.genreRepository = genreRepository;
        this.kafkaSender = kafkaSender;
    }

    public MusicDTO getMusic() {
        MusicDTO musicDTO = new MusicDTO();

        List<DiscDTO> discs = new ArrayList<>();

        discRepository.findAll().stream().forEach(disc -> {
            discs.add(getDiscDTO(disc));
        });

        musicDTO.setDisc(discs);

        kafkaSender.sendMessage(KafkaTopicConfig.MUSIC_TOPIC, musicDTO);

        return musicDTO;
    }

    public DiscDTO getDiscDTO(Disc disc){
        return new DiscDTO(disc.getId(), disc.getTitle(), disc.getYear(), disc.getPrice(), getBandDTO(disc.getBand()));
    }

    public BandDTO getBandDTO(Band band){
        return new BandDTO(band.getId(), band.getName(), band.getCountry(), getGenreDTO(band.getGenre()));
    }

    public GenreDTO getGenreDTO(Genre genre){
        return new GenreDTO(genre.getId(), genre.getGenre());
    }

}
