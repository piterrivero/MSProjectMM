package com.genre.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.genre.service.entity.Genre;
import com.genre.service.repository.GenreRepository;
import com.genre.service.service.GenreService;
import com.genre.service.service.SequenceGeneratorService;

@ExtendWith(MockitoExtension.class)
class GenreServiceApplicationTests {

	// There are created the mocks object that are used inside the service
	@Mock
	private GenreRepository genreRepository;
	@Mock
	private SequenceGeneratorService sequenceGeneratorService;
	
	// There is created an instance of the service and injected the mocks declared before
	@InjectMocks
	private GenreService genreService;

	private static List<Genre> genreListMock;
	
	@BeforeAll
	static void init() {
		genreListMock = new ArrayList<>();
		genreListMock.add(Genre.builder().id(1).genre("Heavy Metal").build());
	}
	
	@Test
	public void shouldListAllGenres() {
		// GIVEN
		when(genreRepository.findAll()).thenReturn(genreListMock);
		// WHEN
		List<Genre> genreList = genreService.getAll();
		// THEN
		assertThat(genreList).isNotNull()
							 .isNotEmpty()
							 .hasSize(1);
	}
	
	@Test
	public void shouldGetGenreById() {
		// GIVEN
		Optional<Genre> genreMock = Optional.of(genreListMock.get(0));
		when(genreRepository.findById(1)).thenReturn(genreMock);
		// WHEN
		Genre genre = genreService.getGenreById(1);
		// THEN
		assertThat(genre).isNotNull();
		assertThat(genre.getGenre()).isEqualTo("Heavy Metal");
	}
	
	@Test
	public void shouldSaveGenre() {
		// GIVEN
		Genre genreMock = Genre.builder().genre("Trash Metal").build();
		when(genreRepository.save(genreMock)).thenReturn(genreMock);
		when(sequenceGeneratorService.generateSequence(Genre.SEQUENCE_NAME)).thenReturn(2L);
		// WHEN
		Genre genre = genreService.save(genreMock);
		// THEN
		assertThat(genre).isNotNull();
		assertThat(genre.getId()).isEqualTo(2);
	}
	
	@Test
	public void shouldUpdateGenre() {
		// GIVEN
		Genre genreMock = Genre.builder().id(1).genre("Heavy Metal").build();
		Optional<Genre> genreMockOp = Optional.of(genreMock);
		Genre updateGenre = Genre.builder().id(1).genre("Heavy Metal Mod").build();
		
		when(genreRepository.findById(1)).thenReturn(genreMockOp);
		when(genreRepository.save(genreMock)).thenReturn(updateGenre);
		
		// WHEN
		Genre genreUpdated = genreService.update(1, updateGenre);
		// THEN
		assertThat(genreUpdated.getGenre()).isEqualTo("Heavy Metal Mod");
	}
	
}
