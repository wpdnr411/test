package com.example.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		
		System.out.println("upload form");
		
	}

	@PostMapping("uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {		// 파일 처리는 스프링에서 제공하는 MultipartFile이라는 타입을 이용한다.
																				// 첨부파일을 여러 개 선택할 수 있으므로 배열 타입으로 설정한다.
		String uploadFolder = "C:/upload";										// 업로드되는 파일 경로
		
		for(MultipartFile multipartFile : uploadFile) {	
			
			System.out.println("---------------------------------------------");
			System.out.println("Upload File Name : " + multipartFile.getOriginalFilename());		// 업로드되는 파일의 이름
			System.out.println("Upload File Size : " + multipartFile.getSize());					// 업로드되는 파일의 크기

			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());			// 파일의 경로와 파일의 이름을 saveFile 변수에 저장
			
			try {
				multipartFile.transferTo(saveFile);		// transferTo()의 파라미터로는 java.io.File의 객체를 지정하면 되기 때문에 업로드되는 원래 파일의 이름으로 C 드라이브 upload 폴더에 원래 이름으로 저장된다.
				} catch (Exception e) {
			}	// end catch
		}	// end for
	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		System.out.println("upload ajax");
	}
}
