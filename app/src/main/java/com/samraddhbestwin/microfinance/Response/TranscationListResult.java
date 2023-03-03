package com.samraddhbestwin.microfinance.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranscationListResult {

@SerializedName("transactions")
@Expose
private List<TransactionListItem> transactionListItems = null;

public List<TransactionListItem> getTransactionListItems() {
return transactionListItems;
}

public void setTransactionListItems(List<TransactionListItem> transactionListItems) {
this.transactionListItems = transactionListItems;
}

}
