package org.isj.ing4.isi.music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtisteDtoList {
    private TitreDto titreDto;
    List<ArtisteDto> artisteDtos;
}
