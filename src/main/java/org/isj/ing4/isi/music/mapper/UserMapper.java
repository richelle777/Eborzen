package org.isj.ing4.isi.music.mapper;

import org.isj.ing4.isi.music.dto.AlbumDto;
import org.isj.ing4.isi.music.dto.UserDto;
import org.isj.ing4.isi.music.model.Album;
import org.isj.ing4.isi.music.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
}
