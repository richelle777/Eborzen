package org.isj.ing4.isi.music.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "playlist_titre", indexes = {
        @Index(name = "id_playlist", columnList = "id_playlist")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistTitre {
    @EmbeddedId
    private PlaylistTitreId id;

    public PlaylistTitreId getId() {
        return id;
    }

    public void setId(PlaylistTitreId id) {
        this.id = id;
    }
}