//package org.isj.ing4.isi.music.repository;
//
//import org.isj.ing4.isi.music.model.Playlist;
//import org.isj.ing4.isi.music.model.PlaylistTitreId;
//import org.isj.ing4.isi.music.model.Titre;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//
//@Repository
//public interface PlaylistTitreIdRepository extends JpaRepository<PlaylistTitreId, Integer>, JpaSpecificationExecutor<PlaylistTitreId> {
//    @Query(value = "SELECT * FROM `titre` INNER JOIN LEFT `playlist_titre` ON titre.id_titre=playlist_titre.id_titre  where playlist_titre.id_playlist = :idP" , nativeQuery = true)
//    List<Titre> findTitreByIdPlaylist(int  idP);
//    //pour recuperer toutes les musiques appartenant a la playlist ayant pour id id.
//}