package org.isj.ing4.isi.music.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistTitreId implements Serializable {
    private static final long serialVersionUID = 1446193526366527874L;
    @Column(name = "id_titre", nullable = false)
    private Integer idTitre;
    @Column(name = "id_playlist", nullable = false)
    private Integer idPlaylist;
}