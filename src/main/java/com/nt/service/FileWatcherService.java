package com.nt.service;

import java.io.IOException;
import java.nio.file.FileSystems;
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

    private WatchService watchService= FileSystems.getDefault().newWatchService();;

    @Autowired
    private FileService fileService;

    public FileWatcherService() throws IOException {
    }

    public void watchInputFolder() {
        try {
            this.LOGGER.info("Watcher Started");
            createDirectory();
            startWatcherService();
            executeWatchService();
            closeWatcherService();
        } catch (IOException | InterruptedException e) {
            this.LOGGER.error("Watcher error", e);
        }
    }

    private void createDirectory(){
        FileUtil.createDirectory();
    }

    private void startWatcherService() throws IOException {
        Paths.get(FileUtil.getFolderIn()).register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    }

    private void executeWatchService() throws InterruptedException {
        WatchKey key;
        while ((key = watchService.take()) != null) {
            fileService.validateAndProcessInputFile(FileUtil.getFolderIn() + "/" + path(key).toString());
            key.reset();
        }
    }

    private Object path(WatchKey key){
        return pollEvents(key).get().context();
    }

    private Optional<WatchEvent<?>> pollEvents(WatchKey key){
        return key.pollEvents().stream().findFirst();
    }

    private void closeWatcherService() throws IOException {
        watchService.close();
    }
}
