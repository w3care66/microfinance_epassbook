package com.samraddhbestwin.microfinance.Response.Respones.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberFormInformation {

@SerializedName("form_no")
@Expose
private String formNo;
@SerializedName("join_date")
@Expose
private String joinDate;
@SerializedName("member_id")
@Expose
private String memberId;
@SerializedName("branch_mi")
@Expose
private String branchMi;
@SerializedName("associate_no")
@Expose
private String associateNo;
@SerializedName("carder")
@Expose
private String carder;

public String getFormNo() {
return formNo;
}

public void setFormNo(String formNo) {
this.formNo = formNo;
}

public String getJoinDate() {
return joinDate;
}

public void setJoinDate(String joinDate) {
this.joinDate = joinDate;
}

public String getMemberId() {
return memberId;
}

public void setMemberId(String memberId) {
this.memberId = memberId;
}

public String getBranchMi() {
return branchMi;
}

public void setBranchMi(String branchMi) {
this.branchMi = branchMi;
}

public String getAssociateNo() {
return associateNo;
}

public void setAssociateNo(String associateNo) {
this.associateNo = associateNo;
}

public String getCarder() {
return carder;
}

public void setCarder(String carder) {
this.carder = carder;
}

}