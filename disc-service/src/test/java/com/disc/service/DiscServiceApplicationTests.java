package com.disc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.disc.service.entity.Disc;
import com.disc.service.repository.DiscRepository;
import com.disc.service.service.DiscService;
import com.disc.service.service.SequenceGeneratorService;

@ExtendWith(MockitoExtension.class)
class DiscServiceApplicationTests {

	// There are created the mocks object that are used inside the service
	@Mock
	private DiscRepository discRepository;
	@Mock
	private SequenceGeneratorService sequenceGeneratorService;
	@Mock
	private MongoTemplate mongoTemplate;

	// There is created an instance of the service and injected the mocks declared
	// before
	@InjectMocks
	private DiscService discService;

	private static List<Disc> discListMock;

	@BeforeAll
	static void init() {
		discListMock = new ArrayList<>();
		discListMock
				.add(Disc.builder().id(1).title("Fear of the dark").year("1992").idBand(1).price(20).stock(4).build());
		discListMock
				.add(Disc.builder().id(2).title("Live After Death").year("1985").idBand(1).price(25).stock(6).build());
		discListMock.add(Disc.builder().id(3).title("Back in Black").year("1980").idBand(2).price(30).stock(2).build());
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
		Disc discMock = Disc.builder().idBand(2).title("Highway to Hell").year("1979").price(20).stock(4).build();
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
		Disc discMock = Disc.builder().idBand(2).title("Highway to Hell").year("1979").price(20).stock(4).build();
		Optional<Disc> discMockOp = Optional.of(discMock);
		Disc updateDisc = Disc.builder().idBand(2).title("Highway to Hell Mod").year("1979").price(20).stock(4).build();
		when(discRepository.findById(1)).thenReturn(discMockOp);
		when(discRepository.save(discMock)).thenReturn(updateDisc);
		// WHEN
		Disc discUpdated = discService.update(1, updateDisc);
		// THEN
		assertThat(discUpdated.getTitle()).isEqualTo("Highway to Hell Mod");
	}

}
