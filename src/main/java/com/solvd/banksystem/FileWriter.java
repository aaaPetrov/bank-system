package com.solvd.banksystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class FileWriter implements AutoCloseable {

    private static final Logger LOGGER = LogManager.getLogger(FileWriter.class);

    private final File file;
    private FileOutputStream fileOutputStream;

    public FileWriter() {
        this.file = new File("./src/main/java/com/solvd/banksystem/", "1.txt");
        try {
            this.file.createNewFile();
            this.fileOutputStream = new FileOutputStream(file, true);
            LOGGER.info("File opened.");
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public void write(String writeInFile) {
        try {
            this.fileOutputStream.write(writeInFile.getBytes());
            LOGGER.info("Written in file.");
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }

    }

    @Override
    public void close() {
        try {
            this.fileOutputStream.close();
            LOGGER.info("File closed.");
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

}
