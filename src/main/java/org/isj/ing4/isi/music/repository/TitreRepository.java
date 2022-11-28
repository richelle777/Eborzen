package org.isj.ing4.isi.music.repository;

import org.isj.ing4.isi.music.model.Titre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitreRepository extends JpaRepository<Titre, Integer>, JpaSpecificationExecutor<Titre> {

    @Query(value = "SELECT DISTINCT(titre.id_titre), titre.intitule, titre.date, titre.id_album, titre.audio, titre.paroles, titre.video, titre.image, titre.duree FROM titre NATURAL JOIN (artiste_titre NATURAL JOIN artiste) WHERE artiste.surnom LIKE %:mot% OR titre.intitule LIKE %:mot%", nativeQuery = true)
    List<Titre> searchByArtisteOrSong(@Param("mot") String mot);
}