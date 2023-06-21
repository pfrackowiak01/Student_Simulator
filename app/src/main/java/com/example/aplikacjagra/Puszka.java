package com.example.aplikacjagra;

import android.media.Image;
import android.widget.TextView;

public class Puszka {

    int skin;          // skin
    int skin2;         // skin po kliknieciu
    String nazwa = "";
    int koszt = 0;
    boolean posiadane = false;

    Puszka(int skin, int skin2, String nazwa, int koszt, Boolean posiadane) {
        this.skin = skin;
        this.skin2 = skin2;
        this.nazwa = nazwa;
        this.koszt = koszt;
        this.posiadane = posiadane;
    }

    @Override
    public String toString() {

        return nazwa;
    }

    public int getSkinImageDrawable() {
        return skin;
    }

    public void setSkinImageDrawable(int pImageDrawable) {
        this.skin = pImageDrawable;
    }

    public int getSkin2ImageDrawable() {
        return skin2;
    }

    public void setSkin2ImageDrawable(int pImageDrawable) {
        this.skin2 = pImageDrawable;
    }

    public String getName() {
        return nazwa;
    }

    public void setName(String name) {
        this.nazwa = name;
    }

    public int getKoszt() {
        return koszt;
    }

    public void setKoszt(int koszt) {
        this.koszt = koszt;
    }

    public boolean getPosiadane() {
        return posiadane;
    }

    public void setPosiadane(boolean posiadane) {
        this.posiadane = posiadane;
    }
}
