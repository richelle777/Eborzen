package org.isj.ing4.isi.music.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isj.ing4.isi.music.model.PlaylistTitreId;

import java.io.Serializable;


@Data
public class PlaylistTitreDto{
    @JsonProperty("idPlaylist")
    private PlaylistTitreIdDto idPlaylist;


    public PlaylistTitreDto() {
    }
}
