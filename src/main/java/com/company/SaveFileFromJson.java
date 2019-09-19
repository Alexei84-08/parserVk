package com.company;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

class SaveFileFromJson {
    private VkParserModel vkParserModel;
    private Gson gson;
    private FileWriter fileWriter;

    SaveFileFromJson(String pathSaveFile) {
        try {
            fileWriter = new FileWriter(pathSaveFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.gson = new Gson();
    }

    void saveInFile(VkParserModel vkParserModel) {
        try {
            fileWriter.write(gson.toJson(vkParserModel));
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void stopWrite(){
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
