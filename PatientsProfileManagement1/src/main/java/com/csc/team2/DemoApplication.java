package com.csc.team2;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.csc.team2.controller.UploadingController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		new File(UploadingController.uploadingdir).mkdirs();
		SpringApplication.run(DemoApplication.class, args);
	}
}
