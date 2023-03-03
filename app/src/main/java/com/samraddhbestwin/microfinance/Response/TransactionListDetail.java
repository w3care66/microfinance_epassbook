package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionListDetail {

@SerializedName("tDetails")
@Expose
private TranscationDetails transcationDetails;

public TranscationDetails getTDetails() {
return transcationDetails;
}

public void setTDetails(TranscationDetails transcationDetails) {
this.transcationDetails = transcationDetails;
}

}