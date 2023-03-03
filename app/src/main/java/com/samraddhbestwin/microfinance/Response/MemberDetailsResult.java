package com.samraddhbestwin.microfinance.Response;

import com.samraddhbestwin.microfinance.Response.BankDetail;

import com.samraddhbestwin.microfinance.Response.MemberDetail;
import com.samraddhbestwin.microfinance.Response.NomineeDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MemberDetailsResult {

    @SerializedName("account_numbers")
    @Expose
    private List<String> accountNumbers = null;
    @SerializedName("memberDetail")
    @Expose
    private MemberDetail memberDetail;
    @SerializedName("bankDetail")
    @Expose
    private BankDetail bankDetail;
    @SerializedName("nomineeDetail")
    @Expose
    private NomineeDetail nomineeDetail;
    @SerializedName("idProofDetail")
    @Expose
    private IdProofDetail idProofDetail;

    public List<String> getAccountNumbers() {
        return accountNumbers;
    }

    public void setAccountNumbers(List<String> accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

    public MemberDetail getMemberDetail() {
        return memberDetail;
    }

    public void setMemberDetail(MemberDetail memberDetail) {
        this.memberDetail = memberDetail;
    }

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

}