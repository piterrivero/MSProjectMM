package com.band.service;

import com.band.service.entity.Band;
import com.band.service.feignclients.DiscFeignClient;
import com.band.service.feignclients.GenreFeignClient;
import com.band.service.kafka.KafkaSender;
import com.band.service.model.DiscDTO;
import com.band.service.model.GenreDTO;
import com.band.service.repository.BandRepository;
import com.band.service.service.BandService;
import com.band.service.service.SequenceGeneratorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BandServiceApplicationTests {

    private static List<Band> bandListMock;
    private static List<DiscDTO> discListMock;
    // There are created the mocks object that are used inside the service
    @Mock
    private BandRepository bandRepository;
    @Mock
    private SequenceGeneratorService sequenceGeneratorService;
    @Mock
    private DiscFeignClient discFeignClient;
    @Mock
    private GenreFeignClient genreFeignClient;
    @Mock
    private KafkaSender kafkaSender;
    // There is created an instance of the service and injected the mocks declared before
    @InjectMocks
    private BandService bandService;

    @BeforeAll
    static void init() {
        bandListMock = new ArrayList<>();
        Band band = new Band();
        band.setId(1);
        band.setIdGenre(1);
        band.setName("Iron Maiden");
        bandListMock.add(band);

        discListMock = new ArrayList<>();
        DiscDTO discDTO = new DiscDTO();
        discDTO.setTitle("Fear of the dark");
        discDTO.setYear("1992");
        discDTO.setIdBand(1);
        discListMock.add(discDTO);
        DiscDTO discDTO2 = new DiscDTO();
        discDTO2.setTitle("Live After Death");
        discDTO2.setYear("1985");
        discDTO2.setIdBand(1);
        discListMock.add(discDTO2);
    }

    @Test
    public void shouldListAllBands() {
        // GIVEN
        when(bandRepository.findAll()).thenReturn(bandListMock);
        // WHEN
        List<Band> bandsList = bandService.getAll();
        // THEN
        assertThat(bandsList).isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    public void shouldGetBandsById() {
        // GIVEN
        Optional<Band> bandMock = Optional.of(bandListMock.get(0));
        when(bandRepository.findById(1)).thenReturn(bandMock);
        // WHEN
        Band band = bandService.getBandById(1);
        // THEN
        assertThat(band).isNotNull();
        assertThat(band.getName()).isEqualTo("Iron Maiden");
    }

    @Test
    public void shouldSaveBand() {
        // GIVEN
        Band bandMock = new Band();
        bandMock.setIdGenre(2);
        bandMock.setName("ACDC");
        bandMock.setCountry("Australia");

        when(bandRepository.save(bandMock)).thenReturn(bandMock);
        when(sequenceGeneratorService.generateSequence(Band.SEQUENCE_NAME)).thenReturn(2L);
        // WHEN
        Band band = bandService.save(bandMock);
        // THEN
        assertThat(band).isNotNull();
        assertThat(band.getId()).isEqualTo(2);
    }

    @Test
    public void shouldSaveDisc() {
        DiscDTO discMock = new DiscDTO();
        discMock.setTitle("Black Ice");
        discMock.setYear("2008");
        discMock.setIdBand(2);
        // GIVEN
        when(discFeignClient.saveDisc(discMock)).thenReturn(discMock);
        // WHEN
        DiscDTO disc = bandService.saveDisc(discMock);
        // THEN
        assertThat(disc).isNotNull();
        assertThat(disc.getTitle()).isEqualTo("Black Ice");
    }

    @Test
    public void shouldGetGenreById() {
        // GIVEN
        GenreDTO genreMock = new GenreDTO();
        genreMock.setGenre("Trash Metal");
        when(genreFeignClient.getGenreById(1)).thenReturn(genreMock);
        // WHEN
        GenreDTO genre = bandService.getGenreById(1);
        // THEN
        assertThat(genre).isNotNull();
        assertThat(genre.getGenre()).isEqualTo("Trash Metal");
    }

    @Test
    public void shouldListDiscsByIdBand() {
        // GIVEN
        when(discFeignClient.listDiscsByIdBand(1)).thenReturn(discListMock);
        // WHEN
        List<DiscDTO> discList = bandService.getDiscByIdBand(1);
        // THEN
        assertThat(discList).isNotNull()
                .isNotEmpty()
                .hasSize(2);
    }

    @Test
    public void shouldUpdateBand() {
        // GIVEN
        Band bandMock = new Band();
        bandMock.setId(1);
        bandMock.setIdGenre(1);
        bandMock.setName("ACDC");
        bandMock.setCountry("Australia");

        Band updateBand = new Band();
        updateBand.setId(1);
        updateBand.setIdGenre(1);
        updateBand.setName("ACDC Mod");
        updateBand.setCountry("Australia");

        Optional<Band> bandMockOp = Optional.of(bandMock);

        when(bandRepository.findById(1)).thenReturn(bandMockOp);
        when(bandRepository.save(bandMock)).thenReturn(updateBand);
        // WHEN
        Band bandUpdated = bandService.update(1, updateBand);
        // THEN
        assertThat(bandUpdated.getName()).isEqualTo("ACDC Mod");
    }

}
