package org.isj.ing4.isi.music.presentation.controller;

import org.isj.ing4.isi.music.dto.ArtisteDtoList;
import org.isj.ing4.isi.music.dto.PlaylistDto;
import org.isj.ing4.isi.music.dto.TitreDto;
import org.isj.ing4.isi.music.dto.UserDto;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.model.User;
import org.isj.ing4.isi.music.service.PlaylistService;
import org.isj.ing4.isi.music.service.UserService;
import org.isj.ing4.isi.music.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayListController {

    @Autowired
    PlaylistService playlistService;
    @Autowired
    UserService userService;

    @GetMapping("/playlist")
    public String pagePlaylist(Model model) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = userService.findUserByEmail(auth.getName());
        List<PlaylistDto> playlistDtos = playlistService.playlistOfUser(auth.getName());
		/*if(user != null) {
			session.setAttribute("userName",  user.getName() + " " + user.getLastName());
		} else {
			session.setAttribute("userName",  "");
		}*/
        model.addAttribute("playlistDto", new PlaylistDto());
        model.addAttribute("playlists", playlistDtos);

        Utils.updateModel(model);
        return "playlist";
    }

    @PostMapping("/addPlaylist")
    public String addPlayList(@ModelAttribute PlaylistDto playlistDto, Model model) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = userService.findUserByEmailDto(auth.getName());
        playlistDto.setUser(user);
        playlistService.save(playlistDto);
        return "redirect:/playlist";
    }

    @GetMapping("/addmusic/{idp}/{idm}")
    public String addMusic(@PathVariable(value = "idp") String idp, @PathVariable(value = "idm") String idm, Model model) {
        int idplay = Integer.parseInt(idp);
        int idmusic = Integer.parseInt(idm);
        playlistService.addMusicToPlaylist(idplay, idmusic);
        return "redirect:/play?id="+idmusic;
    }

    @GetMapping("/mymusic")
    public String pagePlayListMusic(@RequestParam(value = "idp") String idp, Model model) {
        PlaylistDto playlistDto = playlistService.findById(Integer.parseInt(idp));
        model.addAttribute("playlist", playlistDto);
        List<ArtisteDtoList> titreDtos = playlistService.listMusicOfPlaylist(playlistDto.getId());
        model.addAttribute("id", playlistDto.getId());
        model.addAttribute("titres", titreDtos);
        return "songs-playlist";
    }

    @PostMapping("/updatePlaylist")
    public String updatePlaylist(@ModelAttribute PlaylistDto playlistDto, Model model) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = userService.findUserByEmailDto(auth.getName());

        playlistDto.setUser(user);
        playlistService.update(playlistDto);
        return "redirect:/mymusic?idp="+playlistDto.getId();
    }

    @GetMapping("/delete/{idp}/{idm}")
    public String deleteMusic(@PathVariable("idp") String idp, @PathVariable("idm") String idm) {
        int idplay = Integer.parseInt(idp);
        int idmusic = Integer.parseInt(idm);
        playlistService.deleteMusicOfPlaylistBy(idmusic, idplay);
        return "redirect:/mymusic?id="+idmusic;
    }

    @GetMapping("/delete-playlist/{idp}")
    public String deletePlaylist(@PathVariable("idp") String idp) {
        int idplay = Integer.parseInt(idp);
        playlistService.deletePlaylistById(idplay);
        return "redirect:/playlist";
    }
}
