package org.isj.ing4.isi.music.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isj.ing4.isi.music.model.Artiste;
import org.isj.ing4.isi.music.model.ArtisteTitreId;
import org.isj.ing4.isi.music.model.Titre;


public class ArtisteTitreDto extends AbstractDto<ArtisteTitreId> {
    private ArtisteDto idArtiste;
    private TitreDto idTitre;

}