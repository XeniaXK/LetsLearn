package com.example.yohannallenzia.heylearn;

public class Card {

    String cardId;
    String frontText;
    String backText;

    //no-agrs constructor
    public Card(){

    }

    //constructor
    public Card(String cardId, String frontText, String backText) {
        this.cardId = cardId;
        this.frontText = frontText;
        this.backText = backText;
    }

    //getters and setters
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }
}
