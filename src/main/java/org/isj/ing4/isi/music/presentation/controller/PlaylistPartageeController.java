package org.isj.ing4.isi.music.presentation.controller;

import org.isj.ing4.isi.music.dto.PlaylistDto;
import org.isj.ing4.isi.music.utils.Utils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PlaylistPartageeController {

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
}
