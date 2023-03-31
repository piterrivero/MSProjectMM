package com.msproject.ksqlprocessorservice.service;

import com.msproject.ksqlprocessorservice.model.DomainList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DomainService {

    public void getDomainsInfo(String name){
        Mono<DomainList> domainListMono = WebClient.create()
                .get()
                .uri("https://api.domainsdb.info/v1/domains/search?domain=" + name + "&zone=com")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DomainList.class);


        domainListMono.subscribe(domainList -> {
            domainList.getDomains()
                    .forEach(domain -> {
                        //kafkaTemplate.send(KAFKA_TOPIC, domain);
                        System.out.println("Domain message: " + domain.getDomain());
                    });
        });
    }
}
