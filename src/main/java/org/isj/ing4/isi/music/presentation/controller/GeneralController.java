package org.isj.ing4.isi.music.presentation.controller;

import org.isj.ing4.isi.music.dto.ArtisteDtoList;
import org.isj.ing4.isi.music.exception.IsjException;
import org.isj.ing4.isi.music.service.TitreService;
import org.isj.ing4.isi.music.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/")
    public String pageAccueil(Model model) {

		/*if(user != null) {
			session.setAttribute("userName",  user.getName() + " " + user.getLastName());
		} else {
			session.setAttribute("userName",  "");
		}*/
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

    @GetMapping("/playlist")
    public String pagePlaylist(Model model) {

		/*if(user != null) {
			session.setAttribute("userName",  user.getName() + " " + user.getLastName());
		} else {
			session.setAttribute("userName",  "");
		}*/
        Utils.updateModel(model);
        return "playlist";
    }

    @GetMapping("/play")
    public String pagePlay(@RequestParam(value = "id", defaultValue = "") int id, Model model) throws IsjException {

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

    @GetMapping("/mymusic")
    public String pagePlayListMusic(Model model) {
        return "songs-playlist";
    }


}
