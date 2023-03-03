package com.samraddhbestwin.microfinance.Response.Respones.Profile;
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

}