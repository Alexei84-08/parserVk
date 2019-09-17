package com.company;

public class RunParser implements Runnable {
    private ParserVk parserVk;
    private volatile boolean isActive;

    private String[] pathFile;
    private int idPage1;
    private int idPage2;
    private String search11;
    private String search22;

    RunParser(String[] pathFile, int idPage1, int idPage2, String search11, String search22) {
        this.isActive = true;
        this.pathFile = pathFile;
        this.idPage1 = idPage1;
        this.idPage2 = idPage2;
        this.search11 = search11;
        this.search22 = search22;
    }

    void disable() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void run() {
        do {
            String uri = "https://vk.com/id" + idPage1;
            System.out.println(idPage1);
            parserVk = new ParserVk(uri, search11, search22, pathFile[0] + "/ParseVk");
            parserVk.parsAndSave();
            idPage1++;
        }
        while (isActive && (idPage1 <= idPage2));
        parserVk.stop();
        disable();
    }
}
