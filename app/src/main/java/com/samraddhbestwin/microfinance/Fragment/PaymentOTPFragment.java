package com.samraddhbestwin.microfinance.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.samraddhbestwin.microfinance.HomeActivity;
import com.samraddhbestwin.microfinance.OtpActivity;
import com.samraddhbestwin.microfinance.R;
import com.samraddhbestwin.microfinance.Response.PaymentOTPResponse;
import com.samraddhbestwin.microfinance.Response.PaymentSubmitResponse;
import com.samraddhbestwin.microfinance.Response.Respones.CommonResponseWithoutData;
import com.samraddhbestwin.microfinance.Rest.RestHandler;
import com.samraddhbestwin.microfinance.Session.SessionManager;

import java.util.HashMap;
import java.util.Locale;

import in.aabhasjindal.otptextview.OtpTextView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentOTPFragment extends Fragment {
    Button sendbtn;
    OtpTextView otp_view;
    ProgressBar progressBar;
    TextView resend;
    HashMap<String ,String > UserDataToken;
    SessionManager sessionManager;
    String token="";
    HashMap<String, String> UserData;
    String member_id;
    String PreOTP,type,loanId, emiAmount,paymentMode;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_payment_o_t_p, container, false);

        ((HomeActivity)getActivity()).showNotificationIcon(false);
        sendbtn=(Button)rootView.findViewById(R.id.sendbtn);
        otp_view=(OtpTextView) rootView.findViewById(R.id.otp_view);
        progressBar = (ProgressBar)rootView.findViewById(R.id.spin_kit);
        resend=(TextView)rootView.findViewById(R.id.textView7);

        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =((HomeActivity)getActivity()).getUserDataToSession(getActivity());
        member_id = UserData.get(SessionManager.KEY_member_id);

        final Bundle bundle = getArguments();
        if (bundle != null) {
            PreOTP = bundle.getString("otp");
            type = bundle.getString("type");
            loanId = bundle.getString("loan_id");
            emiAmount = bundle.getString("emi_amount");
            paymentMode = bundle.getString("payment_mode");

            ((HomeActivity)getActivity()).set_action_title(type+" Payment");
        }

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResendOTP();
            }
        });

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp=otp_view.getOTP();
                if(otp.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }else if (PreOTP.equalsIgnoreCase(otp)){
                    submitLoanData();
                }else {
                    Toast.makeText(getActivity().getApplicationContext(), "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private void submitLoanData() {
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (type.equalsIgnoreCase("Loan")){
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "loan");
            RequestBody _loan_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(loanId));
            RequestBody _payment_mode = RequestBody.create(MediaType.parse("multipart/form-data"), paymentMode);
            RequestBody _emi_amount = RequestBody.create(MediaType.parse("multipart/form-data"), emiAmount);
            Call<PaymentSubmitResponse> paymentSubmitResponseCall = RestHandler.getApiService().SUBMIT_LOAN_PAYMENT(_member_id,_token, _type, _loan_id, _payment_mode, _emi_amount);
            paymentSubmitResponseCall.enqueue(new Callback<PaymentSubmitResponse>() {
                @Override
                public void onResponse(Call<PaymentSubmitResponse> call, Response<PaymentSubmitResponse> response) {
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
                                        getActivity().getSupportFragmentManager().popBackStack();
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
                                        getActivity().getSupportFragmentManager().popBackStack();
                                    }
                                });
                                dialog.show();
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
                public void onFailure(Call<PaymentSubmitResponse> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else if(type.equalsIgnoreCase("Group Loan")){
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "group_loan");
            RequestBody _loan_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(loanId));
            RequestBody _payment_mode = RequestBody.create(MediaType.parse("multipart/form-data"), paymentMode);
            RequestBody _emi_amount = RequestBody.create(MediaType.parse("multipart/form-data"), emiAmount);
            Call<PaymentSubmitResponse> paymentSubmitResponseCall = RestHandler.getApiService().SUBMIT_GROUP_LOAN_PAYMENT(_member_id,_token, _type, _loan_id, _payment_mode,_emi_amount);
            paymentSubmitResponseCall.enqueue(new Callback<PaymentSubmitResponse>() {
                @Override
                public void onResponse(Call<PaymentSubmitResponse> call, Response<PaymentSubmitResponse> response) {
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
                                        getActivity().getSupportFragmentManager().popBackStack();
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
                public void onFailure(Call<PaymentSubmitResponse> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }else {
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _loan_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(loanId));
            RequestBody _payment_mode = RequestBody.create(MediaType.parse("multipart/form-data"), paymentMode);
            RequestBody _emi_amount = RequestBody.create(MediaType.parse("multipart/form-data"), emiAmount);
            Call<PaymentSubmitResponse> paymentSubmitResponseCall = RestHandler.getApiService().SUBMIT_INVESTMENT_PAYMENT(_member_id,_token, _loan_id, _payment_mode,_emi_amount);
            paymentSubmitResponseCall.enqueue(new Callback<PaymentSubmitResponse>() {
                @Override
                public void onResponse(Call<PaymentSubmitResponse> call, Response<PaymentSubmitResponse> response) {
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
                                        getActivity().getSupportFragmentManager().popBackStack();
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
                public void onFailure(Call<PaymentSubmitResponse> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(), "Server Error...", Toast.LENGTH_SHORT).show();
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    private void ResendOTP() {
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _type;
        if (type.equalsIgnoreCase("Loan")){
            _type = RequestBody.create(MediaType.parse("multipart/form-data"), "loan");
        }else if(type.equalsIgnoreCase("Group Loan")){
            _type = RequestBody.create(MediaType.parse("multipart/form-data"), "group_loan");
        }else{
            _type = RequestBody.create(MediaType.parse("multipart/form-data"), "deposit");
        }
        Call<PaymentOTPResponse> ssbDepositResponseCall = RestHandler.getApiService().PAYMENT_OTP(_member_id,_token, _type);
        ssbDepositResponseCall.enqueue(new Callback<PaymentOTPResponse>() {
            @Override
            public void onResponse(Call<PaymentOTPResponse> call, Response<PaymentOTPResponse> response) {
                try {
                    if (response.isSuccessful()){
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                        if (response.body().getCode() == 200){
                            PreOTP = response.body().getOtp();
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
    }
}