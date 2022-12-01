package org.isj.ing4.isi.music.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.PlaylistTitreDto;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.service.PlaylistTitreService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/playlistTitre")
@RestController
@Slf4j
public class PlaylistTitreController {
    private final PlaylistTitreService playlistTitreService;

    public PlaylistTitreController(PlaylistTitreService  playlistTitreService) {
        this. playlistTitreService=playlistTitreService;
    }
    //pour ajouter une musique(titre) dans une playlist
    @PostMapping("/addmusique")
    public ResponseEntity<Void> addmusique(@RequestBody PlaylistTitreDto playlistTitreDto) throws IsjException {
        System.out.println(playlistTitreDto);
        playlistTitreService.save(playlistTitreDto);
        return ResponseEntity.ok().build();
    }
}
