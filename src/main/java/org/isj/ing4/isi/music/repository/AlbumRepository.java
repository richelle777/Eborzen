package org.isj.ing4.isi.music.repository;

import org.isj.ing4.isi.music.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer>, JpaSpecificationExecutor<Album> {
}