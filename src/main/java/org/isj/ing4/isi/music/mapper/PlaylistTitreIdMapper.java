package org.isj.ing4.isi.music.mapper;


import org.isj.ing4.isi.music.PlaylistTitreDto;
import org.isj.ing4.isi.music.dto.PlaylistTitreIdDto;
import org.isj.ing4.isi.music.model.PlaylistTitre;
import org.isj.ing4.isi.music.model.PlaylistTitreId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaylistTitreIdMapper extends EntityMapper<PlaylistTitreIdDto, PlaylistTitreId>{
}
