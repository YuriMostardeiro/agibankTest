package com.nt;

import com.nt.service.WatcherService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchApplication {

	public static void main(String[] args) {
		WatcherService watcherService = new WatcherService();
		watcherService.watchFile();
	}
}
