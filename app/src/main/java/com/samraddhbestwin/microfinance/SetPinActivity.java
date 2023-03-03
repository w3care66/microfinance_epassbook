package com.samraddhbestwin.microfinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.samraddhbestwin.microfinance.Fragment.DashboardFragment;
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

public class SetPinActivity extends AppCompatActivity {

    Button sendbtn;
    TextView memberid;
    OtpTextView otp_view,re_otp_view;
    ProgressBar progressBar;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    String firebaseToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin);

        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

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

        sendbtn=(Button) findViewById(R.id.sendbtn);
        memberid=(TextView) findViewById(R.id.memberid);
        otp_view=(OtpTextView)findViewById(R.id.otp_view);
        re_otp_view=(OtpTextView)findViewById(R.id.otp_view2);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
              member_id = bundle.getString("member_id");
              memberid.setText(member_id);
        }


        sessionManager = new SessionManager(this);
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String pin=otp_view.getOTP();
                    String re_pin=re_otp_view.getOTP();
                    if(pin.equals("")) {
                        Toast.makeText(SetPinActivity.this, "Please Enter Pin", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(re_pin.equals(pin)) {
                            pinVerfiy(member_id,pin,token);
                        }else  {
                            Toast.makeText(SetPinActivity.this, "Sorry! Your Pin Not Match", Toast.LENGTH_SHORT).show();
                        }
                    }

            }
        });
    }
    public  void pinVerfiy(String member_id,String upi_value,String token){
        progressBar.setVisibility(View.VISIBLE);
        SetPinActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _upi = RequestBody.create(MediaType.parse("multipart/form-data"), upi_value);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _fcm_token = RequestBody.create(MediaType.parse("multipart/form-data"), firebaseToken);
        Call<OtpVerifyResponse> applicationsListResponesCall = RestHandler.getApiService().SETUP_PIN_RESPONES_CALL(_member_id,_upi,_token, _fcm_token);
        applicationsListResponesCall.enqueue(new Callback<OtpVerifyResponse>() {
            @Override
            public void onResponse(Call<OtpVerifyResponse> call, Response<OtpVerifyResponse> response) {
                if (response!=null){
//                    Log.e("getOtpResponse",""+response.body().getCode());
                    if (response.body().getCode() == 200 ){
                        progressBar.setVisibility(View.GONE);
                        SetPinActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(SetPinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                      sessionManager.createLoginSession(member_id,upi_value);

                      Intent intent = new Intent(SetPinActivity.this, PinActivity.class);
                      startActivity(intent);
                        finish();
                    }else {
                        progressBar.setVisibility(View.GONE);
                        SetPinActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(SetPinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    progressBar.setVisibility(View.GONE);
                    SetPinActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Toast.makeText(SetPinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OtpVerifyResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                SetPinActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(SetPinActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}