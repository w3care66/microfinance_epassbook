package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranscationDetailsResult {

@SerializedName("transactionDetail")
@Expose
private TransactionListDetail transactionListDetail;

public TransactionListDetail getTransactionListDetail() {
return transactionListDetail;
}

public void setTransactionListDetail(TransactionListDetail transactionListDetail) {
this.transactionListDetail = transactionListDetail;
}

}
