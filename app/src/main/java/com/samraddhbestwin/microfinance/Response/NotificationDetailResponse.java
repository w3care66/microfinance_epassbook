package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationDetailResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("current_balance")
    @Expose
    private String currentBalance;
    @SerializedName("data")
    @Expose
    private NotificationDetails data;

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

    public NotificationDetails getData() {
        return data;
    }

    public void setData(NotificationDetails data) {
        this.data = data;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public class NotificationDetails {
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
        @SerializedName("due_amount")
        @Expose
        private String dueAmount;
        @SerializedName("emi_amount")
        @Expose
        private String emiAmount;
        @SerializedName("plan_name")
        @Expose
        private String planName;
        @SerializedName("loan_amount")
        @Expose
        private String loanAmount;
        @SerializedName("issue_date")
        @Expose
        private String issueDate;
        @SerializedName("instalment")
        @Expose
        private String instalment;
        @SerializedName("mode")
        @Expose
        private String mode;
        @SerializedName("emi_paid")
        @Expose
        private String emiPaid;

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

        public String getDueAmount() {
            return dueAmount;
        }

        public void setDueAmount(String dueAmount) {
            this.dueAmount = dueAmount;
        }

        public String getEmiAmount() {
            return emiAmount;
        }

        public void setEmiAmount(String emiAmount) {
            this.emiAmount = emiAmount;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }

        public String getLoanAmount() {
            return loanAmount;
        }

        public void setLoanAmount(String loanAmount) {
            this.loanAmount = loanAmount;
        }

        public String getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(String issueDate) {
            this.issueDate = issueDate;
        }

        public String getInstalment() {
            return instalment;
        }

        public void setInstalment(String instalment) {
            this.instalment = instalment;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getEmiPaid() {
            return emiPaid;
        }

        public void setEmiPaid(String emiPaid) {
            this.emiPaid = emiPaid;
        }
    }
}
