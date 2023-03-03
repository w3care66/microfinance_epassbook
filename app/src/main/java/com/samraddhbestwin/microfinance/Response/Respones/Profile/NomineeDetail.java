package com.samraddhbestwin.microfinance.Response.Respones.Profile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class NomineeDetail {
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("member_id")
@Expose
private Integer memberId;
@SerializedName("name")
@Expose
private String name;
@SerializedName("relation")
@Expose
private String relation;
@SerializedName("gender")
@Expose
private String gender;
@SerializedName("dob")
@Expose
private String dob;
@SerializedName("age")
@Expose
private Integer age;
@SerializedName("mobile_no")
@Expose
private String mobileNo;
@SerializedName("is_minor")
@Expose
private String isMinor;
@SerializedName("parent_name")
@Expose
private Object parentName;
@SerializedName("parent_no")
@Expose
private Object parentNo;
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

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getRelation() {
return relation;
}

public void setRelation(String relation) {
this.relation = relation;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public String getDob() {
return dob;
}

public void setDob(String dob) {
this.dob = dob;
}

public Integer getAge() {
return age;
}

public void setAge(Integer age) {
this.age = age;
}

public String getMobileNo() {
return mobileNo;
}

public void setMobileNo(String mobileNo) {
this.mobileNo = mobileNo;
}

public String getIsMinor() {
return isMinor;
}

public void setIsMinor(String isMinor) {
this.isMinor = isMinor;
}

public Object getParentName() {
return parentName;
}

public void setParentName(Object parentName) {
this.parentName = parentName;
}

public Object getParentNo() {
return parentNo;
}

public void setParentNo(Object parentNo) {
this.parentNo = parentNo;
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