package com.samraddhbestwin.microfinance.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.samraddhbestwin.microfinance.Adapter.MyTranscationListAdapter;
import com.samraddhbestwin.microfinance.HomeActivity;
import com.samraddhbestwin.microfinance.Model.MyTranscationModel;
import com.samraddhbestwin.microfinance.R;
import com.samraddhbestwin.microfinance.Response.TransactionListItem;
import com.samraddhbestwin.microfinance.Response.TranscationDetailsResponse;
import com.samraddhbestwin.microfinance.Response.TranscationListResponse;
import com.samraddhbestwin.microfinance.Rest.RestHandler;
import com.samraddhbestwin.microfinance.Session.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class TranscationDetailsFragment extends Fragment {


 View view;
 String member_id,investment_id,transcation_id;
    ProgressBar progressBar;
    SessionManager sessionManager;
    HashMap<String ,String > UserData;

    HashMap<String ,String > UserDataToken;
    String token="", Type = "";
 TextView totalAmountTv,deoamounttext,paymentTypeTv,nameTv,accountNoTv,date,deoamount,transcationIdTv,associteNameTv,associateNoTv,sbAmountTv,description;
 TextView accountBalanceTitleTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_transcation_details, container, false);
        ((HomeActivity)getActivity()).set_action_title(getString(R.string.transcationdetails));
//        ((HomeActivity)getActivity()).set_action_image(R.drawable.arrow,"back" ,getActivity(),"transcationlist");
        ((HomeActivity)getActivity()).showNotificationIcon(true);
        progressBar = (ProgressBar)view.findViewById(R.id.spin_kit);
        totalAmountTv=(TextView)view.findViewById(R.id.totalAmountTv);
        paymentTypeTv=(TextView)view.findViewById(R.id.paymentTypeTv);
        nameTv=(TextView)view.findViewById(R.id.nameTv);
        accountNoTv=(TextView)view.findViewById(R.id.accountNoTv);
        date=(TextView)view.findViewById(R.id.date);
        deoamount=(TextView)view.findViewById(R.id.deoamount);
        deoamounttext=(TextView)view.findViewById(R.id.textView3);
        transcationIdTv=(TextView)view.findViewById(R.id.transcationIdTv);
        associteNameTv=(TextView)view.findViewById(R.id.associteNameTv);
        associateNoTv=(TextView)view.findViewById(R.id.associateNoTv);
        sbAmountTv=(TextView)view.findViewById(R.id.sbAmountTv);
        description=(TextView)view.findViewById(R.id.description);
        accountBalanceTitleTextView=(TextView)view.findViewById(R.id.tx_acc_balance_title);
        UserData =((HomeActivity)getActivity()).getUserDataToSession(getActivity());
        member_id=UserData.get(sessionManager.KEY_member_id);
        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);

        if (getArguments()!=null){
            investment_id = getArguments().getString("investment_id");
            transcation_id = getArguments().getString("transcation_id");
            Type = getArguments().getString("type");
        }

        if (Type.equals("loan") || Type.equals("group_loan")){
            accountBalanceTitleTextView.setVisibility(View.GONE);
        }else {
            accountBalanceTitleTextView.setVisibility(View.VISIBLE);
        }


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    requireActivity().getSupportFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });
        getTranscationDetails(member_id,investment_id,transcation_id);
        return view;


    }

    private void getTranscationDetails(String member_id,String investment_id,String transcation_id) {

        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _investment_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(investment_id));
        RequestBody _transcation_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(transcation_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), Type);

        Call<TranscationDetailsResponse> applicationsListResponesCall = RestHandler.getApiService().TRANSCATION_DETAILS_RESPONES_CALL(_member_id,_investment_id,_transcation_id,_token, _type);
        applicationsListResponesCall.enqueue(new Callback<TranscationDetailsResponse>() {
            @Override
            public void onResponse(Call<TranscationDetailsResponse> call, Response<TranscationDetailsResponse> response) {

                try {
                    if (response!=null){
                        if (response.body().getCode() == 200){

                            if(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getDeposit()!=null) {
                                if (Double.parseDouble(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getDeposit()) > 0.00){
                                    totalAmountTv.setText("\u20B9 " + response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getDeposit().toString());
                                }else {
                                    totalAmountTv.setText("\u20B9 " + response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getWithdrawal().toString());
                                }
                            }else {
                                totalAmountTv.setText("\u20B9 " + response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getWithdrawal().toString());
                            }

//                            if(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getAmount()!=null) {
//                                totalAmountTv.setText("\u20B9 " + response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getAmount().toString());
//                            }
                            if(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getUserName()!=null) {
                                paymentTypeTv.setText(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getUserName().toString());
                            }
                            nameTv.setText(member_id);
                            if(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getAccountNo()!=null) {
                                accountNoTv.setText("A/C:-" + response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getAccountNo().toString());
                            }
                            if(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getCreatedAt()!=null){
                                date.setText(parseTime(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getCreatedAt().toString()));
                            }
                            if(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getDue_amount()!=null) {
                                deoamount.setText("\u20B9 " +response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getDue_amount().toString());
                            }
                            if(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getId()!=null) {
                                transcationIdTv.setText("TID:-" + response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getId().toString());
                            }
                            if(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getAssociateName()!=null) {
                                associteNameTv.setText(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getAssociateName().toString());
                            }
                            if(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getAssociateCode()!=null) {
                                associateNoTv.setText(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getAssociateCode().toString());
                            }
                            if(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getOpeningBalance()!=null) {
                                sbAmountTv.setText("\u20B9 " + response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getOpeningBalance().toString());
                            }
                            if(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getDescription()!=null) {
                                description.setText(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getDescription().toString());
                            }
                            if(response.body().getTranscationDetailsResult().getTransactionListDetail().getTDetails().getPlan_id() == 1){
                                deoamount.setVisibility(View.GONE);
                                deoamounttext.setVisibility(View.GONE);
                            }else{
                                if (Type.equals("loan") || Type.equals("group_loan")){
                                    deoamount.setVisibility(View.GONE);
                                    deoamounttext.setVisibility(View.GONE);
                                }else {
                                    deoamounttext.setVisibility(View.VISIBLE);
                                }
                            }
                            progressBar.setVisibility(View.GONE);
                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        }else {
                            progressBar.setVisibility(View.GONE);
                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        progressBar.setVisibility(View.GONE);
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }



            @Override
            public void onFailure(Call<TranscationDetailsResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });

    }
    public String parseTime(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = format.parse(date.replace("T"," "));
            String d= new SimpleDateFormat("dd/MM/yyyy").format(date1);
            Log.e("Dateeeeee"," "+d);
            return d;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}