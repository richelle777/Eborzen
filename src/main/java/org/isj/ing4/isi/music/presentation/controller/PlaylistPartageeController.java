package org.isj.ing4.isi.music.presentation.controller;

import org.isj.ing4.isi.music.dto.PlaylistDto;
import org.isj.ing4.isi.music.service.PlaylistService;
import org.isj.ing4.isi.music.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PlaylistPartageeController {

    @Autowired
    PlaylistService playlistService;

    @GetMapping("/playlistPartag")
    public String pagePlaylistPartag(Model model) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //User user = userService.findUserByEmail(auth.getName());
//        List<PlaylistDto> playlistDtos = playlistService.playlistOfUser(auth.getName());
		/*if(user != null) {
			session.setAttribute("userName",  user.getName() + " " + user.getLastName());
		} else {
			session.setAttribute("userName",  "");
		}*/
//        model.addAttribute("playlistDto", new PlaylistDto());
//        model.addAttribute("playlists", playlistDtos);
//        model.addAttribute("size", playlistDtos.size());

        Utils.updateModel(model);
        return "playlistpartagee";
    }

    @GetMapping("/allshare")
    public String allShare(Model model) {
        List<PlaylistDto> playlists = playlistService.findAll();
        playlists.removeIf(playlistDto -> playlistDto.getEtatPartage() == 0);
        model.addAttribute("playlist", playlists);
        return "playlistpartage";
    }

    @GetMapping("/share/{id}")
    public String share(@PathVariable("idp") String idp) {
        PlaylistDto playlistDto = playlistService.findById(Integer.parseInt(idp));
        playlistDto.setEtatPartage(1);
        playlistService.update(playlistDto);
        return "redirect:/playlist";
    }
}
