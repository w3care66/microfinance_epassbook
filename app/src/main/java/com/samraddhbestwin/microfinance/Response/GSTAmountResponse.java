package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GSTAmountResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("gst_data")
    @Expose
    private GstData gstData;
    @SerializedName("associate_status")
    @Expose
    private Integer associateStatus;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public GstData getGstData() {
        return gstData;
    }

    public void setGstData(GstData gstData) {
        this.gstData = gstData;
    }

    public Integer getAssociateStatus() {
        return associateStatus;
    }

    public void setAssociateStatus(Integer associateStatus) {
        this.associateStatus = associateStatus;
    }


    public class GstData {

        @SerializedName("gst_label")
        @Expose
        private List<String> gstLabel = null;
        @SerializedName("labelName")
        @Expose
        private String labelName;
        @SerializedName("labelValue")
        @Expose
        private String labelValue;

        public List<String> getGstLabel() {
            return gstLabel;
        }

        public void setGstLabel(List<String> gstLabel) {
            this.gstLabel = gstLabel;
        }

        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }

        public String getLabelValue() {
            return labelValue;
        }

        public void setLabelValue(String labelValue) {
            this.labelValue = labelValue;
        }

    }


}
