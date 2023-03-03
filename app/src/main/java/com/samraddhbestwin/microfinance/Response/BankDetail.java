package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankDetail {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("member_id")
@Expose
private Integer memberId;
@SerializedName("bank_name")
@Expose
private String bankName;
@SerializedName("branch_name")
@Expose
private String branchName;
@SerializedName("account_no")
@Expose
private String accountNo;
@SerializedName("ifsc_code")
@Expose
private String ifscCode;
@SerializedName("address")
@Expose
private String address;
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

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getMemberId() {
return memberId;
}

public void setMemberId(Integer memberId) {
this.memberId = memberId;
}

public String getBankName() {
return bankName;
}

public void setBankName(String bankName) {
this.bankName = bankName;
}

public String getBranchName() {
return branchName;
}

public void setBranchName(String branchName) {
this.branchName = branchName;
}

public String getAccountNo() {
return accountNo;
}

public void setAccountNo(String accountNo) {
this.accountNo = accountNo;
}

public String getIfscCode() {
return ifscCode;
}

public void setIfscCode(String ifscCode) {
this.ifscCode = ifscCode;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
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

}