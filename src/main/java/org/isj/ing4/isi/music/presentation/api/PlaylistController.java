package org.isj.ing4.isi.music.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing4.isi.music.dto.PlaylistDto;
import org.isj.ing4.isi.music.dto.TitreDto;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.service.PlaylistService;
import org.isj.ing4.isi.music.service.PlaylistTitreService;
import org.isj.ing4.isi.music.service.TitreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/playlist")
@RestController
@Slf4j
public class PlaylistController {
    private final PlaylistService playlistService;

    private PlaylistTitreService playlistTitreService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService= playlistService;
    }

    //  pour lister les playlists d'un user en particulier
    @GetMapping("/all/{email}")
    public ResponseEntity<List<PlaylistDto>> getAllOfUser(@PathVariable String email) throws IsjException {
        List<PlaylistDto> playlistDtos = playlistService.playlistOfUser(email);
        return ResponseEntity.ok(playlistDtos);
    }

    //  pour lister les playlists
//    @GetMapping("/all")
//    public ResponseEntity<List<PlaylistDto>> getAll() throws IsjException {
//        List<PlaylistDto> playlistDtos = playlistService.findAll();
//        return ResponseEntity.ok(playlistDtos);
//    }

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

    //pour ajouter une musique dans une playlist
    @PostMapping("/saveToPlaylist/{idM}/{idP}")
    public ResponseEntity<Void> save(@PathVariable int idM , @PathVariable int idP) {
        playlistService.addMusicToPlaylist(idM , idP);
        return ResponseEntity.ok().build();
    }

    //   pour afficher les  musiques(titres) d'une palylist

    @GetMapping("/allMusicOfPlaylist/{idP}")
    public ResponseEntity<List<TitreDto>> getAllMusicOfPlaylist(@PathVariable int idP) throws IsjException {
        List<TitreDto> titreDtos = playlistService.listMusicOfPlaylist(idP);
        return ResponseEntity.ok(titreDtos);
    }

    @GetMapping("/deletePlaylist/{idP}")
    public int deletePlaylist(@PathVariable int idP) throws IsjException {
        return playlistService.deletePlaylistById(idP);
    }

}
