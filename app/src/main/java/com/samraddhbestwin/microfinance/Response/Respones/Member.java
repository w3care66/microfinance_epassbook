package com.samraddhbestwin.microfinance.Response.Respones;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("associate_number")
@Expose
private String associateNumber;
@SerializedName("mobile_no")
@Expose
private String mobileNo;
@SerializedName("profile_imge")
@Expose
private String profileImge;
@SerializedName("branch_code")
@Expose
private String branchCode;
@SerializedName("branch_name")
@Expose
private String branchName;
@SerializedName("senior_associate_code")
@Expose
private String seniorAssociateCode;
@SerializedName("senior_associate_name")
@Expose
private String seniorAssociateName;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getAssociateNumber() {
return associateNumber;
}

public void setAssociateNumber(String associateNumber) {
this.associateNumber = associateNumber;
}

public String getMobileNo() {
return mobileNo;
}

public void setMobileNo(String mobileNo) {
this.mobileNo = mobileNo;
}

public String getProfileImge() {
return profileImge;
}

public void setProfileImge(String profileImge) {
this.profileImge = profileImge;
}

public String getBranchCode() {
return branchCode;
}

public void setBranchCode(String branchCode) {
this.branchCode = branchCode;
}

public String getBranchName() {
return branchName;
}

public void setBranchName(String branchName) {
this.branchName = branchName;
}

public String getSeniorAssociateCode() {
return seniorAssociateCode;
}

public void setSeniorAssociateCode(String seniorAssociateCode) {
this.seniorAssociateCode = seniorAssociateCode;
}

public String getSeniorAssociateName() {
return seniorAssociateName;
}

public void setSeniorAssociateName(String seniorAssociateName) {
this.seniorAssociateName = seniorAssociateName;
}

}