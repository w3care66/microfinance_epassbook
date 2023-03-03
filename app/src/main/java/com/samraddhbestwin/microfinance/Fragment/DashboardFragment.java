package com.samraddhbestwin.microfinance.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.samraddhbestwin.microfinance.Adapter.DashbordItemListAdapter;
import com.samraddhbestwin.microfinance.HomeActivity;
import com.samraddhbestwin.microfinance.LoginActivity;
import com.samraddhbestwin.microfinance.Model.DashbordModel;
import com.samraddhbestwin.microfinance.Model.SSBModelResponse;
import com.samraddhbestwin.microfinance.R;
import com.samraddhbestwin.microfinance.Response.DashBoardResponse;
import com.samraddhbestwin.microfinance.Response.Investment;
import com.samraddhbestwin.microfinance.Response.InvestmentItem;
import com.samraddhbestwin.microfinance.Response.MemberDetailsResponse;
import com.samraddhbestwin.microfinance.Response.Respones.CommonResponseWithoutData;
import com.samraddhbestwin.microfinance.Rest.RestHandler;
import com.samraddhbestwin.microfinance.Session.SessionManager;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    View RootView;
     SessionManager sessionManager;
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    String member_id,maxpaymentAmount ="", minPaymentAmount = "", maxRequestAmount = "";
    ProgressBar progressBar;
    TextView ssbAmountTextView, userNameTextView;
    Button sendPaymentToCard;
    Boolean isDebitCardButtonShow = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView= inflater.inflate(R.layout.fragment_dashboard, container, false);
        UserData =((HomeActivity)getActivity()).getUserDataToSession(getActivity());
        ((HomeActivity)getActivity()).set_action_title(getString(R.string.dashboard));
        ((HomeActivity)getActivity()).showNotificationIcon(true);
        progressBar = RootView.findViewById(R.id.spin_kit);
        member_id=UserData.get(SessionManager.KEY_member_id);
        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);

        getMemberDetails(member_id,token);

        ssbAmountTextView = RootView.findViewById(R.id.txt_ssb_amount);
        sendPaymentToCard = RootView.findViewById(R.id.btn_send_to_debitcard);
        userNameTextView = RootView.findViewById(R.id.txt_user_name);

        RootView.findViewById(R.id.card_ssb).setOnClickListener(view -> {
            ((HomeActivity)getActivity()).openNewFragment("SSB");
        });
        RootView.findViewById(R.id.card_deposit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).openNewFragment("Investment");
            }
        });
        RootView.findViewById(R.id.card_loan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).openNewFragment("Loan");
            }
        });
        RootView.findViewById(R.id.card_group_loan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).openNewFragment("Group Loan");
            }
        });

        sendPaymentToCard.setOnClickListener(view -> {
            if (isDebitCardButtonShow){
            final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity(), R.style.AlertDialog);
            View mView = getLayoutInflater().inflate(R.layout.dialog_payment_submit,null);
            alert.setView(mView);
            ImageView cancelDialog = mView.findViewById(R.id.iv_cancel_dialog);
            EditText amountEditText = mView.findViewById(R.id.edt_debit_amount);
            TextView maxAmountAlert = mView.findViewById(R.id.txt_max_amount);
            maxAmountAlert.setText("Your Max Amount for Deposit : "+maxpaymentAmount);
            Button submitAmount = mView.findViewById(R.id.btn_submit_payment);
            final AlertDialog alertDialog = alert.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
            alertDialog.show();

            amountEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.toString().length() == 1 && charSequence.toString().equals(".")){
                        amountEditText.setText("");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


            submitAmount.setOnClickListener(view1 -> {
                    if (!amountEditText.getText().toString().equalsIgnoreCase("")){
                        if (Double.parseDouble(amountEditText.getText().toString()) > 0.0){
                            if (Double.parseDouble(amountEditText.getText().toString()) <= Double.parseDouble(maxpaymentAmount)){
                                if (Double.parseDouble(amountEditText.getText().toString()) >= Double.parseDouble(minPaymentAmount)){
                                    if (Double.parseDouble(amountEditText.getText().toString()) <= Double.parseDouble(maxRequestAmount)){
                                        alertDialog.dismiss();
                                        submitDebitAmount(amountEditText.getText().toString());
                                    }else{
                                        Toast.makeText(getActivity().getApplicationContext(), "Max Debit amount for request is "+maxRequestAmount, Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(getActivity().getApplicationContext(), "Min debit amount is "+minPaymentAmount, Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getActivity().getApplicationContext(), "Max debit amount limit is "+maxpaymentAmount, Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(), "Transfer amount must be greater than 0", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity().getApplicationContext(), "Please enter debit amount", Toast.LENGTH_SHORT).show();
                    }
            });

            cancelDialog.setOnClickListener(view12 -> {
                alertDialog.dismiss();
            });
            }else {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setContentView(R.layout.dialog_success_failure);
                TextView body = dialog.findViewById(R.id.textview_message);
                body.setText("Your debit card not created.");
                ImageView typeImageView = dialog.findViewById(R.id.imageView);
                typeImageView.setImageResource(R.drawable.ic_failed);
                TextView messageTitle = dialog.findViewById(R.id.textview_title);
                messageTitle.setText("Failure..!!");
                TextView okButton = dialog.findViewById(R.id.okay_text);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        getDashBordData();
      return RootView;
    }

    private void submitDebitAmount(String amount) {
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _amount = RequestBody.create(MediaType.parse("multipart/form-data"), amount);
        Call<CommonResponseWithoutData> debitAmountCall = RestHandler.getApiService().DEBIT_AMOUNT(_member_id,_token, _amount);
        debitAmountCall.enqueue(new Callback<CommonResponseWithoutData>() {
            @Override
            public void onResponse(Call<CommonResponseWithoutData> call, Response<CommonResponseWithoutData> response) {
                try {
                    if (response.isSuccessful()){
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                        if (response.body().getCode() == 200){
                            final Dialog dialog = new Dialog(getActivity());
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(false);
                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                            dialog.setContentView(R.layout.dialog_success_failure);
                            TextView body = dialog.findViewById(R.id.textview_message);
                            body.setText(response.body().getMessage());
                            ImageView typeImageView = dialog.findViewById(R.id.imageView);
                            typeImageView.setImageResource(R.drawable.ic_success);
                            TextView messageTitle = dialog.findViewById(R.id.textview_title);
                            messageTitle.setText("Congratulations..!!");
                            TextView okButton = dialog.findViewById(R.id.okay_text);
                            okButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    getDashBordData();
                                }
                            });
                            dialog.show();
                        }else {
                            final Dialog dialog = new Dialog(getActivity());
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(false);
                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                            dialog.setContentView(R.layout.dialog_success_failure);
                            TextView body = dialog.findViewById(R.id.textview_message);
                            body.setText(response.body().getMessage());
                            ImageView typeImageView = dialog.findViewById(R.id.imageView);
                            typeImageView.setImageResource(R.drawable.ic_failed);
                            TextView messageTitle = dialog.findViewById(R.id.textview_title);
                            messageTitle.setText("Failure..!!");
                            TextView okButton = dialog.findViewById(R.id.okay_text);
                            okButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
//                    Toast.makeText(getActivity().getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity().getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<CommonResponseWithoutData> call, Throwable t) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity().getApplicationContext(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDashBordData() {
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<SSBModelResponse> ssbModelResponseCall = RestHandler.getApiService().SSB_AMOUNT_DETAILS(_member_id,_token);
        ssbModelResponseCall.enqueue(new Callback<SSBModelResponse>() {
            @Override
            public void onResponse(Call<SSBModelResponse> call, Response<SSBModelResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressBar.setVisibility(View.GONE);
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        if (response.body().getCode() == 200){
//                            isDebitCardButtonShow = response.body().getCardAvailable();
//                            if(isDebitCardButtonShow){
//                                sendPaymentToCard.setVisibility(View.VISIBLE);
//                            }else {
//                                sendPaymentToCard.setVisibility(View.INVISIBLE);
//                            }
                            ssbAmountTextView.setText(response.body().getData().getAmounts());
                            maxpaymentAmount = response.body().getData().getMaxAmount();
                            minPaymentAmount = response.body().getData().getMinRequestAmount();


//                            if (!response.body().getNotificationCount().equalsIgnoreCase("")){
//                                ((HomeActivity)getActivity()).notificationCountTextView.setText(response.body().getNotificationCount());
//                            }else {
//                                ((HomeActivity)getActivity()).notificationCountTextView.setText("0");
//                            }
                        }
                    }else {
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SSBModelResponse> call, Throwable t) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    void getMemberDetails(String member_id,String token) {

        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);

        Call<MemberDetailsResponse> applicationsListResponesCall = RestHandler.getApiService().MEMBER_DETAILS_RESPONES_CALL(_member_id,_token);
        applicationsListResponesCall.enqueue(new Callback<MemberDetailsResponse>() {
            @Override
            public void onResponse(Call<MemberDetailsResponse> call, Response<MemberDetailsResponse> response) {
                if (response!=null){
                    if (response.body().getCode() == 200){
                        ((HomeActivity)getActivity()).profileName.setText(response.body().getMemberDetailsResult().getMemberDetail().getFirstName() +" "+response.body().getMemberDetailsResult().getMemberDetail().getLastName());
                        Glide
                                .with(getActivity())
                                .load(response.body().getMemberDetailsResult().getMemberDetail().getImageurl())
                                .centerCrop()
                                .placeholder(R.drawable.ic_baseline_person_24)
                                .into(((HomeActivity)getActivity()).profileImage);

                        ((HomeActivity)getActivity()).profileMemberId.setText(response.body().getMemberDetailsResult().getMemberDetail().getMemberId());

                        userNameTextView.setText(response.body().getMemberDetailsResult().getMemberDetail().getFirstName() +" "+response.body().getMemberDetailsResult().getMemberDetail().getLastName() + " (" + response.body().getMemberDetailsResult().getMemberDetail().getMemberId() + ")");

                    }else {
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MemberDetailsResponse> call, Throwable t) {

            }
        });
    }
}
