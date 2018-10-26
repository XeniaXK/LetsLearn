package com.example.yohannallenzia.heylearn;

public class Questions {

    String id;
    String question;
    String opta;
    String optb;
    String optc;
    String answernr;
    String methodology;

    //no-args constructor
    public Questions() {
    }

    //constructor
    public Questions(String id, String question, String opta, String optb, String optc, String answernr, String methodology) {
        this.id = id;
        this.question = question;
        this.opta = opta;
        this.optb = optb;
        this.optc = optc;
        this.answernr = answernr;
        this.methodology = methodology;
    }

    //getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpta() {
        return opta;
    }

    public void setOpta(String opta) {
        this.opta = opta;
    }

    public String getOptb() {
        return optb;
    }

    public void setOptb(String optb) {
        this.optb = optb;
    }

    public String getOptc() {
        return optc;
    }

    public void setOptc(String optc) {
        this.optc = optc;
    }

    public String getAnswernr() {
        return answernr;
    }

    public void setAnswernr(String answernr) {
        this.answernr = answernr;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }
}
