package com.startup.enginizer.api.service;

import com.startup.enginizer.model.dto.ChatMessage;
import com.startup.enginizer.utils.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("api/service")
public class FileService {

	@RequestMapping(value = "/file", method= RequestMethod.POST)
	public ResponseEntity<ChatMessage> uploadFile(HttpServletRequest request ,
											      @RequestParam MultipartFile uploadedFile,
												  @RequestParam("topicId") int topicId) throws IOException {
		ChatMessage storedFile = FileUtils.saveFileToDiskForChat(uploadedFile, request, topicId);
		return new ResponseEntity<ChatMessage>(storedFile,HttpStatus.CREATED);
	}

	@RequestMapping(value = "/file", method= RequestMethod.GET)
	public void getFile(HttpServletRequest request ,
						HttpServletResponse response,
						@RequestParam("id") String fileId) throws IOException {

		response.setHeader("content-disposition", "attachment; filename=" + fileId);
		FileUtils.getFileAsStream(request,response, fileId);
	}


}
