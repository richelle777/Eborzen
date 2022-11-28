package org.isj.ing4.isi.music.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArtisteAlbumId implements Serializable {
    private static final long serialVersionUID = 8422544042827149558L;
    @Column(name = "id_artiste", nullable = false)
    private Integer idArtiste;
    @Column(name = "id_album", nullable = false)
    private Integer idAlbum;

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Integer getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArtiste, idAlbum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ArtisteAlbumId entity = (ArtisteAlbumId) o;
        return Objects.equals(this.idArtiste, entity.idArtiste) &&
                Objects.equals(this.idAlbum, entity.idAlbum);
    }
}