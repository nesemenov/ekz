package com.example.ekz.Models;

public class Meditations {

    String idMed, timeToComplite, timeToReseat, nameMed;


    public Meditations(String idMed, String timeToComplite, String timeToReseat,String nameMed) {
        this.idMed = idMed;
        this.timeToComplite = timeToComplite;
        this.timeToReseat = timeToReseat;
        this.nameMed = nameMed;
    }

    public String getNameMed() {
        return nameMed;
    }

    public void setNameMed(String nameMed) {
        this.nameMed = nameMed;
    }

    public String getIdMed() {
        return idMed;
    }

    public void setIdMed(String idMed) {
        this.idMed = idMed;
    }

    public String getTimeToComplite() {
        return timeToComplite;
    }

    public void setTimeToComplite(String timeToComplite) {
        this.timeToComplite = timeToComplite;
    }

    public String getTimeToReseat() {
        return timeToReseat;
    }

    public void setTimeToReseat(String timeToReseat) {
        this.timeToReseat = timeToReseat;
    }
}
