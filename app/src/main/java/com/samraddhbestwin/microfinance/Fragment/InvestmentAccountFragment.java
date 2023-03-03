package com.samraddhbestwin.microfinance.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.samraddhbestwin.microfinance.Adapter.SSBDepositAdapter;
import com.samraddhbestwin.microfinance.HomeActivity;
import com.samraddhbestwin.microfinance.R;
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

public class InvestmentAccountFragment extends Fragment implements SSBDepositAdapter.SSBDepositListener {

    ProgressBar progressBar;
    RecyclerView depositAccountRecyclerView;
    TextView noDataFoundTextView;
    SSBDepositAdapter adapter;
    ArrayList<SSBDepositResponse.SSBDepositData> depositAccountList = new ArrayList<>();
    HashMap<String, String> UserDataToken;
    String token = "";
    HashMap<String, String> UserData;
    String member_id;
    SessionManager sessionManager;
    String currentBalance = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_deposit_account, container, false);
        ((HomeActivity) getActivity()).set_action_title(getString(R.string.deposit_account_));
        ((HomeActivity) getActivity()).showNotificationIcon(true);
        progressBar = (ProgressBar) rootView.findViewById(R.id.spin_kit);
        depositAccountRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_deposit_account);
        noDataFoundTextView = (TextView) rootView.findViewById(R.id.tv_no_data_found);
        depositAccountRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        depositAccountRecyclerView.hasFixedSize();
        adapter = new SSBDepositAdapter(this.getContext(), depositAccountList, "Deposit", InvestmentAccountFragment.this);
        depositAccountRecyclerView.setAdapter(adapter);
        UserData = ((HomeActivity) getActivity()).getUserDataToSession(getActivity());
        member_id = UserData.get(SessionManager.KEY_member_id);
        sessionManager = new SessionManager(getActivity());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);
        getDepositAccountListData();

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                requireActivity().getSupportFragmentManager().popBackStack();
                return true;
            }
            return false;
        });
        return rootView;
    }

    private void getDepositAccountListData() {
        depositAccountList.clear();
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "deposit");
        Call<SSBDepositResponse> ssbDepositResponseCall = RestHandler.getApiService().SSB_DEPOSIT_LIST(_member_id, _token, _type);
        ssbDepositResponseCall.enqueue(new Callback<SSBDepositResponse>() {
            @Override
            public void onResponse(Call<SSBDepositResponse> call, Response<SSBDepositResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            if (response.body().getData().size() > 0) {
                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    SSBDepositResponse.SSBDepositData ssbDepositData = new SSBDepositResponse.SSBDepositData();
                                    ssbDepositData.setInvestmentId(response.body().getData().get(i).getInvestmentId());
                                    ssbDepositData.setAccountNumber(response.body().getData().get(i).getAccountNumber());
                                    ssbDepositData.setDenoAmount(response.body().getData().get(i).getDenoAmount());
                                    ssbDepositData.setMaturityDate(response.body().getData().get(i).getMaturityDate());
                                    ssbDepositData.setDeposit(response.body().getData().get(i).getDeposit());
                                    ssbDepositData.setOpeningDate(response.body().getData().get(i).getOpeningDate());
                                    ssbDepositData.setPlan(response.body().getData().get(i).getPlan());
                                    ssbDepositData.setDueAmount(response.body().getData().get(i).getDueAmount());
                                    ssbDepositData.setIsTransactionAvailable(response.body().getData().get(i).getIsTransactionAvailable());
                                    depositAccountList.add(ssbDepositData);
                                }
                                noDataFoundTextView.setVisibility(View.GONE);
                                depositAccountRecyclerView.setVisibility(View.VISIBLE);
                            } else {
                                noDataFoundTextView.setVisibility(View.VISIBLE);
                                depositAccountRecyclerView.setVisibility(View.GONE);
                            }
                            adapter.notifyDataSetChanged();
                            currentBalance = response.body().getCurrentBalance();
                        } else {
                            noDataFoundTextView.setVisibility(View.VISIBLE);
                            depositAccountRecyclerView.setVisibility(View.GONE);
                        }
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                    } else {
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                        noDataFoundTextView.setVisibility(View.VISIBLE);
                        depositAccountRecyclerView.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SSBDepositResponse> call, Throwable t) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);
                noDataFoundTextView.setVisibility(View.VISIBLE);
                depositAccountRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onSSBAccountTransaction(String investment_id) {
    }

    @Override
    public void onInvestAccountTransaction(String investment_id, String type, int position) {
        if (type.equalsIgnoreCase("Transaction")) {
            Fragment fragment = new MyTranscationListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("investment_id", investment_id);
            bundle.putString("type", "deposit");
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
            fragmentTransaction1.addToBackStack(null);
            fragmentTransaction1.replace(R.id.container, fragment).commit();
        } else if (type.equalsIgnoreCase("SSB")) {
            final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity(), R.style.AlertDialog);
            View mView = getLayoutInflater().inflate(R.layout.investment_payment_dialog, null);
            alert.setView(mView);
            ImageView cancelDialog = mView.findViewById(R.id.iv_cancel_dialog);
            EditText amountEditText = mView.findViewById(R.id.edt_inv_deposit_amount);
            TextView accountNoTextView = mView.findViewById(R.id.inv_acc_no);
            accountNoTextView.setText(depositAccountList.get(position).getAccountNumber());
            TextView totalDepositTextView = mView.findViewById(R.id.inv_total_deposit);
            totalDepositTextView.setText(depositAccountList.get(position).getDeposit());
            TextView dueAmountTextView = mView.findViewById(R.id.inv_due_amount_no);
            dueAmountTextView.setText(depositAccountList.get(position).getDueAmount());
            TextView currentBalanceTextView = mView.findViewById(R.id.inv_current_amount_no);
            currentBalanceTextView.setText(currentBalance);

            Button submitAmount = mView.findViewById(R.id.btn_submit_inv_payment);
            final AlertDialog alertDialog = alert.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
            alertDialog.show();


            amountEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().length() == 1 && charSequence.toString().equals(".")) {
                        amountEditText.setText("");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            cancelDialog.setOnClickListener(view12 -> {
                alertDialog.dismiss();
            });
            submitAmount.setOnClickListener(view -> {
                if (!amountEditText.getText().toString().equalsIgnoreCase("")) {
                    if (Double.parseDouble(amountEditText.getText().toString()) > 0.0) {
                        if (Double.parseDouble(currentBalance) > Double.parseDouble(amountEditText.getText().toString())) {
                            alertDialog.dismiss();
                            progressBar.setVisibility(View.VISIBLE);
                            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
                            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                            RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "deposit");
                            Call<PaymentOTPResponse> ssbDepositResponseCall = RestHandler.getApiService().PAYMENT_OTP(_member_id, _token, _type);
                            ssbDepositResponseCall.enqueue(new Callback<PaymentOTPResponse>() {
                                @Override
                                public void onResponse(Call<PaymentOTPResponse> call, Response<PaymentOTPResponse> response) {
                                    try {
                                        if (response.isSuccessful()) {
                                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                            progressBar.setVisibility(View.GONE);
                                            if (response.body().getCode() == 200) {
                                                Toast.makeText(getActivity().getApplicationContext(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                                Fragment fragment = new PaymentOTPFragment();
                                                Bundle bundle = new Bundle();
                                                bundle.putString("loan_id", investment_id);
                                                bundle.putString("emi_amount", amountEditText.getText().toString());
                                                bundle.putString("otp", response.body().getOtp());
                                                bundle.putString("type", "Deposit");
                                                bundle.putString("payment_mode", "0");
                                                fragment.setArguments(bundle);
                                                FragmentManager fragmentManager = getFragmentManager();
                                                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                                                fragmentTransaction1.replace(R.id.container, fragment).commit();
                                            } else {
                                                Toast.makeText(getActivity().getApplicationContext(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(getActivity().getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    } catch (Exception e) {
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
                        } else {
                            Toast.makeText(getActivity(), "Insufficient balance in your SSB account", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Deposit amount must be greater than 0", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter deposit amount", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}