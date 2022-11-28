package org.isj.ing4.isi.music.dto;

import lombok.Data;
import org.isj.ing4.isi.music.model.Album;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class TitreDto extends AbstractDto {
    @Max(Integer.MAX_VALUE)
    private Integer id;
    @NotNull
    private String intitule;
    @NotNull
    private Date date;
    private AlbumDto idAlbum;
    @NotNull
    private String audio;
    @Size(max = 255)
    @NotBlank
    private String paroles;
    @NotNull
    private String video;
    private String duree;
    private String image;
    public TitreDto() {
    }


}