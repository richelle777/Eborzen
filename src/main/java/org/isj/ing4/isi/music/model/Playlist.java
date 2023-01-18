package org.isj.ing4.isi.music.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isj.ing4.isi.music.model.User;

import javax.persistence.*;

@Entity
@Table(name = "playlist", indexes = {
        @Index(name = "user_id", columnList = "user_id")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_playlist", nullable = false)
    private Integer id;

    @Column(name = "intitule_playlist", nullable = false)
    private String intitulePlaylist;

    @Column(name = "etat_partage", nullable = false)
    private int etatPartage = 0;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL ,optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}