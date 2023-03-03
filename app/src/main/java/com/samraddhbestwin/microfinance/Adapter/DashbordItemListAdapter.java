package com.samraddhbestwin.microfinance.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.samraddhbestwin.microfinance.Model.DashbordModel;
import com.samraddhbestwin.microfinance.R;


import java.util.ArrayList;

public class DashbordItemListAdapter extends RecyclerView.Adapter<DashbordItemListAdapter.ViewHolder>  {
    private LayoutInflater mInflater;
    private ArrayList<DashbordModel> mData;
    Context context;
    boolean flag = false;
    EventListener eventListener;
    String checkLayout;
    public interface EventListener {
        void onEvent(String investment_id);
    }
    public DashbordItemListAdapter(Context context, ArrayList<DashbordModel> data, EventListener eventListener) {
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
        view = mInflater.inflate(R.layout.dashbord_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

          viewHolder.sn.setText("#"+String.valueOf(i+1));
          viewHolder.createAt.setText(mData.get(i).getCreateAt());
        viewHolder.formNo.setText(mData.get(i).getFormNo());
        viewHolder.plan.setText(mData.get(i).getPlan());
        viewHolder.associte.setText(mData.get(i).getAccountNo());
        viewHolder.accountNo.setText(mData.get(i).getAccountNo());
        viewHolder.tenure.setText(mData.get(i).getTenure());
        viewHolder.balance.setText(mData.get(i).getBalance());
        viewHolder.deposite_amount.setText(mData.get(i).getDeposite_amount());

        viewHolder.viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListener.onEvent(mData.get(i).getInvetment_id());
            }
        });
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView sn;
        TextView createAt;
        TextView formNo;
        TextView plan;
        TextView associte;
        TextView accountNo;
        TextView tenure;
        TextView balance;
        TextView deposite_amount;
        CardView cardView;
        Button viewbtn;
        ViewHolder(View itemView) {
            super(itemView);
            sn=itemView.findViewById(R.id.textView15);
            createAt=itemView.findViewById(R.id.createattv);
            formNo=itemView.findViewById(R.id.formNoTv);
            plan=itemView.findViewById(R.id.plantv);
            associte=itemView.findViewById(R.id.associteCodeTv);
            accountNo=itemView.findViewById(R.id.accountNoTv);
            tenure=itemView.findViewById(R.id.tenureTv);
            balance=itemView.findViewById(R.id.balanceTv);
            deposite_amount=itemView.findViewById(R.id.depositeTv);
            cardView=itemView.findViewById(R.id.cardView);
            viewbtn=itemView.findViewById(R.id.viewbtn);
        }
    }
}
