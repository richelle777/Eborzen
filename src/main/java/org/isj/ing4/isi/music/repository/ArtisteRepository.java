package org.isj.ing4.isi.music.repository;

import org.isj.ing4.isi.music.model.Artiste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtisteRepository extends JpaRepository<Artiste, Integer>, JpaSpecificationExecutor<Artiste> {
}