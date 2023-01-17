package org.isj.ing4.isi.music.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HistoriqueId implements Serializable {
    private static final long serialVersionUID = 7554052092920665141L;
    @Column(name = "id_musique", nullable = false)
    private Integer idMusique;
    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdMusique() {
        return idMusique;
    }

    public void setIdMusique(Integer idMusique) {
        this.idMusique = idMusique;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idMusique);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HistoriqueId entity = (HistoriqueId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idMusique, entity.idMusique);
    }
}