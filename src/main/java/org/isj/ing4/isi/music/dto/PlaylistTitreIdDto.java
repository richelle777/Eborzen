package org.isj.ing4.isi.music.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlaylistTitreIdDto implements Serializable {
    private Integer idTitre;
    private Integer idPlaylist;
}
