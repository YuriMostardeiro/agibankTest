package com.nt;

import com.nt.service.FileService;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchApplication {

	public static void main(String[] args) {
		FileService fileService = new FileService();
		fileService.watchFile();
	}
}
