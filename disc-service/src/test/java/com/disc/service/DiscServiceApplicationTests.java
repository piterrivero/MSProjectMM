package com.disc.service;

import com.disc.service.entity.Disc;
import com.disc.service.kafka.KafkaSender;
import com.disc.service.repository.DiscRepository;
import com.disc.service.service.DiscService;
import com.disc.service.service.SequenceGeneratorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscServiceApplicationTests {

    private static List<Disc> discListMock;
    // There are created the mocks object that are used inside the service
    @Mock
    private DiscRepository discRepository;
    @Mock
    private SequenceGeneratorService sequenceGeneratorService;
    @Mock
    private MongoTemplate mongoTemplate;
    @Mock
    private KafkaSender kafkaSender;
    // There is created an instance of the service and injected the mocks declared
    // before
    @InjectMocks
    private DiscService discService;

    @BeforeAll
    static void init() {
        discListMock = new ArrayList<>();

        Disc disc1 = new Disc();
        disc1.setId(1);
        disc1.setTitle("Fear of the dark");
        disc1.setYear("1992");
        disc1.setIdBand(1);
        disc1.setPrice(20);
        disc1.setStock(4);
        discListMock.add(disc1);

        Disc disc2 = new Disc();
        disc2.setId(2);
        disc2.setTitle("Live After Death");
        disc2.setYear("1985");
        disc2.setIdBand(1);
        disc2.setPrice(25);
        disc2.setStock(6);
        discListMock.add(disc2);

        Disc disc3 = new Disc();
        disc3.setId(3);
        disc3.setTitle("Back in Black");
        disc3.setYear("1980");
        disc3.setIdBand(2);
        disc3.setPrice(30);
        disc3.setStock(2);
        discListMock.add(disc3);
    }

    @Test
    public void shouldListAllDiscs() {
        // GIVEN
        when(discRepository.findAll()).thenReturn(discListMock);
        // WHEN
        List<Disc> discList = discService.getAll();
        // THEN
        assertThat(discList).isNotNull()
                .isNotEmpty()
                .hasSize(3);
    }

    @Test
    public void shouldListAllLiveDiscs() {
        // GIVEN
        when(discRepository.findAll()).thenReturn(discListMock);
        // WHEN
        List<Disc> discList = discService.getLiveDiscs();
        // THEN
        assertThat(discList).isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    public void shouldSaveDiscs() {
        // GIVEN
        Disc discMock = new Disc();
        discMock.setId(2);
        discMock.setTitle("Highway to Hell");
        discMock.setYear("1979");
        discMock.setIdBand(2);
        discMock.setPrice(20);
        discMock.setStock(4);

        when(sequenceGeneratorService.generateSequence(Disc.SEQUENCE_NAME)).thenReturn(4l);
        when(discRepository.save(discMock)).thenReturn(discMock);
        // WHEN
        Disc disc = discService.save(discMock);
        // THEN
        assertThat(disc).isNotNull();
        assertThat(disc.getId()).isEqualTo(4);
    }

    @Test
    public void shouldGetDiscByIdBand() {
        // GIVEN
        List<Disc> discListBandMock = discListMock.stream().filter(x -> x.getIdBand() == 1)
                .collect(Collectors.toList());

        Query query = new Query();
        query.addCriteria(Criteria.where("idBand").is(1l));
        when(mongoTemplate.find(query, Disc.class)).thenReturn(discListBandMock);

        // WHEN
        List<Disc> discList = discService.getDiscsByIdBand(1l);

        // THEN
        assertThat(discList).isNotNull().isNotEmpty().hasSize(2);
    }

    @Test
    public void shouldUpdateDisc() {
        // GIVEN
        Disc discMock = new Disc();
        discMock.setId(2);
        discMock.setTitle("Highway to Hell");
        discMock.setYear("1979");
        discMock.setIdBand(2);
        discMock.setPrice(20);
        discMock.setStock(4);

        Optional<Disc> discMockOp = Optional.of(discMock);

        Disc updateDisc = new Disc();
        updateDisc.setId(2);
        updateDisc.setTitle("Highway to Hell Mod");
        updateDisc.setYear("1979");
        updateDisc.setIdBand(2);
        updateDisc.setPrice(20);
        updateDisc.setStock(4);

        when(discRepository.findById(1)).thenReturn(discMockOp);
        when(discRepository.save(discMock)).thenReturn(updateDisc);
        // WHEN
        Disc discUpdated = discService.update(1, updateDisc);
        // THEN
        assertThat(discUpdated.getTitle()).isEqualTo("Highway to Hell Mod");
    }

}
