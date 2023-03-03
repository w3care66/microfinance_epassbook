package com.samraddhbestwin.microfinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.samraddhbestwin.microfinance.Response.LoginResponse;
import com.samraddhbestwin.microfinance.Response.OtpSendResponse;
import com.samraddhbestwin.microfinance.Response.OtpVerifyResponse;
import com.samraddhbestwin.microfinance.Rest.RestHandler;
import com.samraddhbestwin.microfinance.Session.SessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Objects;

import in.aabhasjindal.otptextview.OtpTextView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {

    Button sendbtn;
    OtpTextView otp_view;
    ProgressBar progressBar;
    TextView resend;
    HashMap<String, String> UserDataToken;
    SessionManager sessionManager;
    String token = "";
    String firebaseToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        sendbtn = (Button) findViewById(R.id.sendbtn);
        otp_view = (OtpTextView) findViewById(R.id.otp_view);
        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        resend = (TextView) findViewById(R.id.textView7);

        sessionManager = new SessionManager(this);
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        // Get new FCM registration token
                        firebaseToken = task.getResult();
                        Log.d("Firebase Token", firebaseToken);
                    }
                });


        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String member_id = bundle.getString("member_id");
        }


        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = otp_view.getOTP();
                if (otp.equals("")) {
                    Toast.makeText(OtpActivity.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    if (bundle != null) {
                        String member_id = bundle.getString("member_id");
                        VerifyOtp(member_id, otp, token);
                    }
                }

            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bundle != null) {
                    String member_id = bundle.getString("member_id");
                    SendOtp(member_id);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    ////////////////////////VerifyOtp////////////////////////////VerifyOtp////////////////////////////////////////////VerifyOtp////////////
    public void VerifyOtp(String member_id, String otpValue, String token) {

        progressBar.setVisibility(View.VISIBLE);
        OtpActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _otp = RequestBody.create(MediaType.parse("multipart/form-data"), otpValue);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<OtpVerifyResponse> applicationsListResponesCall = RestHandler.getApiService().Otp_Varified_RESPONES_CALL(_member_id, _otp, _token);
        applicationsListResponesCall.enqueue(new Callback<OtpVerifyResponse>() {
            @Override
            public void onResponse(Call<OtpVerifyResponse> call, Response<OtpVerifyResponse> response) {
                Log.e("data", "" + response.body().getCode());
                if (response.body().getCode() != null) {
                    if (response.body().getCode() == 200) {
                        progressBar.setVisibility(View.GONE);
                        OtpActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(OtpActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();

                        Bundle bundle = new Bundle();
                        bundle.putString("member_id", member_id);
                        Intent intent = new Intent(OtpActivity.this, SetPinActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        progressBar.setVisibility(View.GONE);
                        OtpActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(OtpActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    OtpActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Toast.makeText(OtpActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OtpVerifyResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                OtpActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(OtpActivity.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
    ////////////////////////VerifyOtp////////////////////////////VerifyOtp////////////////////////////////////////////VerifyOtp////////////

    ////////////////////////SendOtp////////////////////////////SendOtp////////////////////////////////////////////SendOtp////////////
    public void SendOtp(String member_id) {

        progressBar.setVisibility(View.VISIBLE);
        OtpActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _fcm_token = RequestBody.create(MediaType.parse("multipart/form-data"), firebaseToken);
        Call<LoginResponse> applicationsListResponesCall = RestHandler.getApiService().OTP_SEND_RESPONES_CALL(_member_id, _fcm_token);
        applicationsListResponesCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().getCode() != null) {
                    if (response.body().getCode() == 200) {
                        progressBar.setVisibility(View.GONE);
                        OtpActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(OtpActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        sessionManager.TokenSession(response.body().getToken());
                        Bundle bundle = new Bundle();
                        bundle.putString("member_id", member_id);
                        Intent intent = new Intent(OtpActivity.this, OtpActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        progressBar.setVisibility(View.GONE);
                        OtpActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(OtpActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    OtpActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Toast.makeText(OtpActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                OtpActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(OtpActivity.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
        ////////////////////////SendOtp////////////////////////////SendOtp////////////////////////////////////////////SendOtp////////////
    }
}