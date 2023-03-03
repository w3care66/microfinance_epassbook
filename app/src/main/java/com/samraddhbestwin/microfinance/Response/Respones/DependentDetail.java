package com.samraddhbestwin.microfinance.Response.Respones;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class DependentDetail {
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("dependent_type")
@Expose
private String dependentType;
@SerializedName("gender")
@Expose
private String gender;
@SerializedName("relation")
@Expose
private String relation;
@SerializedName("marital_status")
@Expose
private String maritalStatus;
@SerializedName("living_with_associate")
@Expose
private String livingWithAssociate;
@SerializedName("monthly_income")
@Expose
private String monthlyIncome;

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

public String getDependentType() {
return dependentType;
}

public void setDependentType(String dependentType) {
this.dependentType = dependentType;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public String getRelation() {
return relation;
}

public void setRelation(String relation) {
this.relation = relation;
}

public String getMaritalStatus() {
return maritalStatus;
}

public void setMaritalStatus(String maritalStatus) {
this.maritalStatus = maritalStatus;
}

public String getLivingWithAssociate() {
return livingWithAssociate;
}

public void setLivingWithAssociate(String livingWithAssociate) {
this.livingWithAssociate = livingWithAssociate;
}

public String getMonthlyIncome() {
return monthlyIncome;
}

public void setMonthlyIncome(String monthlyIncome) {
this.monthlyIncome = monthlyIncome;
}

}