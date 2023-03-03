package com.samraddhbestwin.microfinance.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.samraddhbestwin.microfinance.Adapter.LoanAdapter;
import com.samraddhbestwin.microfinance.Adapter.SSBDepositAdapter;
import com.samraddhbestwin.microfinance.HomeActivity;
import com.samraddhbestwin.microfinance.R;
import com.samraddhbestwin.microfinance.Response.LoanListResponse;
import com.samraddhbestwin.microfinance.Response.PaymentOTPResponse;
import com.samraddhbestwin.microfinance.Response.SSBDepositResponse;
import com.samraddhbestwin.microfinance.Rest.RestHandler;
import com.samraddhbestwin.microfinance.Session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupLoanAccountFragment extends Fragment implements LoanAdapter.LoanListener {

    ProgressBar progressBar;
    RecyclerView groupLoanAccountRecyclerView;
    TextView noDataFoundTextView;
    LoanAdapter adapter;
    ArrayList<LoanListResponse.LoanListData> groupLoanAccountList =new ArrayList<>();
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    String member_id;
    SessionManager sessionManager;
    String currentBalance= "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_group_loan_account, container, false);
        ((HomeActivity)getActivity()).set_action_title(getString(R.string.g_loan_account_));
        ((HomeActivity)getActivity()).showNotificationIcon(true);
        progressBar = (ProgressBar)rootView.findViewById(R.id.spin_kit);
        UserData =((HomeActivity)getActivity()).getUserDataToSession(getActivity());
        groupLoanAccountRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_loan_account);
        noDataFoundTextView = (TextView) rootView.findViewById(R.id.tv_no_data_found);
        groupLoanAccountRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        groupLoanAccountRecyclerView.hasFixedSize();
        adapter = new LoanAdapter(this.getContext(), groupLoanAccountList, "Group Loan", GroupLoanAccountFragment.this);
        groupLoanAccountRecyclerView.setAdapter(adapter);
        member_id=UserData.get(SessionManager.KEY_member_id);
        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);

        getGroupLoanData();

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener((v, keyCode, event) -> {
            if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                requireActivity().getSupportFragmentManager().popBackStack();
                return true;
            }
            return false;
        });
        return rootView;
    }

    private void getGroupLoanData() {
        groupLoanAccountList.clear();
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "group_loan");
        Call<LoanListResponse> ssbDepositResponseCall = RestHandler.getApiService().LOAN_DATA_LIST(_member_id,_token, _type);
        ssbDepositResponseCall.enqueue(new Callback<LoanListResponse>() {
            @Override
            public void onResponse(Call<LoanListResponse> call, Response<LoanListResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if (response.body().getCode() == 200){
                            if (response.body().getData().size() > 0){
                                for (int i=0; i< response.body().getData().size(); i++){
                                    LoanListResponse.LoanListData loanListData = new LoanListResponse.LoanListData();
                                    loanListData.setAccountNumber(response.body().getData().get(i).getAccountNumber());
                                    loanListData.setPlanName(response.body().getData().get(i).getPlanName());
                                    loanListData.setEmiAmount(response.body().getData().get(i).getEmiAmount());
                                    loanListData.setInvestmentId(response.body().getData().get(i).getInvestmentId());
                                    loanListData.setLoanId(response.body().getData().get(i).getLoanId());
                                    loanListData.setLoanStatus(response.body().getData().get(i).getLoanStatus());
                                    loanListData.setLoanAmount(response.body().getData().get(i).getLoanAmount());
                                    loanListData.setIssueDate(response.body().getData().get(i).getIssueDate());
                                    loanListData.setInstalment(response.body().getData().get(i).getInstalment());
                                    loanListData.setMode(response.body().getData().get(i).getMode());
                                    loanListData.setEmiPaid(response.body().getData().get(i).getEmiPaid());
                                    loanListData.setApplicationDate(response.body().getData().get(i).getApplicationDate());
                                    loanListData.setIsRunningLoan(response.body().getData().get(i).getIsRunningLoan());
                                    loanListData.setApplicationNo(response.body().getData().get(i).getApplicationNo());
                                    groupLoanAccountList.add(loanListData);
                                }
                                noDataFoundTextView.setVisibility(View.GONE);
                                groupLoanAccountRecyclerView.setVisibility(View.VISIBLE);
                            }else {
                                noDataFoundTextView.setVisibility(View.VISIBLE);
                                groupLoanAccountRecyclerView.setVisibility(View.GONE);
                            }
                            currentBalance = response.body().getCurrentBalance();
                            adapter.notifyDataSetChanged();
                        }else {
                            noDataFoundTextView.setVisibility(View.VISIBLE);
                            groupLoanAccountRecyclerView.setVisibility(View.GONE);
                        }
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                    }else {
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                        noDataFoundTextView.setVisibility(View.VISIBLE);
                        groupLoanAccountRecyclerView.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }
            @Override
            public void onFailure(Call<LoanListResponse> call, Throwable t) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);
                noDataFoundTextView.setVisibility(View.VISIBLE);
                groupLoanAccountRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onLoanTransaction(String investment_id) {
        if (investment_id.equals("")){
            Toast.makeText(getActivity(), "No Transaction Found.", Toast.LENGTH_SHORT).show();
        }else{
            Fragment fragment = new MyTranscationListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("investment_id", investment_id);
            bundle.putString("type", "group_loan");
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
            fragmentTransaction1.addToBackStack(null);
            fragmentTransaction1.replace(R.id.container, fragment).commit();
        }
    }

    @Override
    public void onLoanSSBPayment(String account_no, String emiAmount) {
            if (Double.parseDouble(currentBalance) > Double.parseDouble(emiAmount)){
                progressBar.setVisibility(View.VISIBLE);
                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
                RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "loan");
                Call<PaymentOTPResponse> ssbDepositResponseCall = RestHandler.getApiService().PAYMENT_OTP(_member_id,_token, _type);
                ssbDepositResponseCall.enqueue(new Callback<PaymentOTPResponse>() {
                    @Override
                    public void onResponse(Call<PaymentOTPResponse> call, Response<PaymentOTPResponse> response) {
                        try {
                            if (response.isSuccessful()){
                                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                progressBar.setVisibility(View.GONE);
                                if (response.body().getCode() == 200){
                                    Toast.makeText(getActivity().getApplicationContext(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                    Fragment fragment = new PaymentOTPFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("loan_id", account_no);
                                    bundle.putString("emi_amount", emiAmount);
                                    bundle.putString("otp",response.body().getOtp());
                                    bundle.putString("type", "Group Loan");
                                    bundle.putString("payment_mode", "0");
                                    fragment.setArguments(bundle);
                                    FragmentManager fragmentManager = getFragmentManager();
                                    FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                                    fragmentTransaction1.replace(R.id.container, fragment).commit();
                                }else {
                                    Toast.makeText(getActivity().getApplicationContext(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getActivity().getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                progressBar.setVisibility(View.GONE);
                            }
                        }catch (Exception e){
                            progressBar.setVisibility(View.GONE);
                            e.printStackTrace();
                        }


                    }
                    @Override
                    public void onFailure(Call<PaymentOTPResponse> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }else {
                Toast.makeText(getActivity(), "Insufficient balance in your SSB account", Toast.LENGTH_SHORT).show();
            }
    }
}