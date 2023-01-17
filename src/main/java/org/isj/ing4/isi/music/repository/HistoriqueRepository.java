package org.isj.ing4.isi.music.repository;

import org.isj.ing4.isi.music.model.Historique;
import org.isj.ing4.isi.music.model.HistoriqueId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriqueRepository extends JpaRepository<Historique, HistoriqueId> {
}