package org.isj.ing4.isi.music.service;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.PlaylistTitreDto;
import org.isj.ing4.isi.music.dto.PlaylistDto;
import org.isj.ing4.isi.music.dto.TitreDto;
import org.isj.ing4.isi.music.exception.ErrorInfo;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.mapper.*;
import org.isj.ing4.isi.music.model.*;
import org.isj.ing4.isi.music.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class PlaylistService {
    private final PlaylistRepository playlistrepository;
    private final PlaylistTitreRepository playlistTitreRepository;
    private final TitreRepository titreRepository;
//    private final PlaylistTitreIdRepository playlistTitreIdRepository;
    private final PlaylistMapper playlistMapper;
    private final UserMapper userMapper;
    private final TitreMapper titreMapper;
    private  final UserRepository userRepository;
    private final PlaylistTitreMapper playlistTitreMapper;
    private final PlaylistTitreIdMapper playlistTitreIdMapper;



    public PlaylistDto findById(Integer id) throws IsjException {
        return playlistMapper.toDto(playlistrepository.findById(id).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)));
    }

    public PlaylistService(PlaylistRepository playlistrepository, PlaylistTitreRepository playlistTitreRepository, PlaylistMapper playlistMapper, UserMapper userMapper, PlaylistTitreMapper playlistTitreMapper, TitreRepository titreRepository, TitreMapper titreMapper, UserRepository userRepository, PlaylistTitreIdMapper playlistTitreIdMapper) {
        this.playlistrepository = playlistrepository;
        this.playlistTitreRepository = playlistTitreRepository;
//        this.playlistTitreIdRepository = playlistTitreIdRepository;
        this.playlistMapper = playlistMapper;
        this.userMapper = userMapper;
        this.playlistTitreMapper = playlistTitreMapper;
        this.titreRepository = titreRepository;
        this.titreMapper = titreMapper;
        this.userRepository = userRepository;
        this.playlistTitreIdMapper = playlistTitreIdMapper;
    }

    public List<PlaylistDto> findAll() {
        return playlistrepository.findAll().stream().map(playlistMapper::toDto).collect(Collectors.toList());
    }

    //pour ajouter une nouvelle playlist
    public PlaylistDto save(PlaylistDto playlistDto) {
        Playlist entity = playlistMapper.toEntity(playlistDto);
        return playlistMapper.toDto(playlistrepository.save(entity));
    }

    //pour modifier une playlist
    public PlaylistDto update(PlaylistDto playlistDto) throws IsjException {
        Playlist data = playlistrepository.findById(playlistDto.getId()).get();
        playlistMapper.copy(playlistDto , data);
        return  playlistMapper.toDto(playlistrepository.save(data));
    }

    //pour lister les musiques d'un utilisateur
    public List<PlaylistDto> playlistOfUser(String email){
        return playlistrepository.findPlaylistByUser(email).stream().map(playlistMapper::toDto).collect(Collectors.toList());
    }

    //pour ajouter une musique dans une playlist
    public PlaylistTitreDto addMusicToPlaylist(int idTitre , int idPlaylist) {
        Titre titre = titreRepository.getById(idTitre);
        Playlist playlist = playlistrepository.getById(idPlaylist);

        PlaylistTitreId playlistTitreId = new PlaylistTitreId();
        playlistTitreId.setIdTitre(titre.getId());
        playlistTitreId.setIdPlaylist(playlist.getId());

        PlaylistTitre playlistTitre = new PlaylistTitre();
        playlistTitre.setId(playlistTitreId);

        return playlistTitreMapper.toDto(playlistTitreRepository.save(playlistTitre));
    }

    //pour lister les musiques d'une playlist
    public List<TitreDto> listMusicOfPlaylist(int idPlaylist) {
        return titreRepository.findTitreByIdPlaylist(idPlaylist).stream().map(titreMapper::toDto).collect(Collectors.toList());
    }

    //pour la suppression d'une playlist
    public int deletePlaylistById(int id) {
        playlistTitreRepository.deletePlaylistById(id);
        playlistrepository.deletePlaylistById(id);
        return 1;
    }
}
