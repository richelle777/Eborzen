package org.isj.ing4.isi.music.service;

import org.isj.ing4.isi.music.model.Historique;
import org.isj.ing4.isi.music.model.HistoriqueId;
import org.isj.ing4.isi.music.model.Titre;
import org.isj.ing4.isi.music.model.User;
import org.isj.ing4.isi.music.repository.HistoriqueRepository;
import org.isj.ing4.isi.music.repository.TitreRepository;
import org.isj.ing4.isi.music.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoriqueService {

    @Autowired
    HistoriqueRepository historiqueRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TitreRepository titreRepository;

    public Historique save(String email, int idMusic) {
        User user = userRepository.findByEmail(email);
        Titre titre = titreRepository.findById(idMusic).get();

        Historique historique = new Historique();
        historique.setIdTitre(titre);
        historique.setIdUser(user);
        HistoriqueId historiqueId = new HistoriqueId();
        historiqueId.setIdTitre(titre.getId());
        historiqueId.setIdUser(user.getId());
        historique.setId(historiqueId);
        return historiqueRepository.save(historique);
    }

}
