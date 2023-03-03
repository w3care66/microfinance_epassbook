package com.samraddhbestwin.microfinance.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AssociateLoanDetail {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("messages")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("associate_status")
    @Expose
    private Integer associateStatus;
    @SerializedName("currentBalance")
    @Expose
    private String currentBalance;
    @SerializedName("account_detail")
    @Expose
    private List<AccountDetails> accountDetail = null;

    public Integer getAssociateStatus() {
        return associateStatus;
    }

    public void setAssociateStatus(Integer associateStatus) {
        this.associateStatus = associateStatus;
    }

    public String getStatus() {
        return status;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<AccountDetails> getAccountDetail() {
        return accountDetail;
    }

    public void setAccountDetail(List<AccountDetails> accountDetail) {
        this.accountDetail = accountDetail;
    }


    public static class AccountDetails {
        @SerializedName("account_id")
        @Expose
        private String accountId;
        @SerializedName("account_no")
        @Expose
        private String accountNo;
        @SerializedName("plan_name")
        @Expose
        private String planName;
        @SerializedName("member_name")
        @Expose
        private String memberName;
        @SerializedName("due_amount")
        @Expose
        private String dueAmount;
        @SerializedName("deno_amount")
        @Expose
        private String denoAmount;
        @SerializedName("emi_amount")
        @Expose
        private String emiAmount;
        @SerializedName("is_pay")
        @Expose
        private String isPay;
        @SerializedName("recovered_amount")
        @Expose
        private String recoverAmount;
        @SerializedName("is_penalty")
        @Expose
        private String isPenlty;
        @SerializedName("deposit_amount")
        @Expose
        private String depositAmount;
        @SerializedName("outStandingAmount")
        @Expose
        private String outStandingAmount;

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getDueAmount() {
            return dueAmount;
        }

        public void setDueAmount(String dueAmount) {
            this.dueAmount = dueAmount;
        }

        public String getDenoAmount() {
            return denoAmount;
        }

        public void setDenoAmount(String denoAmount) {
            this.denoAmount = denoAmount;
        }

        public String getEmiAmount() {
            return emiAmount;
        }

        public void setEmiAmount(String emiAmount) {
            this.emiAmount = emiAmount;
        }


        public String getIsPay() {
            return isPay;
        }

        public void setIsPay(String isPay) {
            this.isPay = isPay;
        }

        public String getRecoverAmount() {
            return recoverAmount;
        }

        public void setRecoverAmount(String recoverAmount) {
            this.recoverAmount = recoverAmount;
        }

        public String getIsPenlty() {
            return isPenlty;
        }

        public void setIsPenlty(String isPenlty) {
            this.isPenlty = isPenlty;
        }

        public String getDepositAmount() {
            return depositAmount;
        }

        public void setDepositAmount(String depositAmount) {
            this.depositAmount = depositAmount;
        }


        public String getOutStandingAmount() {
            return outStandingAmount;
        }

        public void setOutStandingAmount(String outStandingAmount) {
            this.outStandingAmount = outStandingAmount;
        }
    }


    public static class LevelList {
        @SerializedName("")
        @Expose
        private String levelTitle;
        @SerializedName("")
        @Expose
        private String levelValue;
    }
}
