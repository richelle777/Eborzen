package org.isj.ing4.isi.music.repository;

import org.isj.ing4.isi.music.model.Playlist;
import org.isj.ing4.isi.music.model.Titre;
import org.isj.ing4.isi.music.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer>, JpaSpecificationExecutor<Playlist> {
    @Query(value = "SELECT intitule_playlist,id_playlist,playlist.user_id FROM `playlist` INNER JOIN `user` ON playlist.user_id=user.user_id  where user.email = :email" , nativeQuery = true)
    List<Playlist> findPlaylistByUser(String  email);

    @Modifying
    @Query(value = "DELETE FROM `playlist` where playlist.id_playlist = :id" , nativeQuery = true)
    void deletePlaylistById(int id);
}
