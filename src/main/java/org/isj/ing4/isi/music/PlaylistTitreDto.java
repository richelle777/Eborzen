package org.isj.ing4.isi.music;

import lombok.Data;
import org.isj.ing4.isi.music.dto.PlaylistTitreIdDto;

import java.io.Serializable;

@Data
public class PlaylistTitreDto implements Serializable {
    private final PlaylistTitreIdDto id;
}
