package org.isj.ing4.isi.music.mapper;

import org.isj.ing4.isi.music.dto.ArtisteDto;
import org.isj.ing4.isi.music.model.Artiste;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtisteMapper extends EntityMapper<ArtisteDto, Artiste> {
}