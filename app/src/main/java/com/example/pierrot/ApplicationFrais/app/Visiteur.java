package com.example.pierrot.ApplicationFrais.app;

/**
 * Created by Utilisateur on 09/05/2016.
 */
public class Visiteur {
    private static Visiteur ourInstance = new Visiteur();

    public static Visiteur getInstance() {
        return ourInstance;
    }

    private Visiteur() {
    }
}
