package org.isj.ing4.isi.music.service;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.dto.PlaylistDto;
import org.isj.ing4.isi.music.exception.ErrorInfo;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.mapper.PlaylistMapper;
import org.isj.ing4.isi.music.mapper.PlaylistTitreMapper;
import org.isj.ing4.isi.music.mapper.UserMapper;
import org.isj.ing4.isi.music.model.Playlist;
import org.isj.ing4.isi.music.model.Titre;
import org.isj.ing4.isi.music.repository.PlaylistRepository;
import org.isj.ing4.isi.music.repository.TitreRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class PlaylistService {
    private final PlaylistRepository repository;
    private final PlaylistMapper playlistMapper;
    private final UserMapper userMapper;
    private final PlaylistTitreMapper playlistTitreMapper;
    private final TitreRepository titreRepository;


    public PlaylistDto findById(Integer id) throws IsjException {
        return playlistMapper.toDto(repository.findById(id).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)));
    }

    public PlaylistService(PlaylistRepository repository, PlaylistMapper playlistMapper, UserMapper userMapper, PlaylistTitreMapper playlistTitreMapper, TitreRepository titreRepository) {
        this.repository = repository;
        this.playlistMapper = playlistMapper;
        this.userMapper = userMapper;
        this.playlistTitreMapper = playlistTitreMapper;
        this.titreRepository = titreRepository;
    }
    public List<PlaylistDto> findAll() {
        return repository.findAll().stream().map(playlistMapper::toDto).collect(Collectors.toList());
    }
    public PlaylistDto save(PlaylistDto playlistDto) {
        Playlist entity = playlistMapper.toEntity(playlistDto);
        return playlistMapper.toDto(repository.save(entity));
    }
    public PlaylistDto update(PlaylistDto playlistDto) throws IsjException {
        Playlist data = repository.findById(playlistDto.getId()).get();
        playlistMapper.copy(playlistDto , data);
        return  playlistMapper.toDto(repository.save(data));
    }
    public List<PlaylistDto> playlistOfUser(String email){
        return repository.findArticleByUser(email).stream().map(playlistMapper::toDto).collect(Collectors.toList());
    }
}
