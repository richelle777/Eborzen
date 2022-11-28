package org.isj.ing4.isi.music.mapper;

import org.isj.ing4.isi.music.dto.TitreDto;
import org.isj.ing4.isi.music.model.Titre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TitreMapper extends EntityMapper<TitreDto, Titre> {
}