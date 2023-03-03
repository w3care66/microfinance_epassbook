package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpSendResponse {

@SerializedName("status")
@Expose
private String status;
@SerializedName("code")
@Expose
private Integer code;
@SerializedName("messages")
@Expose
private String messages;
@SerializedName("result")
@Expose
private OtpResult OtpResult;

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

public OtpResult getResult() {
return OtpResult;
}

public void setResult(OtpResult result) {
this.OtpResult = OtpResult;
}

}