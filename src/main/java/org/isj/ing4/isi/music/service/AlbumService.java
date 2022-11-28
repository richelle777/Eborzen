package org.isj.ing4.isi.music.service;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.dto.AlbumDto;
import org.isj.ing4.isi.music.exception.ErrorInfo;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.mapper.AlbumMapper;
import org.isj.ing4.isi.music.model.Album;
import org.isj.ing4.isi.music.repository.AlbumRepository;
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
public class AlbumService {
    private final AlbumRepository repository;
    private final AlbumMapper albumMapper;

    public AlbumService(AlbumRepository repository, AlbumMapper albumMapper) {
        this.repository = repository;
        this.albumMapper = albumMapper;
    }

    public AlbumDto save(AlbumDto albumDto) {
        Album entity = albumMapper.toEntity(albumDto);
        return albumMapper.toDto(repository.save(entity));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public AlbumDto findById(Integer id) throws IsjException {
        return albumMapper.toDto(repository.findById(id).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)));
    }

    public Page<AlbumDto> findByCondition(AlbumDto albumDto, Pageable pageable) {
        Page<Album> entityPage = repository.findAll(pageable);
        List<Album> entities = entityPage.getContent();
        return new PageImpl<>(albumMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public AlbumDto update(AlbumDto albumDto, Integer id) throws IsjException {
        AlbumDto data = findById(id);
        Album entity = albumMapper.toEntity(albumDto);
        BeanUtils.copyProperties(data, entity);
        return save(albumMapper.toDto(entity));
    }
}