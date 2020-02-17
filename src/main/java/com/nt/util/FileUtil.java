package com.nt.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.nt.domain.OutputFile;
import com.nt.service.FileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private static final String folderIn = System.getProperty("user.home") + "\\data\\in\\";
    private static final String folderOut = System.getProperty("user.home") + "\\data\\out\\";


    public static void createDirectory() {
        createIfNotExists(folderIn);
        createIfNotExists(folderOut);
    }

    public static String getFolderIn() {
        return folderIn;
    }

    public static void createOutputFile(OutputFile outputFile) {
        try {
            FileWriter file = new FileWriter(new File(folderOut + "OutputFile.done"));
            file.write(outputFile.getOutputData());
            file.close();
        } catch (IOException e) {
            logger.error("Watcher error", e);
        }
    }

    private static void createIfNotExists(String folder) {
        File file = new File(folder);
        if (!file.exists())
            file.mkdir();
    }
}