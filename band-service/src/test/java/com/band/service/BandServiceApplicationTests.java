package com.band.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.band.service.kafka.KafkaSender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.band.service.entity.Band;
import com.band.service.feignclients.DiscFeignClient;
import com.band.service.feignclients.GenreFeignClient;
import com.band.service.model.DiscDTO;
import com.band.service.model.GenreDTO;
import com.band.service.repository.BandRepository;
import com.band.service.service.BandService;
import com.band.service.service.SequenceGeneratorService;

@ExtendWith(MockitoExtension.class)
class BandServiceApplicationTests {

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

	private static List<Band> bandListMock;
	private static List<DiscDTO> discListMock;
	
	@BeforeAll
	static void init() {
		bandListMock = new ArrayList<>();
		bandListMock.add(Band.builder().id(1).idGenre(1).name("Iron Maiden").build());
		
		discListMock = new ArrayList<>();
		discListMock.add(DiscDTO.builder().title("Fear of the dark").year("1992").idBand(1).build());
		discListMock.add(DiscDTO.builder().title("Live After Death").year("1985").idBand(1).build());
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
		Band bandMock = Band.builder().name("ACDC").idGenre(2).country("Australia").build();
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
		// GIVEN
		DiscDTO discMock = DiscDTO.builder().title("Black Ice").year("2008").idBand(2).build();
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
		GenreDTO genreMock = GenreDTO.builder().genre("Trash Metal").build();
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
		Band bandMock = Band.builder().name("ACDC").id(1).idGenre(1).country("Australia").build();
		Optional<Band> bandMockOp = Optional.of(bandMock);
		Band updateBand = Band.builder().name("ACDC Mod").id(1).idGenre(1).country("Australia").build();
		when(bandRepository.findById(1)).thenReturn(bandMockOp);
		when(bandRepository.save(bandMock)).thenReturn(updateBand);
		// WHEN
		Band bandUpdated = bandService.update(1, updateBand);
		// THEN
		assertThat(bandUpdated.getName()).isEqualTo("ACDC Mod");
	}
	
}
