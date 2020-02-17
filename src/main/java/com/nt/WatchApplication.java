package com.nt;

import com.nt.service.FileWatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WatchApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WatchApplication.class, args);
		FileWatcherService fileWatcherService = context.getBean(FileWatcherService.class);
		fileWatcherService.watchFile();
	}
}
