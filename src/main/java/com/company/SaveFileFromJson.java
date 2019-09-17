package com.company;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

class SaveFileFromJson {
    private VkParserModel vkParserModel;
    private Gson gson;
    private FileWriter fileWriter;

    SaveFileFromJson(String pathSaveFile, VkParserModel vkParserModel) {
        try {
            fileWriter = new FileWriter(pathSaveFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.vkParserModel = vkParserModel;
        this.gson = new Gson();
    }

    void saveInFile() {
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
