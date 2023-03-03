package com.samraddhbestwin.microfinance.Response.Respones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class ResultAssociateDetailsView {
@SerializedName("idProofDetail")
@Expose
private IdProofDetail idProofDetail;
@SerializedName("associate_form_information")
@Expose
private AssociateFormInformation associateFormInformation;
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
@SerializedName("guarantorDetail")
@Expose
private GuarantorDetail guarantorDetail;
@SerializedName("dependentDetail")
@Expose
private List<DependentDetail> dependentDetail = null;

public IdProofDetail getIdProofDetail() {
return idProofDetail;
}

public void setIdProofDetail(IdProofDetail idProofDetail) {
this.idProofDetail = idProofDetail;
}

public AssociateFormInformation getAssociateFormInformation() {
return associateFormInformation;
}

public void setAssociateFormInformation(AssociateFormInformation associateFormInformation) {
this.associateFormInformation = associateFormInformation;
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

public GuarantorDetail getGuarantorDetail() {
return guarantorDetail;
}

public void setGuarantorDetail(GuarantorDetail guarantorDetail) {
this.guarantorDetail = guarantorDetail;
}

public List<DependentDetail> getDependentDetail() {
return dependentDetail;
}

public void setDependentDetail(List<DependentDetail> dependentDetail) {
this.dependentDetail = dependentDetail;
}

}