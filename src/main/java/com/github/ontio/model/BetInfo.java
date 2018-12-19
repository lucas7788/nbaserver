package com.github.ontio.model;

import java.math.BigDecimal;

public class BetInfo {
    public String time;
    public String txHash;
    public String address;
    public Integer gameID;
    public String HorV;
    public BigDecimal amount;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getHorV() {
        return HorV;
    }

    public void setHorV(String horV) {
        HorV = horV;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }
}
