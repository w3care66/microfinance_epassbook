package com.samraddhbestwin.microfinance.Response;


import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Investment {

    @SerializedName("investments")
    @Expose
    private List<InvestmentItem> investmentsitem = null;

    public List<InvestmentItem> getInvestmentsitem() {
        return investmentsitem;
    }

    public void setInvestmentsitem(List<InvestmentItem> investmentsitem) {
        this.investmentsitem = investmentsitem;
    }

}