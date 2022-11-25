package org.isj.ing4.isi.music.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.dto.AlbumDto;
import org.isj.ing4.isi.music.exception.ErrorInfo;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.mapper.AlbumMapper;
import org.isj.ing4.isi.music.model.Album;
import org.isj.ing4.isi.music.service.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/album")
@RestController
@Slf4j
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated AlbumDto albumDto) {
        albumService.save(albumDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> findById(@PathVariable("id") Integer id) throws IsjException {
        AlbumDto album = albumService.findById(id);
        return ResponseEntity.ok(album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws IsjException {
        Optional.ofNullable(albumService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND);
        });
        albumService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<AlbumDto>> pageQuery(AlbumDto albumDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<AlbumDto> albumPage = albumService.findByCondition(albumDto, pageable);
        return ResponseEntity.ok(albumPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated AlbumDto albumDto, @PathVariable("id") Integer id) throws IsjException {
        albumService.update(albumDto, id);
        return ResponseEntity.ok().build();
    }
}