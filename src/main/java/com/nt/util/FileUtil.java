package com.nt.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.nt.domain.OutputFile;
import com.nt.service.FileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);
    private static final String FOLDERIN = System.getProperty("user.home") + "\\data\\in\\";
    private static final String FOLDEROUT = System.getProperty("user.home") + "\\data\\out\\";


    public static void createDirectory() {
        createIfNotExists(FOLDERIN);
        createIfNotExists(FOLDEROUT);
    }

    public static String getFolderin() {
        return FOLDERIN;
    }

    public static void createOutputFile(OutputFile outputFile) {
        try {
            FileWriter file = new FileWriter(new File(FOLDEROUT + "OutputFile.done"));
            file.write(outputFile.getOutputData());
            file.close();
        } catch (IOException e) {
            LOGGER.error("Watcher error", e);
        }
    }

    private static void createIfNotExists(String folder) {
        File file = new File(folder);
        if (!file.exists())
            file.mkdir();
    }
}