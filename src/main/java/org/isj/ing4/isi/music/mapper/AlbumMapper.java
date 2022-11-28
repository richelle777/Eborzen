package org.isj.ing4.isi.music.mapper;

import org.isj.ing4.isi.music.dto.AlbumDto;
import org.isj.ing4.isi.music.model.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlbumMapper extends EntityMapper<AlbumDto, Album> {
}