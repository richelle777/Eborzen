package org.isj.ing4.isi.music.repository;

import org.isj.ing4.isi.music.model.ArtisteAlbum;
import org.isj.ing4.isi.music.model.ArtisteAlbumId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtisteAlbumRepository extends JpaRepository<ArtisteAlbum, ArtisteAlbumId>, JpaSpecificationExecutor<ArtisteAlbum> {
}