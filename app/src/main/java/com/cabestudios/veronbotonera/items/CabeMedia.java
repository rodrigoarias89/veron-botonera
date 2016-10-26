package com.cabestudios.veronbotonera.items;

/**
 * Created by rarias on 10/20/16.
 */

public class CabeMedia {

    private String name;
    private String fileName;

    public CabeMedia(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }


    public interface CabeMediaListener {
        void play(int id);

        void play(String file);
    }


}
