package org.isj.ing4.isi.music.service;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.dto.ArtisteDto;
import org.isj.ing4.isi.music.exception.ErrorInfo;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.mapper.ArtisteMapper;
import org.isj.ing4.isi.music.model.Artiste;
import org.isj.ing4.isi.music.repository.ArtisteRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ArtisteService {
    private final ArtisteRepository repository;
    private final ArtisteMapper artisteMapper;

    public ArtisteService(ArtisteRepository repository, ArtisteMapper artisteMapper) {
        this.repository = repository;
        this.artisteMapper = artisteMapper;
    }

    public ArtisteDto save(ArtisteDto artisteDto) {
        Artiste entity = artisteMapper.toEntity(artisteDto);
        return artisteMapper.toDto(repository.save(entity));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public ArtisteDto findById(Integer id) throws IsjException {
        return artisteMapper.toDto(repository.findById(id).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)));
    }

    public Page<ArtisteDto> findByCondition(ArtisteDto artisteDto, Pageable pageable) {
        Page<Artiste> entityPage = repository.findAll(pageable);
        List<Artiste> entities = entityPage.getContent();
        return new PageImpl<>(artisteMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ArtisteDto update(ArtisteDto artisteDto, Integer id) throws IsjException {
        ArtisteDto data = findById(id);
        Artiste entity = artisteMapper.toEntity(artisteDto);
        BeanUtils.copyProperties(data, entity);
        return save(artisteMapper.toDto(entity));
    }
}