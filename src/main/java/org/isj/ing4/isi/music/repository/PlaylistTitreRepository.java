package org.isj.ing4.isi.music.repository;

import org.isj.ing4.isi.music.model.Playlist;
import org.isj.ing4.isi.music.model.PlaylistTitre;
import org.isj.ing4.isi.music.model.PlaylistTitreId;
import org.isj.ing4.isi.music.model.Titre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistTitreRepository extends JpaRepository<PlaylistTitre, PlaylistTitreId>, JpaSpecificationExecutor<PlaylistTitre> {

}
