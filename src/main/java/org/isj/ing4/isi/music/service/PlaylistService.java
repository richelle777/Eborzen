package org.isj.ing4.isi.music.service;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.PlaylistTitreDto;
import org.isj.ing4.isi.music.dto.*;
import org.isj.ing4.isi.music.mapper.*;
import org.isj.ing4.isi.music.model.*;
import org.isj.ing4.isi.music.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    private final ArtisteMapper artisteMapper;
    private final ArtisteTitreRepository artisteTitreRepository;
    @Autowired
    private UserService userService;




    public PlaylistDto findById(Integer id) {
        return playlistMapper.toDto(playlistrepository.findById(id).get());
    }

    public PlaylistService(PlaylistRepository playlistrepository, PlaylistTitreRepository playlistTitreRepository, PlaylistMapper playlistMapper, UserMapper userMapper, PlaylistTitreMapper playlistTitreMapper, TitreRepository titreRepository, TitreMapper titreMapper, UserRepository userRepository, PlaylistTitreIdMapper playlistTitreIdMapper, ArtisteMapper artisteMapper, ArtisteTitreRepository artisteTitreRepository) {
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
        this.artisteMapper = artisteMapper;
        this.artisteTitreRepository = artisteTitreRepository;
    }

    public List<PlaylistDto> findAll() {
        return playlistrepository.findAll().stream().map(playlistMapper::toDto).collect(Collectors.toList());
    }

    //pour ajouter une nouvelle playlist
    public PlaylistDto save(PlaylistDto playlistDto) {
        User user = userService.findUserByEmail(playlistDto.getUser().getEmail());
        //User user = userMapper.toEntity(userDto);
        Playlist entity = playlistMapper.toEntity(playlistDto);
        entity.setUser(user);
        return playlistMapper.toDto(playlistrepository.save(entity));
    }

    //pour modifier une playlist
    public PlaylistDto update(PlaylistDto playlistDto) {
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
    public List<ArtisteDtoList> listMusicOfPlaylist(int idPlaylist) {
        List<TitreDto> titres =  titreRepository.findTitreByIdPlaylist(idPlaylist).stream().map(titreMapper::toDto).collect(Collectors.toList());
        List<ArtisteDtoList> artisteDtoLists = new ArrayList<ArtisteDtoList>();
        titres.forEach((titre) -> {
            Titre entity = titreMapper.toEntity(titre);
            List<ArtisteTitre> artisteTitres = artisteTitreRepository.findByIdtitre(entity);
            List<ArtisteDto> artisteDtos = new ArrayList<>();
            artisteTitres.forEach((artisteTitre) -> {
                artisteDtos.add(artisteMapper.toDto(artisteTitre.getIdartiste()));
            });

            artisteDtoLists.add(new ArtisteDtoList(titre,artisteDtos));
        });
        return artisteDtoLists;
    }

    //pour la suppression d'une playlist
    public int deletePlaylistById(int id) {
        playlistTitreRepository.deletePlaylistById(id);
        playlistrepository.deletePlaylistById(id);
        return 1;
    }

    //pour la suppression d'une playlist
    public int deleteMusicOfPlaylistBy(int idTitre , int idPlaylist) {
        playlistrepository.deleteMusicOfPlaylistById(idTitre , idPlaylist);
        return 1;
    }

}
