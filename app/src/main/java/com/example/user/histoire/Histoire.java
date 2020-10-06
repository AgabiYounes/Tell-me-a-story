package com.example.user.histoire;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by AmineYahouni on 16/05/2018.
 */

public class Histoire extends Activity{
    private int Id;
    private String Titre;
    private String Auteur;
    private String NbrPages;
    private int Photo;
    private String History;
    private int Background;
    //constructeur
    public Histoire(String titre, String auteur, String nbrPages, int photo, String history, int background) {
        Titre = titre;
        Auteur = auteur;
        NbrPages = nbrPages;
        Photo = photo;
        History = history;
        Background=background;
    }

    //constructeur vide
    public Histoire() {
    }
    //Getters and Setters

    public int getBackground() {
        return Background;
    }

    public void setBackground(int background) {
        Background = background;
    }

    public int getId() {
        return Id;
    }
    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getAuteur() {
        return Auteur;
    }

    public void setAuteur(String auteur) {
        Auteur = auteur;
    }

    public String getNbrPages() {
        return NbrPages;
    }

    public void setNbrPages(String nbrPages) {
        NbrPages = nbrPages;
    }

    public int getPhoto() {return Photo;

    }

    public void setPhoto(int photo) {
        Photo = photo;
    }

    public String getHistory() {
        return History;
    }

    public void setHistory(String history) {
        History = history;
    }
}
