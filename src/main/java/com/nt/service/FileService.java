package com.nt.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.nt.domain.DataInput;
import com.nt.util.FileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	private final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

	@Autowired
	private ProcessService processService;

	public void validateAndProcessInputFile(String file) {
		if (validateInputFile(file)) {
			processFile(file);
		}
	}

	private boolean validateInputFile(String file) {
		return file.endsWith(".dat");
	}

	private void processFile(String file) {
		DataInput dataInput = new DataInput();
		readFile(file, dataInput);
		FileUtil.createOutputFile(processService.getOutputData(dataInput));
	}

	private void readFile(String filePath, DataInput dataInput) {
		FileInputStream inputStream = null;
		Scanner sc = null;

		try {
			inputStream = new FileInputStream(filePath);
			sc = new Scanner(inputStream, "UTF-8");

			while (sc.hasNextLine()) {
				processService.processRow(sc.nextLine(), dataInput);
			}

			if (sc .ioException() != null)
				throw sc.ioException();

		} catch (IOException e) {
			this.LOGGER.error("File error", e);
		} finally {
			this.closeStream(inputStream, sc);
		}
	}

	private void closeStream(FileInputStream inputStream, Scanner scanner) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				this.LOGGER.error("Error", e);
			}
		}
		if (scanner != null)
			scanner.close();
	}
}