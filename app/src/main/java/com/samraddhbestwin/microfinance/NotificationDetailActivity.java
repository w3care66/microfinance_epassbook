package com.samraddhbestwin.microfinance;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.samraddhbestwin.microfinance.Fragment.PaymentOTPFragment;
import com.samraddhbestwin.microfinance.Response.LoanListResponse;
import com.samraddhbestwin.microfinance.Response.NotificationDetailResponse;
import com.samraddhbestwin.microfinance.Response.PaymentOTPResponse;
import com.samraddhbestwin.microfinance.Response.PaymentSubmitResponse;
import com.samraddhbestwin.microfinance.Response.Respones.CommonResponseWithoutData;
import com.samraddhbestwin.microfinance.Rest.RestHandler;
import com.samraddhbestwin.microfinance.Session.SessionManager;

import java.util.HashMap;
import java.util.List;

import in.aabhasjindal.otptextview.OtpTextView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationDetailActivity extends AppCompatActivity {

    String loanId = "", type, notificationId;
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    String member_id;
    SessionManager sessionManager;
    ProgressBar progressBar;
    String OTP = "";
    CardView detailPageCardView;
    /*Investment*/
    ConstraintLayout investmentDetailConstraintLayout;
    TextView investAccountNumber, investPlanNameTextView, investDenoAmountTextView, investOpeningDateTextView, investMeturityDateTextView, investTotalAmountTextView, investDueAmountTextView;
    Button investSSBPaymentButton, investUPIPaymentButton;
    /*Loan and Group Loan*/
    TextView loanAccountNoTextView, loanTypeTextView, loanAmountTextView, loanIssueDateTextView, loanInstalmentTextView, loanEMIAmountTextView, loanEMIPaidTextView;
    ConstraintLayout loanDetailConstraintLayout;
    Button loanSSBPaymentButton, loanUPIPaymentButton;
    TextView noDataFoundTextView;

    String currentBalance;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification_detail);

        type = getIntent().getStringExtra("type");
        loanId = getIntent().getStringExtra("loan_id");
        notificationId = getIntent().getStringExtra("notification_id");

        UserData = getUserDataToSession(this);
        member_id=UserData.get(SessionManager.KEY_member_id);
        sessionManager = new SessionManager(this);
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);

        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        detailPageCardView = findViewById(R.id.cardView);
        noDataFoundTextView = findViewById(R.id.tv_no_data_found);
        updateNotificationStatus();

        /*Investment View*/
        investmentDetailConstraintLayout = findViewById(R.id.cl_investment_account);
        investAccountNumber = findViewById(R.id.txt_invest_acc_no);
        investPlanNameTextView = findViewById(R.id.txt_invest_plan_name);
        investDenoAmountTextView = findViewById(R.id.txt_invest_deno_amount_name);
        investOpeningDateTextView = findViewById(R.id.txt_invest_opening_date_name);
        investMeturityDateTextView = findViewById(R.id.txt_invest_maturity_date_name);
        investTotalAmountTextView = findViewById(R.id.txt_invest_total_deposit);
        investDueAmountTextView = findViewById(R.id.txt_invest_due_amount);
        investSSBPaymentButton = findViewById(R.id.btn_invest_ssb_payment);
        investUPIPaymentButton = findViewById(R.id.btn_invest_upi_payment);

        if (type.equalsIgnoreCase("maturity")){
            investSSBPaymentButton.setVisibility(View.GONE);
            investUPIPaymentButton.setVisibility(View.GONE);
        }else {
            investSSBPaymentButton.setVisibility(View.VISIBLE);
            investUPIPaymentButton.setVisibility(View.VISIBLE);
        }


        /*Loan View*/
        loanDetailConstraintLayout = findViewById(R.id.cl_loan);
        loanAccountNoTextView = findViewById(R.id.txt_loan_accountNo);
        loanTypeTextView = findViewById(R.id.txt_loan_plan_name);
        loanAmountTextView = findViewById(R.id.txt_loan_amount_name);
        loanIssueDateTextView = findViewById(R.id.txt_loan_issue_date_name);
        loanInstalmentTextView = findViewById(R.id.txt_loan_instalment_name);
        loanEMIAmountTextView = findViewById(R.id.txt_loan_emi_amount_name);
        loanEMIPaidTextView = findViewById(R.id.txt_loan_emi_paid_name);
        loanSSBPaymentButton = findViewById(R.id.btn_ssb_payment);
        loanUPIPaymentButton = findViewById(R.id.btn_upi_payment);


        getNotificationDetails();

        investSSBPaymentButton.setOnClickListener(view -> {
            final AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.AlertDialog);
            View mView = getLayoutInflater().inflate(R.layout.investment_payment_dialog,null);
            alert.setView(mView);
            ImageView cancelDialog = mView.findViewById(R.id.iv_cancel_dialog);
            EditText amountEditText = mView.findViewById(R.id.edt_inv_deposit_amount);
            TextView accountNoTextView = mView.findViewById(R.id.inv_acc_no);
            accountNoTextView.setText(investAccountNumber.getText().toString());
            TextView totalDepositTextView = mView.findViewById(R.id.inv_total_deposit);
            totalDepositTextView.setText(investTotalAmountTextView.getText().toString());
            TextView dueAmountTextView = mView.findViewById(R.id.inv_due_amount_no);
            dueAmountTextView.setText(investDueAmountTextView.getText().toString());
            Button submitAmount = mView.findViewById(R.id.btn_submit_inv_payment);
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
            cancelDialog.setOnClickListener(view12 -> {
                alertDialog.dismiss();
            });

            submitAmount.setOnClickListener(v -> {
                if (!amountEditText.getText().toString().equalsIgnoreCase("")){
                    if (Double.parseDouble(amountEditText.getText().toString()) > 0.0){
                        if (Double.parseDouble(currentBalance) > Double.parseDouble(amountEditText.getText().toString())) {
                            alertDialog.dismiss();
                            progressBar.setVisibility(View.VISIBLE);
                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
                            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                            RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), type);
                            Call<PaymentOTPResponse> ssbDepositResponseCall = RestHandler.getApiService().PAYMENT_OTP(_member_id,_token, _type);
                            ssbDepositResponseCall.enqueue(new Callback<PaymentOTPResponse>() {
                                @Override
                                public void onResponse(Call<PaymentOTPResponse> call, Response<PaymentOTPResponse> response) {
                                    try {
                                        if (response.isSuccessful()){
                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                            progressBar.setVisibility(View.GONE);
                                            if (response.body().getCode() == 200){
                                                OTP = response.body().getOtp();
                                                Toast.makeText(getApplicationContext(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                                final AlertDialog.Builder alert = new AlertDialog.Builder(NotificationDetailActivity.this, R.style.AlertDialog);
                                                View mView = getLayoutInflater().inflate(R.layout.dialog_payment_otp,null);
                                                alert.setView(mView);
                                                OtpTextView otpView = mView.findViewById(R.id.otp_view);
                                                Button submitOtp = mView.findViewById(R.id.sendbtn);
                                                final AlertDialog alertOTPDialog = alert.create();
                                                alertOTPDialog.setCanceledOnTouchOutside(false);
                                                alertOTPDialog.setCancelable(false);
                                                alertOTPDialog.show();

                                                submitOtp.setOnClickListener(view1 -> {
                                                    if (!otpView.getOTP().equalsIgnoreCase("")){
                                                        if (otpView.getOTP().equalsIgnoreCase(OTP)){
                                                            alertOTPDialog.dismiss();
                                                            submitInvestPaymentOfSSB();
                                                        }else {
                                                            Toast.makeText(getApplicationContext(),"Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }else {
                                                        Toast.makeText(getApplicationContext(),"Please Enter OTP", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                            }else {
                                                Toast.makeText(getApplicationContext(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            Toast.makeText(getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }catch (Exception e){
                                        progressBar.setVisibility(View.GONE);
                                        e.printStackTrace();
                                    }


                                }
                                @Override
                                public void onFailure(Call<PaymentOTPResponse> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(), "Insufficient balance in your SSB account", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Deposit amount must be greater than 0", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Please enter deposit amount", Toast.LENGTH_SHORT).show();
                }
            });





        });

        investUPIPaymentButton.setOnClickListener(view -> {

        });

        loanSSBPaymentButton.setOnClickListener(view -> {
            if (Double.parseDouble(currentBalance) > Double.parseDouble(loanEMIAmountTextView.getText().toString())){
                progressBar.setVisibility(View.VISIBLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
                RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), type);
                Call<PaymentOTPResponse> ssbDepositResponseCall = RestHandler.getApiService().PAYMENT_OTP(_member_id,_token, _type);
                ssbDepositResponseCall.enqueue(new Callback<PaymentOTPResponse>() {
                    @Override
                    public void onResponse(Call<PaymentOTPResponse> call, Response<PaymentOTPResponse> response) {
                        try {
                            if (response.isSuccessful()){
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                progressBar.setVisibility(View.GONE);
                                if (response.body().getCode() == 200){
                                    OTP = response.body().getOtp();
                                    Toast.makeText(getApplicationContext(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                    final AlertDialog.Builder alert = new AlertDialog.Builder(NotificationDetailActivity.this, R.style.AlertDialog);
                                    View mView = getLayoutInflater().inflate(R.layout.dialog_payment_otp,null);
                                    alert.setView(mView);
                                    OtpTextView otpView = mView.findViewById(R.id.otp_view);
                                    Button submitOtp = mView.findViewById(R.id.sendbtn);
                                    final AlertDialog alertDialog = alert.create();
                                    alertDialog.setCanceledOnTouchOutside(false);
                                    alertDialog.setCancelable(false);
                                    alertDialog.show();

                                    submitOtp.setOnClickListener(view1 -> {
                                        if (!otpView.getOTP().equalsIgnoreCase("")){
                                            if (otpView.getOTP().equalsIgnoreCase(OTP)){
                                                alertDialog.dismiss();
                                                submitPaymentOfSSB();
                                            }else {
                                                Toast.makeText(getApplicationContext(),"Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            Toast.makeText(getApplicationContext(),"Please Enter OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }else {
                                    Toast.makeText(getApplicationContext(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                progressBar.setVisibility(View.GONE);
                            }
                        }catch (Exception e){
                            progressBar.setVisibility(View.GONE);
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<PaymentOTPResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }else{
                Toast.makeText(getApplicationContext(), "Insufficient balance in your SSB account", Toast.LENGTH_SHORT).show();
            }
        });

        loanUPIPaymentButton.setOnClickListener(view -> {

        });

    }

    private void updateNotificationStatus() {
        try {
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _notificationID = RequestBody.create(MediaType.parse("multipart/form-data"), notificationId);
            Call<CommonResponseWithoutData> statusUpdateResponse = RestHandler.getApiService().UPDATE_NOTIFICATION_STATUS(_member_id,_token, _notificationID);
            statusUpdateResponse.enqueue(new Callback<CommonResponseWithoutData>() {
                @Override
                public void onResponse(Call<CommonResponseWithoutData> call, Response<CommonResponseWithoutData> response) {
                }

                @Override
                public void onFailure(Call<CommonResponseWithoutData> call, Throwable t) {
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void submitInvestPaymentOfSSB() {
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "invest");
        RequestBody _loan_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(loanId));
        RequestBody _payment_mode = RequestBody.create(MediaType.parse("multipart/form-data"), "0");
        RequestBody _emi_amount = RequestBody.create(MediaType.parse("multipart/form-data"), investDueAmountTextView.getText().toString());
        Call<PaymentSubmitResponse> paymentSubmitResponseCall = RestHandler.getApiService().SUBMIT_LOAN_PAYMENT(_member_id,_token, _type, _loan_id, _payment_mode, _emi_amount);
        paymentSubmitResponseCall.enqueue(new Callback<PaymentSubmitResponse>() {
            @Override
            public void onResponse(Call<PaymentSubmitResponse> call, Response<PaymentSubmitResponse> response) {

                try {
                    if (response.isSuccessful()){
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                        if (response.body().getCode() == 200){
                            final Dialog dialog = new Dialog(NotificationDetailActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(false);
                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                            dialog.setContentView(R.layout.dialog_success_failure);
                            TextView body = dialog.findViewById(R.id.textview_message);
                            body.setText(response.body().getMessages());
                            ImageView typeImageView = dialog.findViewById(R.id.imageView);
                            typeImageView.setImageResource(R.drawable.ic_success);
                            TextView messageTitle = dialog.findViewById(R.id.textview_title);
                            messageTitle.setText("Congratulations..!!");
                            TextView okButton = dialog.findViewById(R.id.okay_text);
                            okButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(NotificationDetailActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                }
                            });
                            dialog.show();
                        }else {
                            final Dialog dialog = new Dialog(NotificationDetailActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(false);
                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                            dialog.setContentView(R.layout.dialog_success_failure);
                            TextView body = dialog.findViewById(R.id.textview_message);
                            body.setText(response.body().getMessages());
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
                    }else {
                        Toast.makeText(getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<PaymentSubmitResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void submitPaymentOfSSB() {
        progressBar.setVisibility(View.VISIBLE);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (type.equalsIgnoreCase("loan_emi")){
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "loan");
            RequestBody _loan_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(loanId));
            RequestBody _payment_mode = RequestBody.create(MediaType.parse("multipart/form-data"), "0");
            RequestBody _emi_amount = RequestBody.create(MediaType.parse("multipart/form-data"), loanEMIAmountTextView.getText().toString());
            Call<PaymentSubmitResponse> paymentSubmitResponseCall = RestHandler.getApiService().SUBMIT_LOAN_PAYMENT(_member_id,_token, _type, _loan_id, _payment_mode, _emi_amount);
            paymentSubmitResponseCall.enqueue(new Callback<PaymentSubmitResponse>() {
                @Override
                public void onResponse(Call<PaymentSubmitResponse> call, Response<PaymentSubmitResponse> response) {

                    try {
                        if (response.isSuccessful()){
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            progressBar.setVisibility(View.GONE);
                            if (response.body().getCode() == 200){
                                final Dialog dialog = new Dialog(NotificationDetailActivity.this);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setCancelable(false);
                                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                dialog.setContentView(R.layout.dialog_success_failure);
                                TextView body = dialog.findViewById(R.id.textview_message);
                                body.setText(response.body().getMessages());
                                ImageView typeImageView = dialog.findViewById(R.id.imageView);
                                typeImageView.setImageResource(R.drawable.ic_success);
                                TextView messageTitle = dialog.findViewById(R.id.textview_title);
                                messageTitle.setText("Congratulations..!!");
                                TextView okButton = dialog.findViewById(R.id.okay_text);
                                okButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        Intent intent = new Intent(NotificationDetailActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finishAffinity();
                                    }
                                });
                                dialog.show();
                            }else {
                                final Dialog dialog = new Dialog(NotificationDetailActivity.this);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setCancelable(false);
                                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                dialog.setContentView(R.layout.dialog_success_failure);
                                TextView body = dialog.findViewById(R.id.textview_message);
                                body.setText(response.body().getMessages());
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
                        }else {
                            Toast.makeText(getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                        progressBar.setVisibility(View.GONE);
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<PaymentSubmitResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progressBar.setVisibility(View.GONE);
                }
            });
        } else {
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "group_loan");
            RequestBody _loan_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(loanId));
            RequestBody _payment_mode = RequestBody.create(MediaType.parse("multipart/form-data"), "0");
            RequestBody _emi_amount = RequestBody.create(MediaType.parse("multipart/form-data"), loanEMIAmountTextView.getText().toString());
            Call<PaymentSubmitResponse> paymentSubmitResponseCall = RestHandler.getApiService().SUBMIT_GROUP_LOAN_PAYMENT(_member_id,_token, _type, _loan_id, _payment_mode, _emi_amount);
            paymentSubmitResponseCall.enqueue(new Callback<PaymentSubmitResponse>() {
                @Override
                public void onResponse(Call<PaymentSubmitResponse> call, Response<PaymentSubmitResponse> response) {
                    try {
                        if (response.isSuccessful()){
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            progressBar.setVisibility(View.GONE);
                            if (response.body().getCode() == 200){
                                final Dialog dialog = new Dialog(NotificationDetailActivity.this);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setCancelable(false);
                                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                dialog.setContentView(R.layout.dialog_success_failure);
                                TextView body = dialog.findViewById(R.id.textview_message);
                                body.setText(response.body().getMessages());
                                ImageView typeImageView = dialog.findViewById(R.id.imageView);
                                typeImageView.setImageResource(R.drawable.ic_success);
                                TextView messageTitle = dialog.findViewById(R.id.textview_title);
                                messageTitle.setText("Congratulations..!!");
                                TextView okButton = dialog.findViewById(R.id.okay_text);
                                okButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        Intent intent = new Intent(NotificationDetailActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finishAffinity();
                                    }
                                });
                                dialog.show();
                            }else {
                                final Dialog dialog = new Dialog(NotificationDetailActivity.this);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setCancelable(false);
                                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                dialog.setContentView(R.layout.dialog_success_failure);
                                TextView body = dialog.findViewById(R.id.textview_message);
                                body.setText(response.body().getMessages());
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
                        }else {
                            Toast.makeText(getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                        progressBar.setVisibility(View.GONE);
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<PaymentSubmitResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

    }

    private void getNotificationDetails() {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _type = null;
        if(type.equalsIgnoreCase("loan_emi")){
            _type = RequestBody.create(MediaType.parse("multipart/form-data"), "loan");
        }else if(type.equalsIgnoreCase("group_loan_emi")){
            _type = RequestBody.create(MediaType.parse("multipart/form-data"), "group_loan");
        }else{
            _type = RequestBody.create(MediaType.parse("multipart/form-data"), type);
        }
        RequestBody _loanId = RequestBody.create(MediaType.parse("multipart/form-data"), loanId);
        Call<NotificationDetailResponse> ssbDepositResponseCall = RestHandler.getApiService().NOTIFICATION_DETAILS(_member_id,_token, _type,_loanId);
        ssbDepositResponseCall.enqueue(new Callback<NotificationDetailResponse>() {
            @Override
            public void onResponse(Call<NotificationDetailResponse> call, Response<NotificationDetailResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if (response.body().getCode() == 200){
                            if(response.body().getData() != null){
                                detailPageCardView.setVisibility(View.VISIBLE);
                                noDataFoundTextView.setVisibility(View.GONE);
                                if(type.equalsIgnoreCase("loan_emi") || type.equalsIgnoreCase("group_loan_emi")){
                                    loanDetailConstraintLayout.setVisibility(View.VISIBLE);
                                    investmentDetailConstraintLayout.setVisibility(View.GONE);
                                    loanAccountNoTextView.setText(response.body().getData().getAccountNumber());
                                    loanTypeTextView.setText(response.body().getData().getPlanName());
                                    loanAmountTextView.setText(response.body().getData().getLoanAmount());
                                    loanIssueDateTextView.setText(response.body().getData().getIssueDate());
                                    loanInstalmentTextView.setText(response.body().getData().getInstalment() +"/"+response.body().getData().getMode());
                                    loanEMIAmountTextView.setText(response.body().getData().getEmiAmount());
                                    loanEMIPaidTextView.setText(response.body().getData().getEmiPaid());
                                }else {
                                    loanDetailConstraintLayout.setVisibility(View.GONE);
                                    investmentDetailConstraintLayout.setVisibility(View.VISIBLE);
                                    investAccountNumber.setText(response.body().getData().getAccountNumber());
                                    investPlanNameTextView.setText(response.body().getData().getPlanName());
                                    investDenoAmountTextView.setText(response.body().getData().getDenoAmount());
                                    investOpeningDateTextView.setText(response.body().getData().getOpeningDate());
                                    investMeturityDateTextView.setText(response.body().getData().getMaturityDate());
                                    investTotalAmountTextView.setText(response.body().getData().getDeposit());
                                    investDueAmountTextView.setText(response.body().getData().getDueAmount());
                                }
                            }else {
                                detailPageCardView.setVisibility(View.GONE);
                                noDataFoundTextView.setVisibility(View.VISIBLE);
                            }
                            currentBalance = response.body().getCurrentBalance();
                        }else {
                            detailPageCardView.setVisibility(View.GONE);
                            noDataFoundTextView.setVisibility(View.VISIBLE);
                        }
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                    }else {
                        detailPageCardView.setVisibility(View.GONE);
                        noDataFoundTextView.setVisibility(View.VISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    detailPageCardView.setVisibility(View.GONE);
                    noDataFoundTextView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<NotificationDetailResponse> call, Throwable t) {
                detailPageCardView.setVisibility(View.GONE);
                noDataFoundTextView.setVisibility(View.VISIBLE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    public HashMap<String, String> getUserDataToSession(Context context) {
        sessionManager = new SessionManager(context);
        HashMap<String, String> userData = sessionManager.getUserDetails();
        return userData;
    }


}