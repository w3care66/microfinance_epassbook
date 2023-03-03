package com.samraddhbestwin.microfinance.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberDetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("form_no")
    @Expose
    private String formNo;
    @SerializedName("re_date")
    @Expose
    private String reDate;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("role_id")
    @Expose
    private Integer roleId;
    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("associate_code")
    @Expose
    private String associateCode;
    @SerializedName("mi_code")
    @Expose
    private Integer miCode;
    @SerializedName("fa_code")
    @Expose
    private Integer faCode;
    @SerializedName("branch_id")
    @Expose
    private Integer branchId;
    @SerializedName("branch_code")
    @Expose
    private Integer branchCode;
    @SerializedName("associate_id")
    @Expose
    private Integer associateId;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("annual_income")
    @Expose
    private String annualIncome;
    @SerializedName("occupation_id")
    @Expose
    private String occupationId;
    @SerializedName("marital_status")
    @Expose
    private String maritalStatus;
    @SerializedName("anniversary_date")
    @Expose
    private String anniversaryDate;
    @SerializedName("father_husband")
    @Expose
    private String fatherHusband;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("state_id")
    @Expose
    private Integer stateId;
    @SerializedName("district_id")
    @Expose
    private Integer districtId;
    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("village")
    @Expose
    private String village;
    @SerializedName("pin_code")
    @Expose
    private String pinCode;
    @SerializedName("religion_id")
    @Expose
    private String religionId;
    @SerializedName("mother_name")
    @Expose
    private Object motherName;
    @SerializedName("signature")
    @Expose
    private String signature;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("special_category_id")
    @Expose
    private String specialCategoryId;
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
    @SerializedName("is_associate")
    @Expose
    private Integer isAssociate;
    @SerializedName("associate_micode")
    @Expose
    private Integer associateMicode;
    @SerializedName("associate_facode")
    @Expose
    private Integer associateFacode;
    @SerializedName("associate_no")
    @Expose
    private String associateNo;
    @SerializedName("associate_status")
    @Expose
    private Integer associateStatus;
    @SerializedName("associate_form_no")
    @Expose
    private String associateFormNo;
    @SerializedName("associate_join_date")
    @Expose
    private String associateJoinDate;
    @SerializedName("associate_senior_code")
    @Expose
    private String associateSeniorCode;
    @SerializedName("associate_senior_id")
    @Expose
    private Integer associateSeniorId;
    @SerializedName("current_carder_id")
    @Expose
    private Integer currentCarderId;
    @SerializedName("ssb_account")
    @Expose
    private String ssbAccount;
    @SerializedName("rd_account")
    @Expose
    private String rdAccount;
    @SerializedName("is_block")
    @Expose
    private Integer isBlock;
    @SerializedName("branch_mi")
    @Expose
    private String branchMi;
    @SerializedName("reinvest_approve_status")
    @Expose
    private Integer reinvestApproveStatus;
    @SerializedName("reinvest_opening_date")
    @Expose
    private String reinvestOpeningDate;
    @SerializedName("reinvest_old_account_number")
    @Expose
    private Object reinvestOldAccountNumber;
    @SerializedName("old_c_id")
    @Expose
    private Object oldCId;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("varifiy_time")
    @Expose
    private Integer varifiyTime;
    @SerializedName("is_varified")
    @Expose
    private Integer isVarified;
    @SerializedName("upi")
    @Expose
    private Integer upi;
    @SerializedName("imageurl")
    @Expose
    private String imageurl;
    @SerializedName("signatureurl")
    @Expose
    private String signatureurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
    }

    public String getReDate() {
        return reDate;
    }

    public void setReDate(String reDate) {
        this.reDate = reDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAssociateCode() {
        return associateCode;
    }

    public void setAssociateCode(String associateCode) {
        this.associateCode = associateCode;
    }

    public Integer getMiCode() {
        return miCode;
    }

    public void setMiCode(Integer miCode) {
        this.miCode = miCode;
    }

    public Integer getFaCode() {
        return faCode;
    }

    public void setFaCode(Integer faCode) {
        this.faCode = faCode;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Integer branchCode) {
        this.branchCode = branchCode;
    }

    public Integer getAssociateId() {
        return associateId;
    }

    public void setAssociateId(Integer associateId) {
        this.associateId = associateId;
    }

    public String getLastName() {
        return lastName == null ? "" : lastName;
//         lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(String occupationId) {
        this.occupationId = occupationId;
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

    public String getFatherHusband() {
        return fatherHusband;
    }

    public void setFatherHusband(String fatherHusband) {
        this.fatherHusband = fatherHusband;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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

    public String getReligionId() {
        return religionId;
    }

    public void setReligionId(String religionId) {
        this.religionId = religionId;
    }

    public Object getMotherName() {
        return motherName;
    }

    public void setMotherName(Object motherName) {
        this.motherName = motherName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSpecialCategoryId() {
        return specialCategoryId;
    }

    public void setSpecialCategoryId(String specialCategoryId) {
        this.specialCategoryId = specialCategoryId;
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

    public Integer getIsAssociate() {
        return isAssociate;
    }

    public void setIsAssociate(Integer isAssociate) {
        this.isAssociate = isAssociate;
    }

    public Integer getAssociateMicode() {
        return associateMicode;
    }

    public void setAssociateMicode(Integer associateMicode) {
        this.associateMicode = associateMicode;
    }

    public Integer getAssociateFacode() {
        return associateFacode;
    }

    public void setAssociateFacode(Integer associateFacode) {
        this.associateFacode = associateFacode;
    }

    public String getAssociateNo() {
        return associateNo;
    }

    public void setAssociateNo(String associateNo) {
        this.associateNo = associateNo;
    }

    public Integer getAssociateStatus() {
        return associateStatus;
    }

    public void setAssociateStatus(Integer associateStatus) {
        this.associateStatus = associateStatus;
    }

    public String getAssociateFormNo() {
        return associateFormNo;
    }

    public void setAssociateFormNo(String associateFormNo) {
        this.associateFormNo = associateFormNo;
    }

    public String getAssociateJoinDate() {
        return associateJoinDate;
    }

    public void setAssociateJoinDate(String associateJoinDate) {
        this.associateJoinDate = associateJoinDate;
    }

    public String getAssociateSeniorCode() {
        return associateSeniorCode;
    }

    public void setAssociateSeniorCode(String associateSeniorCode) {
        this.associateSeniorCode = associateSeniorCode;
    }

    public Integer getAssociateSeniorId() {
        return associateSeniorId;
    }

    public void setAssociateSeniorId(Integer associateSeniorId) {
        this.associateSeniorId = associateSeniorId;
    }

    public Integer getCurrentCarderId() {
        return currentCarderId;
    }

    public void setCurrentCarderId(Integer currentCarderId) {
        this.currentCarderId = currentCarderId;
    }

    public String getSsbAccount() {
        return ssbAccount;
    }

    public void setSsbAccount(String ssbAccount) {
        this.ssbAccount = ssbAccount;
    }

    public String getRdAccount() {
        return rdAccount;
    }

    public void setRdAccount(String rdAccount) {
        this.rdAccount = rdAccount;
    }

    public Integer getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Integer isBlock) {
        this.isBlock = isBlock;
    }

    public String getBranchMi() {
        return branchMi;
    }

    public void setBranchMi(String branchMi) {
        this.branchMi = branchMi;
    }

    public Integer getReinvestApproveStatus() {
        return reinvestApproveStatus;
    }

    public void setReinvestApproveStatus(Integer reinvestApproveStatus) {
        this.reinvestApproveStatus = reinvestApproveStatus;
    }

    public String getReinvestOpeningDate() {
        return reinvestOpeningDate;
    }

    public void setReinvestOpeningDate(String reinvestOpeningDate) {
        this.reinvestOpeningDate = reinvestOpeningDate;
    }

    public Object getReinvestOldAccountNumber() {
        return reinvestOldAccountNumber;
    }

    public void setReinvestOldAccountNumber(Object reinvestOldAccountNumber) {
        this.reinvestOldAccountNumber = reinvestOldAccountNumber;
    }

    public Object getOldCId() {
        return oldCId;
    }

    public void setOldCId(Object oldCId) {
        this.oldCId = oldCId;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Integer getVarifiyTime() {
        return varifiyTime;
    }

    public void setVarifiyTime(Integer varifiyTime) {
        this.varifiyTime = varifiyTime;
    }

    public Integer getIsVarified() {
        return isVarified;
    }

    public void setIsVarified(Integer isVarified) {
        this.isVarified = isVarified;
    }

    public Integer getUpi() {
        return upi;
    }

    public void setUpi(Integer upi) {
        this.upi = upi;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getSignatureurl() {
        return signatureurl;
    }

    public void setSignatureurl(String signatureurl) {
        this.signatureurl = signatureurl;
    }

}