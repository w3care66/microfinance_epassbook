package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranscationDetailsResponse {

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
private TranscationDetailsResult transcationDetailsResult;

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

public TranscationDetailsResult getTranscationDetailsResult() {
return transcationDetailsResult;
}

public void setTranscationDetailsResult(TranscationDetailsResult transcationDetailsResult) {
this.transcationDetailsResult = transcationDetailsResult;
}

}