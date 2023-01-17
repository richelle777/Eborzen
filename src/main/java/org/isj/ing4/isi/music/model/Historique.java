package org.isj.ing4.isi.music.model;

import javax.persistence.*;

@Entity
@Table(name = "historique", indexes = {
        @Index(name = "id_musique", columnList = "id_musique"),
        @Index(name = "id_user", columnList = "id_user")
})
public class Historique {
    @EmbeddedId
    private HistoriqueId id;

    @MapsId("idMusique")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_musique", nullable = false)
    private Titre idMusique;

    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Titre getIdMusique() {
        return idMusique;
    }

    public void setIdMusique(Titre idMusique) {
        this.idMusique = idMusique;
    }

    public HistoriqueId getId() {
        return id;
    }

    public void setId(HistoriqueId id) {
        this.id = id;
    }
}