package com.samraddhbestwin.microfinance.Response.Respones.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserProfile {

@SerializedName("bankDetail")
@Expose
private BankDetail bankDetail;
@SerializedName("nomineeDetail")
@Expose
private NomineeDetail nomineeDetail;
@SerializedName("idProofDetail")
@Expose
private IdProofDetail idProofDetail;
@SerializedName("member_form_information")
@Expose
private MemberFormInformation memberFormInformation;
@SerializedName("associate_details")
@Expose
private AssociateDetails associateDetails;
@SerializedName("personal_information")
@Expose
private PersonalInformation personalInformation;
@SerializedName("profile_imge")
@Expose
private String profileImge;
@SerializedName("signatureurl")
@Expose
private String signatureurl;
@SerializedName("dependentDetail")
@Expose
private List<DependentDetails> dependentDetail = null;

public BankDetail getBankDetail() {
return bankDetail;
}

public void setBankDetail(BankDetail bankDetail) {
this.bankDetail = bankDetail;
}

public NomineeDetail getNomineeDetail() {
return nomineeDetail;
}

public void setNomineeDetail(NomineeDetail nomineeDetail) {
this.nomineeDetail = nomineeDetail;
}

public IdProofDetail getIdProofDetail() {
return idProofDetail;
}

public void setIdProofDetail(IdProofDetail idProofDetail) {
this.idProofDetail = idProofDetail;
}

public MemberFormInformation getMemberFormInformation() {
return memberFormInformation;
}

public void setMemberFormInformation(MemberFormInformation memberFormInformation) {
this.memberFormInformation = memberFormInformation;
}

public AssociateDetails getAssociateDetails() {
return associateDetails;
}

public void setAssociateDetails(AssociateDetails associateDetails) {
this.associateDetails = associateDetails;
}

public PersonalInformation getPersonalInformation() {
return personalInformation;
}

public void setPersonalInformation(PersonalInformation personalInformation) {
this.personalInformation = personalInformation;
}

public String getProfileImge() {
return profileImge;
}

public void setProfileImge(String profileImge) {
this.profileImge = profileImge;
}

public String getSignatureurl() {
return signatureurl;
}

public void setSignatureurl(String signatureurl) {
this.signatureurl = signatureurl;
}

public List<DependentDetails> getDependentDetail() {
return dependentDetail;
}

public void setDependentDetail(List<DependentDetails> dependentDetail) {
this.dependentDetail = dependentDetail;
}

}