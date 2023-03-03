package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoanListResponse {

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
    private List<LoanListData> data = null;

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

    public List<LoanListData> getData() {
        return data;
    }

    public void setData(List<LoanListData> data) {
        this.data = data;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }


    public static class LoanListData {
        @SerializedName("account_number")
        @Expose
        private String accountNumber;
        @SerializedName("emi_amount")
        @Expose
        private String emiAmount;
        @SerializedName("plan_name")
        @Expose
        private String planName;
        @SerializedName("investment_id")
        @Expose
        private String investmentId;
        @SerializedName("loan_id")
        @Expose
        private String loanId;
        @SerializedName("loan_status")
        @Expose
        private String loanStatus;
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
        @SerializedName("application_date")
        @Expose
        private String applicationDate;
        @SerializedName("is_running_loan")
        @Expose
        private String isRunningLoan;
        @SerializedName("application_no")
        @Expose
        private String applicationNo;


        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
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

        public String getInvestmentId() {
            return investmentId;
        }

        public void setInvestmentId(String investmentId) {
            this.investmentId = investmentId;
        }

        public String getLoanId() {
            return loanId;
        }

        public void setLoanId(String loanId) {
            this.loanId = loanId;
        }

        public String getLoanStatus() {
            return loanStatus;
        }

        public void setLoanStatus(String loanStatus) {
            this.loanStatus = loanStatus;
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

        public String getApplicationDate() {
            return applicationDate;
        }

        public void setApplicationDate(String applicationDate) {
            this.applicationDate = applicationDate;
        }

        public String getIsRunningLoan() {
            return isRunningLoan;
        }

        public void setIsRunningLoan(String isRunningLoan) {
            this.isRunningLoan = isRunningLoan;
        }

        public String getApplicationNo() {
            return applicationNo;
        }

        public void setApplicationNo(String applicationNo) {
            this.applicationNo = applicationNo;
        }
    }
}
