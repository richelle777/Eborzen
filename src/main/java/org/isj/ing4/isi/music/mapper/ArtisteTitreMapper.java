package org.isj.ing4.isi.music.mapper;

import org.isj.ing4.isi.music.dto.ArtisteTitreDto;
import org.isj.ing4.isi.music.model.ArtisteTitre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtisteTitreMapper extends EntityMapper<ArtisteTitreDto, ArtisteTitre> {
}