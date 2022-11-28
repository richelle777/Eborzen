package org.isj.ing4.isi.music.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "artiste_album")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtisteAlbum {
    @EmbeddedId
    private ArtisteAlbumId id;

    @MapsId("idArtiste")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_artiste", nullable = false)
    private Artiste idArtiste;

    @MapsId("idAlbum")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_album", nullable = false)
    private Album idAlbum;

}