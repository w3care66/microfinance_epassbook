package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

public class TranscationDetails {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("transaction_type")
    @Expose
    private Integer transactionType;
    @SerializedName("transaction_id")
    @Expose
    private Integer transactionId;
    @SerializedName("saving_account_transaction_reference_id")
    @Expose
    private Object savingAccountTransactionReferenceId;
    @SerializedName("investment_id")
    @Expose
    private Integer investmentId;
    @SerializedName("account_no")
    @Expose
    private String accountNo;
    @SerializedName("associate_id")
    @Expose
    private Integer associateId;
    @SerializedName("member_id")
    @Expose
    private Integer memberId;
    @SerializedName("opening_balance")
    @Expose
    private String openingBalance;
    @SerializedName("deposit")
    @Expose
    private String deposit;
    @SerializedName("withdrawal")
    @Expose
    private String withdrawal;
    @SerializedName("is_eli")
    @Expose
    private Integer isEli;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("reference_no")
    @Expose
    private Object referenceNo;
    @SerializedName("branch_id")
    @Expose
    private Integer branchId;
    @SerializedName("branch_code")
    @Expose
    private Integer branchCode;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("payment_mode")
    @Expose
    private Integer paymentMode;
    @SerializedName("saving_account_id")
    @Expose
    private Integer savingAccountId;
    @SerializedName("cheque_dd_no")
    @Expose
    private String chequeDdNo;
    @SerializedName("bank_name")
    @Expose
    private Object bankName;
    @SerializedName("branch_name")
    @Expose
    private Object branchName;
    @SerializedName("payment_date")
    @Expose
    private Object paymentDate;
    @SerializedName("online_payment_id")
    @Expose
    private Object onlinePaymentId;
    @SerializedName("online_payment_by")
    @Expose
    private Object onlinePaymentBy;
    @SerializedName("amount_deposit_by_name")
    @Expose
    private String amountDepositByName;
    @SerializedName("amount_deposit_by_id")
    @Expose
    private Integer amountDepositById;
    @SerializedName("created_by_id")
    @Expose
    private Integer createdById;
    @SerializedName("created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("is_renewal")
    @Expose
    private Integer isRenewal;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("is_deleted")
    @Expose
    private Integer isDeleted;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deo_amount")
    @Expose
    private String deoAmount;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("associate_name")
    @Expose
    private String associateName;
    @SerializedName("associate_code")
    @Expose
    private String associateCode;
    @SerializedName("due_amount")
    @Expose
    private Integer due_amount;

    public Integer getDue_amount() {
        return due_amount;
    }

    public void setDue_amount(Integer due_amount) {
        this.due_amount = due_amount;
    }

    public Integer getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(Integer plan_id) {
        this.plan_id = plan_id;
    }

    @SerializedName("plan_id")
    @Expose
    private Integer plan_id;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Object getSavingAccountTransactionReferenceId() {
        return savingAccountTransactionReferenceId;
    }

    public void setSavingAccountTransactionReferenceId(Object savingAccountTransactionReferenceId) {
        this.savingAccountTransactionReferenceId = savingAccountTransactionReferenceId;
    }

    public Integer getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(Integer investmentId) {
        this.investmentId = investmentId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getAssociateId() {
        return associateId;
    }

    public void setAssociateId(Integer associateId) {
        this.associateId = associateId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(String withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Integer getIsEli() {
        return isEli;
    }

    public void setIsEli(Integer isEli) {
        this.isEli = isEli;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(Object referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Integer branchCode) {
        this.branchCode = branchCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Integer getSavingAccountId() {
        return savingAccountId;
    }

    public void setSavingAccountId(Integer savingAccountId) {
        this.savingAccountId = savingAccountId;
    }

    public String getChequeDdNo() {
        return chequeDdNo;
    }

    public void setChequeDdNo(String chequeDdNo) {
        this.chequeDdNo = chequeDdNo;
    }

    public Object getBankName() {
        return bankName;
    }

    public void setBankName(Object bankName) {
        this.bankName = bankName;
    }

    public Object getBranchName() {
        return branchName;
    }

    public void setBranchName(Object branchName) {
        this.branchName = branchName;
    }

    public Object getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Object paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Object getOnlinePaymentId() {
        return onlinePaymentId;
    }

    public void setOnlinePaymentId(Object onlinePaymentId) {
        this.onlinePaymentId = onlinePaymentId;
    }

    public Object getOnlinePaymentBy() {
        return onlinePaymentBy;
    }

    public void setOnlinePaymentBy(Object onlinePaymentBy) {
        this.onlinePaymentBy = onlinePaymentBy;
    }

    public String getAmountDepositByName() {
        return amountDepositByName;
    }

    public void setAmountDepositByName(String amountDepositByName) {
        this.amountDepositByName = amountDepositByName;
    }

    public Integer getAmountDepositById() {
        return amountDepositById;
    }

    public void setAmountDepositById(Integer amountDepositById) {
        this.amountDepositById = amountDepositById;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getIsRenewal() {
        return isRenewal;
    }

    public void setIsRenewal(Integer isRenewal) {
        this.isRenewal = isRenewal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeoAmount() {
        return deoAmount;
    }

    public void setDeoAmount(String deoAmount) {
        this.deoAmount = deoAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

    public String getAssociateCode() {
        return associateCode;
    }

    public void setAssociateCode(String associateCode) {
        this.associateCode = associateCode;
    }

}