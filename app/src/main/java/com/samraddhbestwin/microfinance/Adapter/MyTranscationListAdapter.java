package com.samraddhbestwin.microfinance.Adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samraddhbestwin.microfinance.Model.MyTranscationModel;
import com.samraddhbestwin.microfinance.R;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class MyTranscationListAdapter extends RecyclerView.Adapter<MyTranscationListAdapter.ViewHolder>  {
    private LayoutInflater mInflater;

    private ArrayList<MyTranscationModel> mData;
    Context context;
    boolean flag = false;
    EventListener eventListener;
    String checkLayout;
    public interface EventListener {
        void onEvent(String investment,String transcation_id);
    }
    public MyTranscationListAdapter(Context context, ArrayList<MyTranscationModel> data, EventListener eventListener) {
        if (context!=null) {
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
            this.eventListener = eventListener;
            this.checkLayout = checkLayout;
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=null;
        view = mInflater.inflate(R.layout.my_transcation_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {

        if (mData.get(i).getType().equals("loan") || mData.get(i).getType().equals("group_loan")){
            viewHolder.ssbDepositcardView.setVisibility(View.GONE);
            viewHolder.loanCardView.setVisibility(View.VISIBLE);
            viewHolder.loanDateTextView.setText(mData.get(i).getDate());
            viewHolder.loanAmountTextView.setText("\u20B9 "+  mData.get(i).getEmiDeposit());
            viewHolder.loanAmountTextView.setTextColor(context.getColor(R.color.buttonbackground));
            viewHolder.loanCaConstraintLayout.setBackgroundColor(Color.parseColor("#FFF6D0D0"));
            viewHolder.loanTransactionIdTextView.setText("#"+mData.get(i).getTranscatoinid());
            viewHolder.loanDescriptionTextView.setText(mData.get(i).getDescription());
        }else {
            viewHolder.ssbDepositcardView.setVisibility(View.VISIBLE);
            viewHolder.loanCardView.setVisibility(View.GONE);
            viewHolder.date.setText(mData.get(i).getDate());
            if(mData.get(i).getWithdrawal().equals("0.00") || mData.get(i).getWithdrawal().equals(null)){
                viewHolder.amount.setText("\u20B9 "+  mData.get(i).getDeposite() + "/"+mData.get(i).getPayment_type());
                viewHolder.amount.setTextColor(context.getColor(R.color.buttonbackground));
                viewHolder.cardid.setBackgroundColor(Color.parseColor("#FFF6D0D0"));
            }else{
                viewHolder.amount.setText("\u20B9 "+  mData.get(i).getWithdrawal() + "/"+mData.get(i).getPayment_type());
                viewHolder.amount.setTextColor(context.getColor(R.color.red));
                viewHolder.cardid.setBackgroundColor(Color.parseColor("#FFD2F1FA"));
            }
            viewHolder.balcace.setText("\u20B9 "+ mData.get(i).getBalance());
            viewHolder.description.setText(mData.get(i).getDescription());
            viewHolder.transcationid.setText("#"+mData.get(i).getTranscatoinid());
        }



        viewHolder.ssbDepositcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListener.onEvent(mData.get(i).getInvestmentid(),mData.get(i).getTranscatoinid());
            }
        });
        viewHolder.loanCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListener.onEvent(mData.get(i).getInvestmentid(),mData.get(i).getTranscatoinid());
            }
        });

    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView  date,amount,balcace,transcationid,description;
        ConstraintLayout cardid, loanCaConstraintLayout;
        CardView ssbDepositcardView, loanCardView;
        TextView loanDateTextView, loanTransactionIdTextView, loanAmountTextView, loanDescriptionTextView;
        ViewHolder(View itemView) {
            super(itemView);
            cardid=itemView.findViewById(R.id.cardid);
            ssbDepositcardView=itemView.findViewById(R.id.cardView1);
            date=itemView.findViewById(R.id.date);
            amount=itemView.findViewById(R.id.amount);
            balcace=itemView.findViewById(R.id.balcace);
            transcationid=itemView.findViewById(R.id.transcationid);
            description=itemView.findViewById(R.id.description);

            loanCardView=itemView.findViewById(R.id.cv_loan);
            loanDateTextView=itemView.findViewById(R.id.loan_date);
            loanTransactionIdTextView=itemView.findViewById(R.id.txt_transaction_id);
            loanAmountTextView=itemView.findViewById(R.id.tv_amount);
            loanDescriptionTextView=itemView.findViewById(R.id.tv_nuration);
            loanCaConstraintLayout=itemView.findViewById(R.id.loan_cardid);
        }
    }
}
