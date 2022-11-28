package org.isj.ing4.isi.music.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class AlbumDto {
    private Integer id;
    @NotNull
    private Date date;
    @NotNull
    private String producteur;
    @NotNull
    private String nom;
    @NotNull
    private byte[] image;

    public AlbumDto() {
    }


}