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
    @Query(value = "SELECT tit.id_titre , tit.intitule , tit.date , tit.id_album , tit.audio , tit.paroles , tit.video , tit.image , tit.duree FROM `titre` as tit INNER JOIN  `playlist_titre` as play ON tit.id_titre=play.id_titre  where play.id_playlist = :idP" , nativeQuery = true)
    List<Titre> findTitreByIdPlaylist(int  idP);
    //pour recuperer toutes les musiques appartenant a la playlist ayant pour id id.

    @Query(value = "SELECT tit.id_titre , tit.intitule , tit.date , tit.id_album , tit.audio , tit.paroles , tit.video , tit.image , tit.duree, tit.prix FROM `titre` as tit INNER JOIN  `historique` as play ON tit.id_titre=play.id_titre  where play.id_user = :idP LIMIT 5" , nativeQuery = true)
    List<Titre> findHistoriqueTitreByUser(int  idP);
}