package com.samraddhbestwin.microfinance.Response.Respones;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssociateDetailsMember {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("member_join_date")
@Expose
private String memberJoinDate;
@SerializedName("branch_code")
@Expose
private Integer branchCode;
@SerializedName("branch_name")
@Expose
private String branchName;
@SerializedName("sector_name")
@Expose
private String sectorName;
@SerializedName("member_id")
@Expose
private String memberId;
@SerializedName("associate_carder")
@Expose
private String associate_carder;

@SerializedName("associate_no")
@Expose
private String associateCode;
@SerializedName("name")
@Expose
private String associateName;
@SerializedName("address")
@Expose
private String address;
@SerializedName("firstId")
@Expose
private String firstId;
@SerializedName("secondId")
@Expose
private String secondId;
@SerializedName("status")
@Expose
private String status;

@SerializedName("nominee_name")
@Expose
private String nominee_name;

@SerializedName("relation")
@Expose
private String relation;

@SerializedName("nominee_age")
@Expose
private String nominee_age;

@SerializedName("email")
@Expose
private String email;

@SerializedName("mobile_no")
@Expose
private String mobile_no;

    public String getNominee_name() {
        return nominee_name;
    }

    public void setNominee_name(String nominee_name) {
        this.nominee_name = nominee_name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getNominee_age() {
        return nominee_age;
    }

    public void setNominee_age(String nominee_age) {
        this.nominee_age = nominee_age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getMemberJoinDate() {
return memberJoinDate;
}

public void setMemberJoinDate(String memberJoinDate) {
this.memberJoinDate = memberJoinDate;
}

public Integer getBranchCode() {
return branchCode;
}

public void setBranchCode(Integer branchCode) {
this.branchCode = branchCode;
}

public String getBranchName() {
return branchName;
}

public void setBranchName(String branchName) {
this.branchName = branchName;
}

public String getSectorName() {
return sectorName;
}

public void setSectorName(String sectorName) {
this.sectorName = sectorName;
}

public String getMemberId() {
return memberId;
}

public void setMemberId(String memberId) {
this.memberId = memberId;
}

/*public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}*/

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

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getFirstId() {
return firstId;
}

public void setFirstId(String firstId) {
this.firstId = firstId;
}

public String getSecondId() {
return secondId;
}

public void setSecondId(String secondId) {
this.secondId = secondId;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

    public String getAssociate_carder() {
        return associate_carder;
    }

    public void setAssociate_carder(String associate_carder) {
        this.associate_carder = associate_carder;
    }
}