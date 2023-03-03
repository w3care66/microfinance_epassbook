package com.samraddhbestwin.microfinance.Model;

public class DashbordModel {
    String invetment_id;
    String createAt;
    String formNo;
    String plan;
    String associte;
    String accountNo;
    String tenure;
    String balance;
    String deposite_amount;
    public DashbordModel(String invetment_id, String createAt, String formNo, String plan, String associte, String accountNo, String tenure, String balance, String deposite_amount) {
        this.invetment_id = invetment_id;
        this.createAt = createAt;
        this.formNo = formNo;
        this.plan = plan;
        this.associte = associte;
        this.accountNo = accountNo;
        this.tenure = tenure;
        this.balance = balance;
        this.deposite_amount = deposite_amount;
    }



    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getAssocite() {
        return associte;
    }

    public void setAssocite(String associte) {
        this.associte = associte;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDeposite_amount() {
        return deposite_amount;
    }

    public void setDeposite_amount(String deposite_amount) {
        this.deposite_amount = deposite_amount;
    }

    public String getInvetment_id() {
        return invetment_id;
    }

    public void setInvetment_id(String invetment_id) {
        this.invetment_id = invetment_id;
    }

}
