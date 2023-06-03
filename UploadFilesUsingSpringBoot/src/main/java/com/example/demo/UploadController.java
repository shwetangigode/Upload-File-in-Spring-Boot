package com.example.demo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {
	private static final String UPLOAD_DIRECTORY="D://springbootworkspace///UploadFilesUsingSpringBoot//file//";
	//C://dev//upload//files//
	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "No File");
			return "redirect:upload_status";
		}
		try {
			final Path directory = Paths.get(UPLOAD_DIRECTORY);
			final Path filepath= Paths.get(UPLOAD_DIRECTORY+file.getOriginalFilename());
			if(!Files.exists(directory)) {
				Files.createDirectories(directory);
			}
			Files.write(filepath, file.getBytes());
			redirectAttributes.addFlashAttribute("message","Success!");
		}catch (Exception e) {
			redirectAttributes.addFlashAttribute("message","Error!");
		}
		return "redirect:upload_status";
	}
	
	@GetMapping("/upload_status")
	public String uploadStaus() {
		return "upload_status";
	}
}
