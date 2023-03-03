package com.samraddhbestwin.microfinance.Response.Respones.Profile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonalInformation {

@SerializedName("first_name")
@Expose
private String firstName;
@SerializedName("last_name")
@Expose
private String lastName;
@SerializedName("email")
@Expose
private Object email;
@SerializedName("mobile_no")
@Expose
private String mobileNo;
@SerializedName("age")
@Expose
private Integer age;
@SerializedName("gender")
@Expose
private String gender;
@SerializedName("occupation_id")
@Expose
private String occupationId;
@SerializedName("annual_income")
@Expose
private String annualIncome;
@SerializedName("mother_name")
@Expose
private Object motherName;
@SerializedName("father_husband")
@Expose
private String fatherHusband;
@SerializedName("marital_status")
@Expose
private String maritalStatus;
@SerializedName("anniversary_date")
@Expose
private String anniversaryDate;
@SerializedName("religion")
@Expose
private String religion;
@SerializedName("special_category")
@Expose
private String specialCategory;
@SerializedName("status")
@Expose
private String status;
@SerializedName("address")
@Expose
private String address;
@SerializedName("state")
@Expose
private String state;
@SerializedName("district")
@Expose
private String district;
@SerializedName("city")
@Expose
private String city;
@SerializedName("village")
@Expose
private String village;
@SerializedName("pin_code")
@Expose
private String pinCode;

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

public Object getEmail() {
return email;
}

public void setEmail(Object email) {
this.email = email;
}

public String getMobileNo() {
return mobileNo;
}

public void setMobileNo(String mobileNo) {
this.mobileNo = mobileNo;
}

public Integer getAge() {
return age;
}

public void setAge(Integer age) {
this.age = age;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public String getOccupationId() {
return occupationId;
}

public void setOccupationId(String occupationId) {
this.occupationId = occupationId;
}

public String getAnnualIncome() {
return annualIncome;
}

public void setAnnualIncome(String annualIncome) {
this.annualIncome = annualIncome;
}

public Object getMotherName() {
return motherName;
}

public void setMotherName(Object motherName) {
this.motherName = motherName;
}

public String getFatherHusband() {
return fatherHusband;
}

public void setFatherHusband(String fatherHusband) {
this.fatherHusband = fatherHusband;
}

public String getMaritalStatus() {
return maritalStatus;
}

public void setMaritalStatus(String maritalStatus) {
this.maritalStatus = maritalStatus;
}

public String getAnniversaryDate() {
return anniversaryDate;
}

public void setAnniversaryDate(String anniversaryDate) {
this.anniversaryDate = anniversaryDate;
}

public String getReligion() {
return religion;
}

public void setReligion(String religion) {
this.religion = religion;
}

public String getSpecialCategory() {
return specialCategory;
}

public void setSpecialCategory(String specialCategory) {
this.specialCategory = specialCategory;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getState() {
return state;
}

public void setState(String state) {
this.state = state;
}

public String getDistrict() {
return district;
}

public void setDistrict(String district) {
this.district = district;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getVillage() {
return village;
}

public void setVillage(String village) {
this.village = village;
}

public String getPinCode() {
return pinCode;
}

public void setPinCode(String pinCode) {
this.pinCode = pinCode;
}

}