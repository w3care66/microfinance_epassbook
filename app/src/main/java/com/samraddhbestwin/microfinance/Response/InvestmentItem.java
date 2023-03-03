package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvestmentItem {

@SerializedName("invetment_id")
@Expose
private Integer invetmentId;
@SerializedName("account_number")
@Expose
private String accountNumber;
@SerializedName("plan")
@Expose
private String plan;
@SerializedName("form_number")
@Expose
private String formNumber;
@SerializedName("created_at")
@Expose
private String createdAt;

public Integer getInvetmentId() {
return invetmentId;
}

public void setInvetmentId(Integer invetmentId) {
this.invetmentId = invetmentId;
}

public String getAccountNumber() {
return accountNumber;
}

public void setAccountNumber(String accountNumber) {
this.accountNumber = accountNumber;
}

public String getPlan() {
return plan;
}

public void setPlan(String plan) {
this.plan = plan;
}

public String getFormNumber() {
return formNumber;
}

public void setFormNumber(String formNumber) {
this.formNumber = formNumber;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

}