package com.band.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.band.service.entity.Band;
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
	
	// There is created an instance of the service and injected the mocks declared before
	@InjectMocks
	private BandService bandService;

	private static List<Band> bandListMock;
	
	@BeforeAll
	static void init() {
		bandListMock = new ArrayList<>();
		bandListMock.add(Band.builder().id(1).idGenre(1).name("Iron Maiden").build());
	}
	
	@Test
	public void shouldListAllBands() {
		// GIVEN
		// WHEN
		// THEN
	}
	
	@Test
	public void shouldGetBandsById() {
		// GIVEN
		// WHEN
		// THEN
	}
	
	@Test
	public void shouldSaveBand() {
		// GIVEN
		// WHEN
		// THEN
	}
	
	@Test
	public void shouldSaveDisc() {
		// GIVEN
		// WHEN
		// THEN
	}
	
	@Test
	public void shouldGetGenreById() {
		// GIVEN
		// WHEN
		// THEN
	}
	
	@Test
	public void shouldListDiscsByIdBand() {
		// GIVEN
		// WHEN
		// THEN
	}

}
