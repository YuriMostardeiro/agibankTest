package com.nt.service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Optional;

import com.nt.util.FileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileWatcherService {

    private final Logger LOGGER = LoggerFactory.getLogger(FileWatcherService.class);

    private WatchService watchService;

    @Autowired
    private FileService fileService;

    public void watchInputFolder() {
        try {
            this.LOGGER.info("Watcher Started");
            FileUtil.createDirectory();
            startWatchService();
            executeWatchService();
            closeWatchService();
        } catch (IOException | InterruptedException e) {
            this.LOGGER.error("Watcher error", e);
        }
    }

    private void startWatchService() throws IOException {
        watchService = FileSystems.getDefault().newWatchService();
        Path directory = Paths.get(FileUtil.getFolderin());
        directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    }

    private void executeWatchService() throws InterruptedException, IOException {
        while (true) {
            WatchKey key = watchService.take();
            Optional<WatchEvent<?>> watchEvent = key.pollEvents().stream().findFirst();
            Path path = (Path) watchEvent.get().context();
            fileService.validateAndProcessInputFile(FileUtil.getFolderin() + "/" + path.toString());
            key.reset();
        }
    }

    private void closeWatchService() throws IOException {
        watchService.close();
    }
}
