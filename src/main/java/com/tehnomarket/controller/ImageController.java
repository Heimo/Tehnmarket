package com.tehnomarket.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tehnomarket.model.dao.ProductDao;

@Controller
@MultipartConfig
public class ImageController {

	@Autowired
	private ProductDao productDao;
	
	private static final String FILE_PATH = "D:\\TEHNOMARKET_IMAGES\\";

	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String showUploadForm() {
		return "upload";
	}
	
	// NOT FIXED FOR OUR SITE, copy/paste from krasi !!!
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public ModelAndView saveImage(Model m, @RequestParam("file") MultipartFile uploadedFile,@RequestParam("id") int id,HttpServletRequest request) throws IOException {
		String fileName = id + "-mainImage."+ FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		System.out.println(uploadedFile.getOriginalFilename());
		File serverFile = new File(FILE_PATH + fileName);
		Files.copy(uploadedFile.getInputStream(), serverFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		request.setAttribute("id",id);
		try {
			productDao.addPictureToProductById(fileName,id);
		} catch (SQLException e) {
			return new ModelAndView("error");
		}
		return new ModelAndView("redirect:/editProduct?id="+id);
	}

	@RequestMapping(value="/download/{filename:.+}", method=RequestMethod.GET)
	public void downloadFile(HttpServletResponse resp, @PathVariable("filename") String fileName) throws IOException {
		String completeFileName = fileName+".jpg";
		System.out.println(fileName);
		File serverFile = new File(FILE_PATH + completeFileName);
		Files.copy(serverFile.toPath(), resp.getOutputStream());
	}
	
	
	
}
