package com.fsoft.exercise.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fsoft.exercise.services.FileService;

@Controller
@RequestMapping("/")
public class DocManagementController {
	@Autowired
	private FileService fileService;
	private static final String PATH_FILE = "D:/files/";
	
	@RequestMapping(method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("files", fileService.getAll());
		return "index";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("files") MultipartFile[] files, ModelMap model) {
		try {
			fileService.addFile(files, PATH_FILE);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			model.addAttribute("error", "Upload process does not success");
			model.addAttribute("files", fileService.getAll());
			return "index";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteFile(@RequestParam("id") int[] fileIds) {
		fileService.deleteFile(fileIds);
		return "redirect:/";
	}
}
