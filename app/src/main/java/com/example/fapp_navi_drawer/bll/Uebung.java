package com.example.fapp_navi_drawer.bll;

import java.io.Serializable;

/**
 * Created by pupil on 4/2/19.
 */

public class Uebung implements Serializable {

    private int id;
    private String beschreibung;
    private String bezeichnung;
    private int sets;
    private int wdh;
    private int gwt;
    private MUSKELGRUPPE muskelgruppe;


    public Uebung(int id, String bezeichnung, String beschreibung, int s, int w, int g, MUSKELGRUPPE muskelgruppe){
        this.id = id;
        this.beschreibung = beschreibung;
        this.bezeichnung = bezeichnung;
        this.muskelgruppe = muskelgruppe;
        this.sets=s;
        this.wdh=w;
        this.gwt=g;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public MUSKELGRUPPE getMuseklgruppe() {
        return muskelgruppe;
    }

    public void setMuseklgruppe(MUSKELGRUPPE mg) {
        this.muskelgruppe = mg;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getWdh() {
        return wdh;
    }

    public void setWdh(int wdh) {
        this.wdh = wdh;
    }

    public int getGwt() {
        return gwt;
    }

    public void setGwt(int gwt) {
        this.gwt = gwt;
    }



    @Override
    public String toString() {
        return this.bezeichnung + " " + this.beschreibung + " " + this.muskelgruppe ;
    }
}
