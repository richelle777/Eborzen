package org.isj.ing4.isi.music.service;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.dto.ArtisteDto;
import org.isj.ing4.isi.music.dto.ArtisteDtoList;
import org.isj.ing4.isi.music.dto.TitreDto;
import org.isj.ing4.isi.music.exception.ErrorInfo;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.mapper.ArtisteMapper;
import org.isj.ing4.isi.music.mapper.TitreMapper;
import org.isj.ing4.isi.music.model.ArtisteTitre;
import org.isj.ing4.isi.music.model.Titre;
import org.isj.ing4.isi.music.model.User;
import org.isj.ing4.isi.music.repository.ArtisteTitreRepository;
import org.isj.ing4.isi.music.repository.TitreRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class TitreService {
    private final TitreRepository repository;
    private final TitreMapper titreMapper;
    private final ArtisteMapper artisteMapper;
    private final ArtisteTitreRepository artisteTitreRepository;
    @Autowired
    UserService userService;

    public TitreService(TitreRepository repository, TitreMapper titreMapper, ArtisteMapper artisteMapper, ArtisteTitreRepository artisteTitreRepository) {
        this.repository = repository;
        this.titreMapper = titreMapper;
        this.artisteMapper = artisteMapper;
        this.artisteTitreRepository = artisteTitreRepository;
    }

    public TitreDto save(TitreDto titreDto) {
        Titre entity = titreMapper.toEntity(titreDto);
        return titreMapper.toDto(repository.save(entity));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public TitreDto findById(Integer id) throws IsjException {
        return titreMapper.toDto(repository.findById(id).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)));
    }

    public Page<TitreDto> findByCondition(TitreDto titreDto, Pageable pageable) {
        Page<Titre> entityPage = repository.findAll(pageable);
        List<Titre> entities = entityPage.getContent();
        return new PageImpl<>(titreMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public List<TitreDto> findAll() {
        return repository.findAll().stream().map(titreMapper::toDto).collect(Collectors.toList());
    }

    public TitreDto update(TitreDto titreDto, Integer id) throws IsjException {
        TitreDto data = findById(id);
        Titre entity = titreMapper.toEntity(titreDto);
        BeanUtils.copyProperties(data, entity);
        return save(titreMapper.toDto(entity));
    }

    public List<ArtisteDtoList> getAllWithArtiste() {
        List<TitreDto> titres = findAll();
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

    public List<ArtisteDtoList> getSongByArtisteOrTitle(String mot) {
        List<TitreDto> titres = repository.searchByArtisteOrSong(mot).stream().map(titreMapper::toDto).collect(Collectors.toList());
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


    public List<ArtisteDtoList> getMusicHistoryUser(String email) {
        User user = userService.findUserByEmail(email);
        List<TitreDto> titres = repository.findHistoriqueTitreByUser(user.getId()).stream().map(titreMapper::toDto).collect(Collectors.toList());;
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
}