package com.samraddhbestwin.microfinance.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.samraddhbestwin.microfinance.R;
import com.samraddhbestwin.microfinance.Response.LoanListResponse;

import java.util.ArrayList;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.ViewHolder> {
    Context mContext;
    ArrayList<LoanListResponse.LoanListData> accountList;
    String ListType;
    LoanListener loanListener;

    public interface LoanListener {
        void onLoanTransaction(String investment_id);
        void onLoanSSBPayment(String loan_id, String emiAmount);
    }


    public LoanAdapter(Context context, ArrayList<LoanListResponse.LoanListData> accountlist, String type, LoanListener listener) {
        this.mContext = context;
        this.accountList = accountlist;
        this.ListType = type;
        this.loanListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_loan_account, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoanListResponse.LoanListData loanListData = accountList.get(position);
//        holder.loanAccountNumberTextView.setText(loanListData.getAccountNumber());
        holder.loanSerialNumberTextView.setText("#" + (position + 1));
        holder.loanPlanNameTextView.setText(loanListData.getPlanName());
        holder.loanAmountTextView.setText(loanListData.getLoanAmount());

        if (loanListData.getIsRunningLoan().equalsIgnoreCase("1")) {
            holder.accountNoTitleTextView.setText("A/C No. : ");
            holder.loanAccountNumberTextView.setText(loanListData.getAccountNumber());
            holder.runningLoanConstraintLayout.setVisibility(View.VISIBLE);
            holder.nonrunningLoanConstraintLayout.setVisibility(View.GONE);
            holder.loanIssueDateTextView.setText(loanListData.getIssueDate());
            holder.loanInstalmentTextView.setText(loanListData.getInstalment() + "/" + loanListData.getMode());
            holder.loanEmiAmountTextView.setText(loanListData.getEmiAmount());
            holder.loanEmiPaidTextView.setText(loanListData.getEmiPaid());
        } else {
            holder.runningLoanConstraintLayout.setVisibility(View.GONE);
            holder.nonrunningLoanConstraintLayout.setVisibility(View.VISIBLE);

            if(loanListData.getLoanStatus().equalsIgnoreCase("0") || loanListData.getLoanStatus().equalsIgnoreCase("1")){
                holder.accountNoTitleTextView.setText("Application No. : ");
                holder.loanAccountNumberTextView.setText(loanListData.getApplicationNo());
            }else {
                holder.accountNoTitleTextView.setText("A/C No. : ");
                holder.loanAccountNumberTextView.setText(loanListData.getAccountNumber());
            }

            holder.loanApplicationDateTextView.setText(loanListData.getApplicationDate());
            holder.loanInstalmentModeTextView.setText(loanListData.getInstalment() + "/" + loanListData.getMode());
            if (loanListData.getLoanStatus().equalsIgnoreCase("0")) {
                holder.loanStatusTextView.setText("Pending");
            } else if (loanListData.getLoanStatus().equalsIgnoreCase("1")) {
                holder.loanStatusTextView.setText("Approve");
            } else if (loanListData.getLoanStatus().equalsIgnoreCase("2")) {
                holder.loanStatusTextView.setText("Reject");
            } else if (loanListData.getLoanStatus().equalsIgnoreCase("3")) {
                holder.loanStatusTextView.setText("Clear");
            } else if (loanListData.getLoanStatus().equalsIgnoreCase("4")) {
                holder.loanStatusTextView.setText("Due");
            }
        }


//        if (loanListData.getLoanStatus().equals("4")) {
//            holder.loanSSBPaymentButton.setVisibility(View.VISIBLE);
//            holder.loanUPIPaymentButton.setVisibility(View.VISIBLE);
//        } else {
//            holder.loanSSBPaymentButton.setVisibility(View.GONE);
//            holder.loanUPIPaymentButton.setVisibility(View.GONE);
//        }

        holder.loanTransactionButton.setOnClickListener(view -> {
            loanListener.onLoanTransaction(loanListData.getInvestmentId());
        });

        holder.loanSSBPaymentButton.setOnClickListener(view -> {
            loanListener.onLoanSSBPayment(loanListData.getInvestmentId(), loanListData.getEmiAmount());
        });
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView loanAccountNumberTextView, loanSerialNumberTextView, loanPlanNameTextView, loanAmountTextView;
        TextView loanIssueDateTextView, loanInstalmentTextView, loanEmiAmountTextView, loanEmiPaidTextView;

        TextView loanApplicationDateTextView, loanInstalmentModeTextView, loanStatusTextView;
        Button loanSSBPaymentButton, loanUPIPaymentButton, loanTransactionButton;
        ConstraintLayout runningLoanConstraintLayout, nonrunningLoanConstraintLayout;
        TextView accountNoTitleTextView;
        ViewHolder(View itemView) {
            super(itemView);
            loanAccountNumberTextView = itemView.findViewById(R.id.txt_loan_accountNo);
            loanSerialNumberTextView = itemView.findViewById(R.id.txt_loan_serial_no);
            loanPlanNameTextView = itemView.findViewById(R.id.txt_loan_plan_name);
            loanAmountTextView = itemView.findViewById(R.id.txt_loan_amount_name);

            loanIssueDateTextView = itemView.findViewById(R.id.txt_loan_issue_date_name);
            loanInstalmentTextView = itemView.findViewById(R.id.txt_loan_instalment_name);
            loanEmiAmountTextView = itemView.findViewById(R.id.txt_loan_emi_amount_name);
            loanEmiPaidTextView = itemView.findViewById(R.id.txt_loan_emi_paid_name);

            loanApplicationDateTextView = itemView.findViewById(R.id.txt_loan_application_date_name);
            loanInstalmentModeTextView = itemView.findViewById(R.id.txt_loan_instalment_mode_name);
            loanStatusTextView = itemView.findViewById(R.id.txt_loan_status_name);

            loanSSBPaymentButton = itemView.findViewById(R.id.btn_ssb_payment);
            loanUPIPaymentButton = itemView.findViewById(R.id.btn_upi_payment);
            loanTransactionButton = itemView.findViewById(R.id.btn_loan_transaction);

            runningLoanConstraintLayout = itemView.findViewById(R.id.cl_loan_running);
            nonrunningLoanConstraintLayout = itemView.findViewById(R.id.cl_loan_status);

            accountNoTitleTextView = itemView.findViewById(R.id.txt_loan_account_title);
        }

    }
}
