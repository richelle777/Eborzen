package org.isj.ing4.isi.music.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.dto.ArtisteDto;
import org.isj.ing4.isi.music.exception.ErrorInfo;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.mapper.ArtisteMapper;
import org.isj.ing4.isi.music.model.Artiste;
import org.isj.ing4.isi.music.service.ArtisteService;
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

@RequestMapping("/api/artiste")
@RestController
@Slf4j

public class ArtisteController {
    private final ArtisteService artisteService;

    public ArtisteController(ArtisteService artisteService) {
        this.artisteService = artisteService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated ArtisteDto artisteDto) {
        artisteService.save(artisteDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtisteDto> findById(@PathVariable("id") Integer id) throws IsjException {
        ArtisteDto artiste = artisteService.findById(id);
        return ResponseEntity.ok(artiste);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws IsjException {
        Optional.ofNullable(artisteService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND);
        });
        artisteService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<ArtisteDto>> pageQuery(ArtisteDto artisteDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ArtisteDto> artistePage = artisteService.findByCondition(artisteDto, pageable);
        return ResponseEntity.ok(artistePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated ArtisteDto artisteDto, @PathVariable("id") Integer id) throws IsjException {
        artisteService.update(artisteDto, id);
        return ResponseEntity.ok().build();
    }
}