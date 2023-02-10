package com.band.service.mapper;

import com.band.service.entity.Band;
import com.band.service.model.BandDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BandMapper {

    BandDTO modelToDto(Band band);

    Band dtoToModel(BandDTO bandDTO);

    List<BandDTO> modelsToDto(List<Band> bands);

    List<Band> dtoToModel(List<BandDTO> bandDTOS);

}
