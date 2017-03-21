package com.csc.team2.controller;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.csc.team2.model.File;
import com.csc.team2.model.Medicine;
import com.csc.team2.service.FileServiceImpl;

@RestController
public class FileController {
	
	@Autowired
	FileServiceImpl fileService;
	
	public static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> createMedicine(@RequestBody File file,@RequestParam("file") MultipartFile image,UriComponentsBuilder ucBuilder){
		
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/Medicine/{id}").buildAndExpand(file.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
//	
//	private byte[] getImageByteArray(final InputStream inputStream) {
//	     byte images[] = null;
//	   try{
//	       final BufferedImage image = ImageIO.read(inputStream);
//	            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	       ImageIO.write(image, "jpeg", baos);
//	       images = baos.toByteArray();
//	      }catch (final Exception e) {
//	        e.printStackTrace();
//	    }
//	   return images;
//	 }
}
