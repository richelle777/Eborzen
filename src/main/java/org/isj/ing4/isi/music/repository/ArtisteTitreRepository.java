package org.isj.ing4.isi.music.repository;

import org.isj.ing4.isi.music.model.ArtisteTitre;
import org.isj.ing4.isi.music.model.ArtisteTitreId;
import org.isj.ing4.isi.music.model.Titre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtisteTitreRepository extends JpaRepository<ArtisteTitre, ArtisteTitreId>, JpaSpecificationExecutor<ArtisteTitre> {
    List<ArtisteTitre> findByIdtitre(Titre titre);

}