package com.photo.organizer.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class PictureOrganizerService {

    public void organizePictures(String inputFolderPath, String outputFolderPath, String extension) throws IOException {
        // Create output folder if it doesn't exist
        Files.createDirectories(Paths.get(outputFolderPath));

        // Retrieve all picture files from input folder and subfolders
        Files.walkFileTree(Paths.get(inputFolderPath), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (isPictureFile(file, extension)) {
                    // Get picture modified date
                    Date modifiedDate = new Date(attrs.lastModifiedTime().toMillis());

                    // Format the date as yyyy for year
                    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                    String formattedYear = yearFormat.format(modifiedDate);

                    // Format the date as MMMM for full month name
                    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
                    String formattedMonth = monthFormat.format(modifiedDate);

                    // Create year folder in the output folder
                    String yearFolder = outputFolderPath + File.separator + formattedYear;
                    Files.createDirectories(Paths.get(yearFolder));

                    // Create month folder inside the year folder
                    String monthFolder = yearFolder + File.separator + formattedMonth;
                    Files.createDirectories(Paths.get(monthFolder));

                    // Move the picture file to the month folder
                    Files.move(file, Paths.get(monthFolder, file.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private boolean isPictureFile(Path file, String extension) {
        String fileName = file.getFileName().toString().toLowerCase();
        return fileName.endsWith(extension);
    }
}
