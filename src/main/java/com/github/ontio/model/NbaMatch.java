package com.github.ontio.model;

public class NbaMatch {

    public String date;
    public String gameID;
    public String hTeamID;
    public String vTeamID;
    public String state;
    public String txHash;

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String gethTeamID() {
        return hTeamID;
    }

    public void sethTeamID(String hTeamID) {
        this.hTeamID = hTeamID;
    }

    public String getvTeamID() {
        return vTeamID;
    }

    public void setvTeamID(String vTeamID) {
        this.vTeamID = vTeamID;
    }
}
