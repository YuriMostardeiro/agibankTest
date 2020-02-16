package com.nt.util;

import com.nt.domain.Customer;
import com.nt.domain.OutputFile;
import com.nt.domain.Sale;
import com.nt.domain.Salesman;
import com.nt.service.CustomerService;
import com.nt.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtil {

    private static final String folderIn = System.getProperty("user.home") + "\\data\\in\\";
    private static final String folderOut = System.getProperty("user.home") + "\\data\\out\\";
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private static FileWriter file;

    public static void createDirectory(String folder) {
        File file = new File(folder);
        if (!file.exists())
            file.mkdir();
    }

    public static String getFolderIn() {
            return folderIn;
    }

    public static String getFolderOut() {
        return folderOut;
    }

    public static boolean createOutputFile(OutputFile outputFile) {

        FileUtil.createDirectory(folderOut);
        try {
            file = new FileWriter(new File(folderOut + "OutputFile.done.txt"));
            file.write(outputFile.getOutputData());
            file.close();
            return true;
        } catch (IOException e) {
            logger.error("Watcher error", e);
            return false;
        }
    }
}
