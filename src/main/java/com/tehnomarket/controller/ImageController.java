package com.tehnomarket.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@MultipartConfig
public class ImageController {

	private static final String FILE_PATH = "D:\\TEHNOMARKET_IMAGES\\";

	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String showUploadForm() {
		return "upload";
	}

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String saveImage(Model m, @RequestParam("failche") MultipartFile uploadedFile) throws IOException {
//		String extension = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		String fileName = "krasi-"+uploadedFile.getOriginalFilename();
		File serverFile = new File(FILE_PATH + fileName);
		Files.copy(uploadedFile.getInputStream(), serverFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		m.addAttribute("filename", fileName);
		return "upload";
	}

	@RequestMapping(value="/download/{filename:.+}", method=RequestMethod.GET)
	public void downloadFile(HttpServletResponse resp, @PathVariable("filename") String fileName) throws IOException {
		System.out.println(fileName);
		File serverFile = new File(FILE_PATH + fileName);
		Files.copy(serverFile.toPath(), resp.getOutputStream());
	}
	
	
	
}
