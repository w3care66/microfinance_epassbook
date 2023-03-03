package com.samraddhbestwin.microfinance.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SSBModelResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("data")
    @Expose
    private SSBDetails data;
    @SerializedName("is_card_available")
    @Expose
    private Boolean isCardAvailable;
    @SerializedName("notification_count")
    @Expose
    private String notificationCount;

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

    public SSBDetails getData() {
        return data;
    }

    public void setData(SSBDetails data) {
        this.data = data;
    }

    public Boolean getCardAvailable() {
        return isCardAvailable;
    }

    public void setCardAvailable(Boolean cardAvailable) {
        isCardAvailable = cardAvailable;
    }

    public String getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(String notificationCount) {
        this.notificationCount = notificationCount;
    }

    public class SSBDetails {

        @SerializedName("amounts")
        @Expose
        private String amounts;
        @SerializedName("max_amount")
        @Expose
        private String maxAmount;
        @SerializedName("min_request_amount")
        @Expose
        private String minRequestAmount;
        @SerializedName("max_request_amount")
        @Expose
        private String maxRequestAmount;
        @SerializedName("is_button_show")
        @Expose
        private String isButtonShow;

        public String getAmounts() {
            return amounts;
        }

        public void setAmounts(String amounts) {
            this.amounts = amounts;
        }

        public String getMaxAmount() {
            return maxAmount;
        }

        public void setMaxAmount(String maxAmount) {
            this.maxAmount = maxAmount;
        }

        public String getIsButtonShow() {
            return isButtonShow;
        }

        public void setIsButtonShow(String isButtonShow) {
            this.isButtonShow = isButtonShow;
        }

        public String getMinRequestAmount() {
            return minRequestAmount;
        }

        public void setMinRequestAmount(String minRequestAmount) {
            this.minRequestAmount = minRequestAmount;
        }

        public String getMaxRequestAmount() {
            return maxRequestAmount;
        }

        public void setMaxRequestAmount(String maxRequestAmount) {
            this.maxRequestAmount = maxRequestAmount;
        }
    }
}
