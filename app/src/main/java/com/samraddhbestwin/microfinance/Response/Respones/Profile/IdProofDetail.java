package com.samraddhbestwin.microfinance.Response.Respones.Profile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdProofDetail {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("member_id")
@Expose
private Integer memberId;
@SerializedName("first_id_type_id")
@Expose
private String firstIdTypeId;
@SerializedName("first_id_no")
@Expose
private String firstIdNo;
@SerializedName("first_address")
@Expose
private String firstAddress;
@SerializedName("second_id_type_id")
@Expose
private String secondIdTypeId;
@SerializedName("second_id_no")
@Expose
private String secondIdNo;
@SerializedName("second_address")
@Expose
private String secondAddress;
@SerializedName("status")
@Expose
private String status;
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

public String getFirstIdTypeId() {
return firstIdTypeId;
}

public void setFirstIdTypeId(String firstIdTypeId) {
this.firstIdTypeId = firstIdTypeId;
}

public String getFirstIdNo() {
return firstIdNo;
}

public void setFirstIdNo(String firstIdNo) {
this.firstIdNo = firstIdNo;
}

public String getFirstAddress() {
return firstAddress;
}

public void setFirstAddress(String firstAddress) {
this.firstAddress = firstAddress;
}

public String getSecondIdTypeId() {
return secondIdTypeId;
}

public void setSecondIdTypeId(String secondIdTypeId) {
this.secondIdTypeId = secondIdTypeId;
}

public String getSecondIdNo() {
return secondIdNo;
}

public void setSecondIdNo(String secondIdNo) {
this.secondIdNo = secondIdNo;
}

public String getSecondAddress() {
return secondAddress;
}

public void setSecondAddress(String secondAddress) {
this.secondAddress = secondAddress;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
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