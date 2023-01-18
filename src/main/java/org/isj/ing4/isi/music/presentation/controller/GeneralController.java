package org.isj.ing4.isi.music.presentation.controller;

import org.isj.ing4.isi.music.dto.ArtisteDtoList;
import org.isj.ing4.isi.music.dto.PlaylistDto;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.model.Historique;
import org.isj.ing4.isi.music.service.HistoriqueService;
import org.isj.ing4.isi.music.service.PlaylistService;
import org.isj.ing4.isi.music.service.TitreService;
import org.isj.ing4.isi.music.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GeneralController {

    @Autowired
    TitreService titreService;
    @Autowired
    PlaylistService playlistService;
    @Autowired
    HistoriqueService historiqueService;

    @GetMapping("/")
    public String pageAccueil(Model model) {

		/*if(user != null) {
			session.setAttribute("userName",  user.getName() + " " + user.getLastName());
		} else {
			session.setAttribute("userName",  "");
		}*/

        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getName() != "anonymousUser") {

           model.addAttribute("titres", titreService.getMusicHistoryUser(auth.getName()));

        } else {
            model.addAttribute("titres", titreService.getAllWithArtiste());
        }

        return "index";
    }

    @GetMapping("/songs")
    public String pageSong(Model model) {

		/*if(user != null) {
			session.setAttribute("userName",  user.getName() + " " + user.getLastName());
		} else {
			session.setAttribute("userName",  "");
		}*/
        Utils.updateModel(model);
        model.addAttribute("musics", titreService.findAll());
        model.addAttribute("titres", titreService.getAllWithArtiste());
        return "songs";
    }



    @GetMapping("/play")
    public String pagePlay(@RequestParam(value = "id", defaultValue = "") int id, Model model) throws IsjException {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       /// System.out.println(auth);
        if(auth.getName() != "anonymousUser") {
            List<PlaylistDto> playlistDtos = playlistService.playlistOfUser(auth.getName());
            model.addAttribute("playlists", playlistDtos);
            historiqueService.save(auth.getName(), id);
        }
		/*if(user != null) {
			session.setAttribute("userName",  user.getName() + " " + user.getLastName());
		} else {
			session.setAttribute("userName",  "");
		}*/
        Utils.updateModel(model);
        model.addAttribute("titre", titreService.findById(id));
        return "play";
    }

    @PostMapping("/searchsong")
    public String pageSearch(@RequestParam(value = "key", defaultValue = "") String key, Model model) throws IsjException {

		/*if(user != null) {
			session.setAttribute("userName",  user.getName() + " " + user.getLastName());
		} else {
			session.setAttribute("userName",  "");
		}*/
        List<ArtisteDtoList> artisteDtoLists = titreService.getSongByArtisteOrTitle(key);
        Utils.updateModelWithValue(model, key);
        model.addAttribute("titres", artisteDtoLists);
        return "search-songs";
    }




}
