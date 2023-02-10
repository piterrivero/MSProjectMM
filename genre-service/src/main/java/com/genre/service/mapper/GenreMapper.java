package com.genre.service.mapper;

import com.genre.service.entity.Genre;
import com.genre.service.model.GenreDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO modelToDto(Genre genre);

    Genre dtoToModel(GenreDTO genreDTO);

    List<GenreDTO> modelsToDto(List<Genre> genres);

    List<Genre> dtoToModel(List<GenreDTO> genreDTOS);

}
