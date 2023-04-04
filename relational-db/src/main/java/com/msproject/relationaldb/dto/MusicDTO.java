package com.msproject.relationaldb.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicDTO {

    private List<DiscDTO> disc;

}
