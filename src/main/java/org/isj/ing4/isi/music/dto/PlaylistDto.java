package org.isj.ing4.isi.music.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PlaylistDto extends AbstractDto{
    @Max(Integer.MAX_VALUE)
    private  Integer id;
    private String intitulePlaylist;
    private int etatPartage = 0;
    private UserDto user;
    public PlaylistDto() {
    }
}
