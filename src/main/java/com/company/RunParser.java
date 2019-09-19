package com.company;

public class RunParser implements Runnable {
    private ParserVk parserVk;
    private volatile boolean isActive;

    private int idPage1;
    private int idPage2;
    private String search1;
    private String search2;

    RunParser(String pathFile, int idPage1, int idPage2, String search1, String search2) {
        this.isActive = true;
        this.idPage1 = idPage1;
        this.idPage2 = idPage2;
        this.search1 = search1;
        this.search2 = search2;
        this.parserVk = new ParserVk(pathFile + "/ParseVk");
    }

    void disable() {
        isActive = false;
    }

    @Override
    public void run() {
        do {
            System.out.println(idPage1);
            parserVk.parsAndSave(idPage1, search1, search2);
            idPage1++;
        }
        while (isActive && (idPage1 <= idPage2));
        parserVk.stop();
        disable();
    }
}
