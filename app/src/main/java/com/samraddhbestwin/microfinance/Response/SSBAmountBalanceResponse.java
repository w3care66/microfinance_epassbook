package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SSBAmountBalanceResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Currentbalance")
    @Expose
    private String currentbalance;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("associate_status")
    @Expose
    private Integer associateStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentbalance() {
        return currentbalance;
    }

    public void setCurrentbalance(String currentbalance) {
        this.currentbalance = currentbalance;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getAssociateStatus() {
        return associateStatus;
    }

    public void setAssociateStatus(Integer associateStatus) {
        this.associateStatus = associateStatus;
    }
}
