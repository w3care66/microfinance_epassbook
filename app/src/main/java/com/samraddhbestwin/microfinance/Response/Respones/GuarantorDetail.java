package com.samraddhbestwin.microfinance.Response.Respones;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class GuarantorDetail {
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("member_id")
@Expose
private Integer memberId;
@SerializedName("first_name")
@Expose
private String firstName;
@SerializedName("first_mobile_no")
@Expose
private String firstMobileNo;
@SerializedName("first_address")
@Expose
private String firstAddress;
@SerializedName("second_name")
@Expose
private String secondName;
@SerializedName("second_mobile_no")
@Expose
private String secondMobileNo;
@SerializedName("second_address")
@Expose
private String secondAddress;
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

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getFirstMobileNo() {
return firstMobileNo;
}

public void setFirstMobileNo(String firstMobileNo) {
this.firstMobileNo = firstMobileNo;
}

public String getFirstAddress() {
return firstAddress;
}

public void setFirstAddress(String firstAddress) {
this.firstAddress = firstAddress;
}

public String getSecondName() {
return secondName;
}

public void setSecondName(String secondName) {
this.secondName = secondName;
}

public String getSecondMobileNo() {
return secondMobileNo;
}

public void setSecondMobileNo(String secondMobileNo) {
this.secondMobileNo = secondMobileNo;
}

public String getSecondAddress() {
return secondAddress;
}

public void setSecondAddress(String secondAddress) {
this.secondAddress = secondAddress;
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