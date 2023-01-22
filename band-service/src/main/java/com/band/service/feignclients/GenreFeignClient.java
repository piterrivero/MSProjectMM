package com.band.service.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.band.service.model.GenreDTO;

@FeignClient(name = "genre-service")
@RequestMapping("/genre")
public interface GenreFeignClient {

	@GetMapping("/{genreId}")
	public GenreDTO getGenreById(@PathVariable("genreId") long usuarioId);
	
}
