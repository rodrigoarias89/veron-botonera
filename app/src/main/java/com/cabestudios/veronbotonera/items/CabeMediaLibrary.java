package com.cabestudios.veronbotonera.items;

/**
 * Created by rarias on 10/20/16.
 */

public class CabeMediaLibrary {

    private CabeMedia[] cabeMedias = {
            new CabeMedia("Hijo de remil puta", "audio/chapuhdrmp.ogg"),
            new CabeMedia("Campeones, muchachos!", "audio/campeones.ogg"),
            new CabeMedia("Tengo malas noticias", "audio/gente.mp4"),
            new CabeMedia("Chupame un huevo", "audio/chupame.ogg"),
            new CabeMedia("Stranger", "audio/example.mp3"),
            new CabeMedia("Remix", "audio/remix.aac")};


    public CabeMedia[] getCabeMedias() {
        return cabeMedias;
    }

    public int getSize() {
        return cabeMedias.length;
    }

    public CabeMedia getItem(int index) {
        return cabeMedias[index];
    }
}
