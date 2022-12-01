package org.isj.ing4.isi.music.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.dto.PlaylistDto;
import org.isj.ing4.isi.music.dto.TitreDto;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.service.PlaylistService;
import org.isj.ing4.isi.music.service.TitreService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/playlist")
@RestController
@Slf4j
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService= playlistService;
    }

    //  pour lister les playlists
    @GetMapping("/all")
    public ResponseEntity<List<PlaylistDto>> getAll() throws IsjException {
        List<PlaylistDto> playlistDtos = playlistService.findAll();
        return ResponseEntity.ok(playlistDtos);
    }

    //  pour ajouter une playlist
    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody @Validated PlaylistDto playlistDto) {
        playlistService.save(playlistDto);
        return ResponseEntity.ok().build();
    }

    //   pour modifier une playlist
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Validated PlaylistDto playlistDto) throws IsjException {
        playlistService.update(playlistDto);
        return ResponseEntity.ok().build();
    }

    //   pour afficher les  musiques(titres) d'une palylist



}