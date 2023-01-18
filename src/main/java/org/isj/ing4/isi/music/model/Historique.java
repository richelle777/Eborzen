package org.isj.ing4.isi.music.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "historique", indexes = {
        @Index(name = "id_titre", columnList = "id_titre"),
        @Index(name = "id_user", columnList = "id_user")
})
@Data
public class Historique {
    @EmbeddedId
    private HistoriqueId id;

    @MapsId("idTitre")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_titre", nullable = false)
    private Titre idTitre;

    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User idUser;

}