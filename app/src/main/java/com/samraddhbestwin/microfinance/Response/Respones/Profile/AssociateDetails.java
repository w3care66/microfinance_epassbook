package com.samraddhbestwin.microfinance.Response.Respones.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssociateDetails {

@SerializedName("associate_code")
@Expose
private String associateCode;
@SerializedName("associate_name")
@Expose
private String associateName;
@SerializedName("mobile_no")
@Expose
private String mobileNo;
@SerializedName("carder")
@Expose
private String carder;

public String getAssociateCode() {
return associateCode;
}

public void setAssociateCode(String associateCode) {
this.associateCode = associateCode;
}

public String getAssociateName() {
return associateName;
}

public void setAssociateName(String associateName) {
this.associateName = associateName;
}

public String getMobileNo() {
return mobileNo;
}

public void setMobileNo(String mobileNo) {
this.mobileNo = mobileNo;
}

public String getCarder() {
return carder;
}

public void setCarder(String carder) {
this.carder = carder;
}

}