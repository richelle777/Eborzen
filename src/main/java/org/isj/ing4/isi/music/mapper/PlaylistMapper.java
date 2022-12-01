package org.isj.ing4.isi.music.mapper;

import org.isj.ing4.isi.music.dto.PlaylistDto;
import org.isj.ing4.isi.music.model.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface PlaylistMapper extends EntityMapper<PlaylistDto, Playlist> {
    void copy(PlaylistDto playlistDTO, @MappingTarget Playlist playlist);
}
