package org.isj.ing4.isi.music.mapper;

import org.isj.ing4.isi.music.dto.ArtisteAlbumDto;
import org.isj.ing4.isi.music.model.ArtisteAlbum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtisteAlbumMapper extends EntityMapper<ArtisteAlbumDto, ArtisteAlbum> {
}