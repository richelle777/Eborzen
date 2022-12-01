package org.isj.ing4.isi.music.service;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.PlaylistTitreDto;
import org.isj.ing4.isi.music.dto.PlaylistTitreIdDto;
import org.isj.ing4.isi.music.mapper.PlaylistTitreIdMapper;
import org.isj.ing4.isi.music.mapper.PlaylistTitreMapper;
import org.isj.ing4.isi.music.model.Playlist;
import org.isj.ing4.isi.music.model.PlaylistTitre;
import org.isj.ing4.isi.music.model.PlaylistTitreId;
import org.isj.ing4.isi.music.model.Titre;
import org.isj.ing4.isi.music.repository.PlaylistRepository;
import org.isj.ing4.isi.music.repository.PlaylistTitreRepository;
import org.isj.ing4.isi.music.repository.TitreRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class PlaylistTitreService {
//    public PlaylistDto findById(Integer id) throws IsjException {
//        return playlistMapper.toDto(repository.findById(id).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)));
//    }
    private  PlaylistRepository repository;
    private PlaylistTitreRepository playlistTitreRepository;
    private PlaylistTitreMapper playlistTitreMapper;
    private TitreRepository titreRepository;

    public PlaylistTitreDto save(PlaylistTitreDto playlistTitreDto) {
        PlaylistTitre entityTitre = new PlaylistTitre();
        PlaylistTitreId entityId = new PlaylistTitreId();
        Playlist playlist = repository.findById(playlistTitreDto.getId().getIdPlaylist()).get();
        Titre titre = titreRepository.findById(playlistTitreDto.getId().getIdTitre()).get();
        entityId.setIdPlaylist(playlist.getId());
        entityId.setIdTitre(titre.getId());
        entityTitre.setId(entityId);
        return playlistTitreMapper.toDto(playlistTitreRepository.save(entityTitre));
    }
}
