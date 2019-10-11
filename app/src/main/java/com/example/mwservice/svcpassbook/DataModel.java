package com.example.mwservice.svcpassbook;

/**
 * Created by mwservice on 04-04-2018.
 */

public class DataModel {
    String date,particular,transactionId,withdraw,deposit,balance;

    public DataModel(String date, String particular, String transactionId, String withdraw, String deposit, String balance) {
        this.date = date;
        this.particular = particular;
        this.transactionId = transactionId;
        this.withdraw = withdraw;
        this.deposit = deposit;
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParticular() {
        return particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(String withdraw) {
        this.withdraw = withdraw;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
