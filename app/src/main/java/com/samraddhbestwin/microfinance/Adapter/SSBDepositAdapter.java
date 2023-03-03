package com.samraddhbestwin.microfinance.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.samraddhbestwin.microfinance.Fragment.MyTranscationListFragment;
import com.samraddhbestwin.microfinance.Fragment.SSBAccountFragment;
import com.samraddhbestwin.microfinance.R;
import com.samraddhbestwin.microfinance.Response.SSBDepositResponse;

import java.util.ArrayList;

public class SSBDepositAdapter extends RecyclerView.Adapter<SSBDepositAdapter.ViewHolder>{
    Context mContext;
    ArrayList<SSBDepositResponse.SSBDepositData> ssbAccountList;
    String ListType;

    SSBDepositListener ssbDepositListener;
    public interface SSBDepositListener {
        void onSSBAccountTransaction(String investment_id);
        void onInvestAccountTransaction(String investment_id, String type, int position);
    }

    public SSBDepositAdapter(Context context, ArrayList<SSBDepositResponse.SSBDepositData> accountList, String type, SSBDepositListener listener) {
        this.mContext = context;
        this.ssbAccountList = accountList;
        this.ListType = type;
        this.ssbDepositListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(mContext).inflate(R.layout.adapter_ssb_deposit_account, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SSBDepositResponse.SSBDepositData ssbDepositData = ssbAccountList.get(position);

        if (ListType.equalsIgnoreCase("SSB")){
          holder.ssbConstraintLayout.setVisibility(View.VISIBLE);
          holder.investConstraintLayout.setVisibility(View.GONE);
          holder.ssbSrNoTextView.setText("#"+(position+1));
          holder.ssbAccountNoTextView.setText(ssbDepositData.getAccountNumber());
          holder.ssbPlanNameTextView.setText(ssbDepositData.getPlan());
          holder.ssbOpeningDateTextView.setText(ssbDepositData.getOpeningDate());
          holder.ssbTotalDepositTextView.setText(ssbDepositData.getDeposit());
          holder.ssbDenoAmountTextView.setText(ssbDepositData.getDenoAmount());
        }else {
            holder.ssbConstraintLayout.setVisibility(View.GONE);
            holder.investConstraintLayout.setVisibility(View.VISIBLE);
            holder.investSrNoTextView.setText("#"+(position+1));
            holder.investAccountNoTextView.setText(ssbDepositData.getAccountNumber());
            holder.investPlanNameTextView.setText(ssbDepositData.getPlan());
            holder.investDenoAmountTextView.setText(ssbDepositData.getDenoAmount());
            holder.investOpeningDateTextView.setText(ssbDepositData.getOpeningDate());
            holder.investMaturityDateTextView.setText(ssbDepositData.getMaturityDate());
            holder.investTotalDepositTextView.setText(ssbDepositData.getDeposit());
            holder.investDueAmountTextView.setText(ssbDepositData.getDueAmount()); // Pending Due Amount

            if(ssbDepositData.getIsTransactionAvailable() == 1){
                holder.investPaySSBButton.setVisibility(View.VISIBLE);
//                holder.investPayUPIButton.setVisibility(View.INVISIBLE);
            }else {
                holder.investPaySSBButton.setVisibility(View.INVISIBLE);
                holder.investPayUPIButton.setVisibility(View.GONE);
            }

        }

        holder.ssbTransactionButton.setOnClickListener(view -> {
            ssbDepositListener.onSSBAccountTransaction(ssbDepositData.getInvestmentId());
        });

        holder.investPaySSBButton.setOnClickListener(view -> {
            ssbDepositListener.onInvestAccountTransaction(ssbDepositData.getInvestmentId(), "SSB", position);
        });
        holder.investPayUPIButton.setOnClickListener(view -> {
            ssbDepositListener.onInvestAccountTransaction(ssbDepositData.getInvestmentId(), "UPI", position);
        });
        holder.investTransactionButton.setOnClickListener(view -> {
            ssbDepositListener.onInvestAccountTransaction(ssbDepositData.getInvestmentId(), "Transaction", position);
        });
    }

    @Override
    public int getItemCount() {
        return ssbAccountList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView ssbSrNoTextView, ssbAccountNoTextView, ssbPlanNameTextView, ssbOpeningDateTextView, ssbTotalDepositTextView, ssbDenoAmountTextView;
        ConstraintLayout ssbConstraintLayout, investConstraintLayout;
        Button ssbTransactionButton;
        Button investPaySSBButton, investPayUPIButton, investTransactionButton;
        TextView investSrNoTextView, investAccountNoTextView, investPlanNameTextView, investDenoAmountTextView, investOpeningDateTextView, investMaturityDateTextView, investTotalDepositTextView, investDueAmountTextView;


        ViewHolder(View itemView) {
            super(itemView);
            ssbSrNoTextView = itemView.findViewById(R.id.txt_serial_no);
            ssbAccountNoTextView = itemView.findViewById(R.id.txt_accountNo);
            ssbPlanNameTextView = itemView.findViewById(R.id.txt_plan_name);
            ssbOpeningDateTextView = itemView.findViewById(R.id.txt_account_opening_date);
            ssbTotalDepositTextView = itemView.findViewById(R.id.txt_ssb_total_deposit);
            ssbDenoAmountTextView = itemView.findViewById(R.id.txt_ssb_deno_amount);

            ssbConstraintLayout = itemView.findViewById(R.id.cl_ssb_account);
            investConstraintLayout = itemView.findViewById(R.id.cl_investment_account);

            ssbTransactionButton = itemView.findViewById(R.id.ssb_transaction);

            investPaySSBButton = itemView.findViewById(R.id.btn_invest_ssb_payment);
            investPayUPIButton = itemView.findViewById(R.id.btn_invest_upi_payment);
            investTransactionButton = itemView.findViewById(R.id.btn_invest_transaction);

            investSrNoTextView = itemView.findViewById(R.id.txt_invest_srno);
            investAccountNoTextView = itemView.findViewById(R.id.txt_invest_acc_no);
            investPlanNameTextView = itemView.findViewById(R.id.txt_invest_plan_name);
            investDenoAmountTextView = itemView.findViewById(R.id.txt_invest_deno_amount_name);
            investOpeningDateTextView = itemView.findViewById(R.id.txt_invest_opening_date_name);
            investMaturityDateTextView = itemView.findViewById(R.id.txt_invest_maturity_date_name);
            investTotalDepositTextView = itemView.findViewById(R.id.txt_invest_total_deposit);
            investDueAmountTextView = itemView.findViewById(R.id.txt_invest_due_amount);


        }
    }
}
