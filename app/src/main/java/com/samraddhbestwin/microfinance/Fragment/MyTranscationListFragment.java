package com.samraddhbestwin.microfinance.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

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
import com.samraddhbestwin.microfinance.Response.TranscationListResponse;
import com.samraddhbestwin.microfinance.Rest.RestHandler;
import com.samraddhbestwin.microfinance.Session.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
public class MyTranscationListFragment extends Fragment implements MyTranscationListAdapter.EventListener{
    View RootView;
    ArrayList<MyTranscationModel> myTranscationModels =new ArrayList<>();
    MyTranscationListAdapter myTranscationListAdapter;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    HashMap<String ,String> userData=new HashMap<>();
    String member_id, investment_id;

    SessionManager sessionManager;
    HashMap<String ,String > UserDataToken;
    String token="", _type = "";
    TextView startHeaderTextView, MidHeaderTextView, EndHeaderTextView;
    TextView noDataFoundTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootView= inflater.inflate(R.layout.fragment_transcation_list, container, false);
        recyclerView=RootView.findViewById(R.id.recyclerview);
        progressBar = (ProgressBar)RootView.findViewById(R.id.spin_kit);

        startHeaderTextView = (TextView) RootView.findViewById(R.id.textView9);
        MidHeaderTextView = (TextView)RootView.findViewById(R.id.textView10);
        EndHeaderTextView = (TextView)RootView.findViewById(R.id.textView12);
        noDataFoundTextView = (TextView)RootView.findViewById(R.id.tv_no_data_found);

        ((HomeActivity)getActivity()).set_action_title(getString(R.string.transcationlist));
        ((HomeActivity)getActivity()).showNotificationIcon(true);

        userData = ((HomeActivity)getActivity()).getUserDataToSession(getActivity());

       member_id=userData.get(SessionManager.KEY_member_id);
        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);


        if (getArguments()!=null){
            investment_id =getArguments().getString("investment_id");
            _type =getArguments().getString("type");
        }


        if (_type.equals("loan") || _type.equals("group_loan")){
            startHeaderTextView.setText("Date");
            MidHeaderTextView.setText("Transaction ID");
            EndHeaderTextView.setText("Amount");
        }else {
            startHeaderTextView.setText("Date");
            MidHeaderTextView.setText("Amount");
            EndHeaderTextView.setText("Balance");
        }



        RootView.setFocusableInTouchMode(true);
        RootView.requestFocus();
        RootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    requireActivity().getSupportFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });
        getTransactionList(member_id, investment_id);
        return RootView;
    }
     public void getTransactionList(String member_id,String investment_id) {
        myTranscationModels.clear();
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
         RequestBody _investment_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(investment_id));
         RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
         RequestBody type = RequestBody.create(MediaType.parse("multipart/form-data"), _type);
        Call<TranscationListResponse> applicationsListResponesCall = RestHandler.getApiService().TRANSCATION_LIST_RESPONES_CALL(_member_id,_investment_id,_token, type);
        applicationsListResponesCall.enqueue(new Callback<TranscationListResponse>() {
            @Override
            public void onResponse(Call<TranscationListResponse> call, Response<TranscationListResponse> response) {
                try {
                    if (response!=null){
                        if (response.body().getCode() == 200){
                            List<TransactionListItem> transcationListResponses =response.body().getTranscationListResult().getTransactionListItems();
                            if(transcationListResponses.size() > 0){
                                for (TransactionListItem transactionListItem : transcationListResponses){
                                    String payment_date="",ChequeDdNo="",withdraw="",Deposit="",Balance="",PaymentType="",description="", emiDeposit="";
                                    if(transactionListItem.getCreatedAt() != null){
                                        payment_date=parseTime(transactionListItem.getCreatedAt());

                                    }
                                    if(transactionListItem.getChequeDdNo()!=null){
                                        ChequeDdNo=transactionListItem.getChequeDdNo();
                                    }
                                    if(transactionListItem.getWithdrawal()!=null){
                                        withdraw=transactionListItem.getWithdrawal();
                                    }
                                    if(transactionListItem.getDeposit()!=null){
                                        Deposit=transactionListItem.getDeposit();
                                    }
                                    if(transactionListItem.getOpeningBalance()!=null){
                                        Balance=transactionListItem.getOpeningBalance();
                                    }
                                    if(transactionListItem.getPaymentType()!=null){
                                        PaymentType=transactionListItem.getPaymentType();
                                    }
                                    if(transactionListItem.getDescription()!=null){
                                        description=transactionListItem.getDescription();
                                    }
                                    if (_type.equals("loan") || _type.equals("group_loan")){
                                        emiDeposit = transactionListItem.getEmiDeposit();
                                    }


                                    myTranscationModels.add(new MyTranscationModel(
                                            investment_id,
                                            transactionListItem.getId().toString(),
                                            payment_date,
                                            description,
                                            ChequeDdNo,
                                            withdraw,
                                            Deposit,
                                            Balance,
                                            PaymentType,emiDeposit, _type));
                                }
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                myTranscationListAdapter = new MyTranscationListAdapter(getActivity(), myTranscationModels, MyTranscationListFragment.this);
                                recyclerView.setAdapter(myTranscationListAdapter);
                                recyclerView.setVisibility(View.VISIBLE);
                                noDataFoundTextView.setVisibility(View.GONE);
                            }else{
                                noDataFoundTextView.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }
                            progressBar.setVisibility(View.GONE);
                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        }else {
                            noDataFoundTextView.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        noDataFoundTextView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
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
            public void onFailure(Call<TranscationListResponse> call, Throwable t) {
                noDataFoundTextView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });
    }
    @Override
    public void onEvent(String investment_id,String transcation_id) {
         Fragment  fragment = new TranscationDetailsFragment();
        Bundle bundle=new Bundle();
        bundle.putString("investment_id",investment_id);
        bundle.putString("transcation_id",transcation_id);
        bundle.putString("type",_type);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.addToBackStack("transcationlist");
        fragmentTransaction1.replace(R.id.container, fragment).commit();
    }
    public String parseTime(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 =null;
              date1 = format.parse(date.replace("T"," "));
            String d="";
             d= new SimpleDateFormat("dd/MM/yyyy").format(date1);
            Log.e("Dateeeeee"," "+d);
            return d;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}