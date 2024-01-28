package com.photo.organizer.controller;

import com.photo.organizer.model.PictureOrganizerProperties;
import com.photo.organizer.service.PictureOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class PictureOrganizerController {

	private final PictureOrganizerProperties properties;
	private final PictureOrganizerService pictureOrganizerService;

	@Autowired
	public PictureOrganizerController(PictureOrganizerProperties properties, PictureOrganizerService pictureOrganizerService) {
		this.properties = properties;
		this.pictureOrganizerService = pictureOrganizerService;
	}

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("inputFolderPath", properties.getInputFolderPath());
		model.addAttribute("outputFolderPath", properties.getOutputFolderPath());
		return "index";
	}

	@PostMapping("/organize")
	public String organizePictures(@RequestParam("inputFolder") String inputFolder,
								   @RequestParam("outputFolder") String outputFolder,
								   @RequestParam("extension") String extension) throws IOException {
		properties.setInputFolderPath(inputFolder);
		properties.setOutputFolderPath(outputFolder);
		pictureOrganizerService.organizePictures(inputFolder, outputFolder, extension);
		return "redirect:/";
	}
}