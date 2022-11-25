package org.isj.ing4.isi.music.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArtisteTitreId implements Serializable {
    private static final long serialVersionUID = 4535382259155880561L;
    @Column(name = "id_artiste", nullable = false)
    private Integer idArtiste;
    @Column(name = "id_titre", nullable = false)
    private Integer idTitre;

    public Integer getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Integer idTitre) {
        this.idTitre = idTitre;
    }

    public Integer getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTitre, idArtiste);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ArtisteTitreId entity = (ArtisteTitreId) o;
        return Objects.equals(this.idTitre, entity.idTitre) &&
                Objects.equals(this.idArtiste, entity.idArtiste);
    }
}