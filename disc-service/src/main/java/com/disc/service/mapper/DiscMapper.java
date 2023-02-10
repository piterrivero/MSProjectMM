package com.disc.service.mapper;

import com.disc.service.entity.Disc;
import com.disc.service.model.DiscDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscMapper {

    DiscDTO modelToDto(Disc disc);

    Disc dtoToModel(DiscDTO discDTO);

    List<DiscDTO> modelsToDto(List<Disc> discs);

    List<Disc> dtoToModel(List<DiscDTO> discDTOS);

}
