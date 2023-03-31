package com.msproject.ksqlprocessorservice.service;

import com.msproject.ksqlprocessorservice.model.Artist;
import com.msproject.ksqlprocessorservice.model.MusicDataList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MusicInfoService {

    public void getMusicInfo(){

        Mono<MusicDataList> dataListMono = WebClient.create()
                .get()
                .uri("https://api.deezer.com/search?q=eminem")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MusicDataList.class);

        dataListMono.subscribe(dataList -> {
            dataList.getData()
                    .forEach(data -> {
                        //kafkaTemplate.send(KAFKA_TOPIC, domain);
                        System.out.println("Data message: " + data.getTitle());
                    });
        });

    }

    public void getMusicArtists(){

        List<Artist> artistsList = new ArrayList<>();

        for (int id=0; id<100; id++) {
            Mono<Artist> artistMono = WebClient.create()
                    .get()
                    .uri("https://api.deezer.com/artist/" + id)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Artist.class);

            artistMono.subscribe(artist -> {
                log.info("=>"+artist.getName());
                artistsList.add(artist);
            });
        }

        log.info("=>"+artistsList.size());

    }

}
