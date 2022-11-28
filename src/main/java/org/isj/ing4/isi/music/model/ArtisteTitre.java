package org.isj.ing4.isi.music.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "artiste_titre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtisteTitre {
    @EmbeddedId
    private ArtisteTitreId id;

    @MapsId("idArtiste")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_artiste", nullable = false)
    private Artiste idartiste;

    @MapsId("idTitre")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_titre", nullable = false)
    private Titre idtitre;

}