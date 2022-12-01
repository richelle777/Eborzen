package org.isj.ing4.isi.music.mapper;

import org.isj.ing4.isi.music.PlaylistTitreDto;
import org.isj.ing4.isi.music.dto.ArtisteTitreDto;
import org.isj.ing4.isi.music.model.ArtisteTitre;
import org.isj.ing4.isi.music.model.PlaylistTitre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaylistTitreMapper extends EntityMapper<PlaylistTitreDto, PlaylistTitre>{
}
