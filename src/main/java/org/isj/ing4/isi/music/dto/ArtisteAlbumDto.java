package org.isj.ing4.isi.music.dto;

import lombok.Data;
import org.isj.ing4.isi.music.model.Album;
import org.isj.ing4.isi.music.model.Artiste;
import org.isj.ing4.isi.music.model.ArtisteAlbumId;

@Data
public class ArtisteAlbumDto {
    private ArtisteDto idArtiste;
    private AlbumDto idAlbum;


    public ArtisteAlbumDto() {
    }


}