package com.samraddhbestwin.microfinance.Model;
public class MyTranscationModel {


    String investmentid;
    String transcatoinid;
    String date;
    String description;
    String chequeNo;
    String withdrawal;
    String deposite;
    String balance;
    String payment_type;
    String emiDeposit;
    String type;

    public MyTranscationModel(String investmentid,String transcatoinid, String date, String description, String chequeNo, String withdrawal, String deposite, String balance,String payment_type, String _emiDeposit, String _type) {
        this.transcatoinid = transcatoinid;
        this.date = date;
        this.description = description;
        this.chequeNo = chequeNo;
        this.withdrawal = withdrawal;
        this.deposite = deposite;
        this.balance = balance;
        this.payment_type = payment_type;
        this.investmentid=investmentid;
        this.emiDeposit=_emiDeposit;
        this.type=_type;
    }

    public String getTranscatoinid() {
        return transcatoinid;
    }

    public void setTranscatoinid(String transcatoinid) {
        this.transcatoinid = transcatoinid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(String withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getDeposite() {
        return deposite;
    }

    public void setDeposite(String deposite) {
        this.deposite = deposite;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getInvestmentid() {
        return investmentid;
    }

    public void setInvestmentid(String investmentid) {
        this.investmentid = investmentid;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getEmiDeposit() {
        return emiDeposit;
    }

    public void setEmiDeposit(String emiDeposit) {
        this.emiDeposit = emiDeposit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
