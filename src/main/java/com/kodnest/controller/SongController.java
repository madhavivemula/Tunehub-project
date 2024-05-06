package com.kodnest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kodnest.entity.Song;
import com.kodnest.service.SongService;



@Controller
public class SongController {
	@Autowired
	SongService songService;
	@PostMapping("/addsongs")

	public String addSong(@ModelAttribute Song song) {
		//		 songService.saveSong(song);
		//		 return "song";
		String name = song.getName();
		boolean songExists = songService.songExists(name);
		if(songExists==false) {
			songService.saveSong(song);
			System.out.println("Song added successfully");
		}
		else {
			System.out.println("Duplicate Song");
		}
		return "adminhome";
	}
	@GetMapping("/playsongs")
	public String playSongs(Model model) {
		boolean preminum = true;
		if(preminum) {
		List<Song> songslist = songService.fetchAllSongs();
		model.addAttribute("songs", songslist);
		System.out.println(songslist);
		return "viewsongs";
	} else {
		return "pay";
	}
}
	@GetMapping("/viewsongs")
	public String viewSongs(Model model) {
		
		List<Song> songslist = songService.fetchAllSongs();
		model.addAttribute("songs", songslist);
		System.out.println(songslist);
		return "viewsongs";
	
}
}


















