package com.nt.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.nt.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatcherService {

    private final Logger logger = LoggerFactory.getLogger(FileService.class);

    private final FileService fileService = new FileService();

    @Autowired
    private WatchService watcher;

    public void watchFile() {
        try {
            this.logger.info("Watcher Started");

            FileUtil.createDirectory(FileUtil.getFolderIn());
            watcher = FileSystems.getDefault().newWatchService();

            Path directory = Paths.get(FileUtil.getFolderIn());
            directory.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

            while (true) {
                WatchKey key = watcher.take();
                Optional<WatchEvent<?>> watchEvent = key.pollEvents().stream().findFirst();
                Path path = (Path) watchEvent.get().context();
                if (path.toString().endsWith(".txt")) {
                    this.logger.info("ReadFile Started");

                    List<String> listFileRow = new ArrayList<>();
                    BufferedReader buffered = new BufferedReader(new FileReader(FileUtil.getFolderIn() + "\\" + path.toString()));
                    String row = "";

                    while ((row = buffered.readLine()) != null) {
                        listFileRow.add(row);
                    }
                    buffered.close();

                    fileService.createOutputFile(listFileRow);
                    this.logger.info("OutputFile Updated");
                }
                boolean valid = key.reset();
                if (!valid) {
                    this.logger.info("Watcher Stoped");
                    break;
                }
            }
            watcher.close();
        } catch (IOException | InterruptedException e) {
            this.logger.error("Watcher error", e);
            e.printStackTrace();
        }
    }
}
