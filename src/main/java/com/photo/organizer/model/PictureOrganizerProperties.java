package com.photo.organizer.model;

import org.springframework.stereotype.Component;

@Component
public class PictureOrganizerProperties {
    private String inputFolderPath;
    private String outputFolderPath;

    public String getInputFolderPath() {
        return inputFolderPath;
    }

    public void setInputFolderPath(String inputFolderPath) {
        this.inputFolderPath = inputFolderPath;
    }

    public String getOutputFolderPath() {
        return outputFolderPath;
    }

    public void setOutputFolderPath(String outputFolderPath) {
        this.outputFolderPath = outputFolderPath;
    }
}