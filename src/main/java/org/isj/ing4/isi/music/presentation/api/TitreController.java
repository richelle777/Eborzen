package org.isj.ing4.isi.music.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.dto.ArtisteDtoList;
import org.isj.ing4.isi.music.dto.ArtisteTitreDto;
import org.isj.ing4.isi.music.dto.TitreDto;
import org.isj.ing4.isi.music.exception.ErrorInfo;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.mapper.TitreMapper;
import org.isj.ing4.isi.music.model.Titre;
import org.isj.ing4.isi.music.service.TitreService;
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

@RequestMapping("/api/titre")
@RestController
@Slf4j
public class TitreController {
    private final TitreService titreService;

    public TitreController(TitreService titreService) {
        this.titreService = titreService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated TitreDto titreDto) {
        titreService.save(titreDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<TitreDto> findById(@PathVariable("id") Integer id) throws IsjException {
        TitreDto titre = titreService.findById(id);
        return ResponseEntity.ok(titre);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws IsjException {
        Optional.ofNullable(titreService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND);
        });
        titreService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page/page-query")
    public ResponseEntity<Page<TitreDto>> pageQuery(TitreDto titreDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<TitreDto> titrePage = titreService.findByCondition(titreDto, pageable);
        return ResponseEntity.ok(titrePage);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated TitreDto titreDto, @PathVariable("id") Integer id) throws IsjException {
        titreService.update(titreDto, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<TitreDto>> getAll() throws IsjException {
        List<TitreDto> titreDtos =  titreService.findAll();
        return ResponseEntity.ok(titreDtos);
    }

    @GetMapping("/artistTitre/all")
    public ResponseEntity<List<ArtisteDtoList>> getAllWithArtiste() throws IsjException {

        return ResponseEntity.ok(titreService.getAllWithArtiste());
    }
}