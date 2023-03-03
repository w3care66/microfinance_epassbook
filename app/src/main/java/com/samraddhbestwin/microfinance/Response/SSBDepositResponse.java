package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SSBDepositResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("currentBalance")
    @Expose
    private String currentBalance;
    @SerializedName("data")
    @Expose
    private List<SSBDepositData> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<SSBDepositData> getData() {
        return data;
    }

    public void setData(List<SSBDepositData> data) {
        this.data = data;
    }

    public static class SSBDepositData {

        @SerializedName("investment_id")
        @Expose
        private String investmentId;
        @SerializedName("account_number")
        @Expose
        private String accountNumber;
        @SerializedName("deno_amount")
        @Expose
        private String denoAmount;
        @SerializedName("maturity_date")
        @Expose
        private String maturityDate;
        @SerializedName("deposit")
        @Expose
        private String deposit;
        @SerializedName("opening_date")
        @Expose
        private String openingDate;
        @SerializedName("plan")
        @Expose
        private String plan;
        @SerializedName("due_amount")
        @Expose
        private String dueAmount;
        @SerializedName("is_transaction_available")
        @Expose
        private int isTransactionAvailable;

        public String getDueAmount() {
            return dueAmount;
        }

        public void setDueAmount(String dueAmount) {
            this.dueAmount = dueAmount;
        }

        public String getInvestmentId() {
            return investmentId;
        }

        public void setInvestmentId(String investmentId) {
            this.investmentId = investmentId;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getDenoAmount() {
            return denoAmount;
        }

        public void setDenoAmount(String denoAmount) {
            this.denoAmount = denoAmount;
        }

        public String getMaturityDate() {
            return maturityDate;
        }

        public void setMaturityDate(String maturityDate) {
            this.maturityDate = maturityDate;
        }

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public String getOpeningDate() {
            return openingDate;
        }

        public void setOpeningDate(String openingDate) {
            this.openingDate = openingDate;
        }

        public String getPlan() {
            return plan;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }

        public int getIsTransactionAvailable() {
            return isTransactionAvailable;
        }

        public void setIsTransactionAvailable(int isTransactionAvailable) {
            this.isTransactionAvailable = isTransactionAvailable;
        }
    }

}
