package com.samraddhbestwin.microfinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.samraddhbestwin.microfinance.Fragment.DashboardFragment;
import com.samraddhbestwin.microfinance.Response.OtpVerifyResponse;
import com.samraddhbestwin.microfinance.Rest.RestHandler;
import com.samraddhbestwin.microfinance.Session.SessionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import in.aabhasjindal.otptextview.OtpTextView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinActivity extends AppCompatActivity {

    Button sendbtn;
    TextView memberid;
    OtpTextView otp_view,re_otp_view;
    ProgressBar progressBar;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;

    Boolean isFromDeepLink = false;
    Boolean isFromNotification = false;
    String type, loanId, notificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        try {
            Uri uri = getIntent().getData();
            if (uri != null) {
                isFromDeepLink = true;
                List<String> parameters = uri.getPathSegments();
                type = parameters.get(0).split("-")[0];
                loanId = parameters.get(0).split("-")[1];
                notificationId = parameters.get(1).split("-")[1];
            }
        }catch (Exception e){e.printStackTrace();}

        try {
            if(getIntent().hasExtra("is_from")){
                isFromNotification = true;
                type = getIntent().getStringExtra("type");
                loanId = getIntent().getStringExtra("loan_id");
                notificationId = getIntent().getStringExtra("notification_id");
            }
        }catch (Exception e){e.printStackTrace();}


        sendbtn=(Button) findViewById(R.id.sendbtn);
//        memberid=(TextView) findViewById(R.id.memberid);
        otp_view=(OtpTextView)findViewById(R.id.otp_view);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);


        sessionManager = new SessionManager(this);
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
       UserData =sessionManager.getUserDetails();

       member_id= UserData.get(SessionManager.KEY_member_id);
       //memberid.setText(member_id);





        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pin=otp_view.getOTP();

                if(pin.equals("")) {
                    Toast.makeText(PinActivity.this, "Please Enter Pin", Toast.LENGTH_SHORT).show();
                }
                         pinVerfiy(member_id,pin,token);
                }


        });
    }
    public  void pinVerfiy(String member_id,String upi_value,String token){
        progressBar.setVisibility(View.VISIBLE);
        PinActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
        RequestBody _upi = RequestBody.create(MediaType.parse("multipart/form-data"), upi_value);
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<OtpVerifyResponse> applicationsListResponesCall = RestHandler.getApiService().VERIFY_PIN_RESPONES_CALL(_member_id,_upi,_token);
        applicationsListResponesCall.enqueue(new Callback<OtpVerifyResponse>() {
            @Override
            public void onResponse(Call<OtpVerifyResponse> call, Response<OtpVerifyResponse> response) {
                if (response!=null){
//                    Log.e("getOtpResponse",""+response.body().getCode());
                    if (response.body().getCode() == 200 ){
                        progressBar.setVisibility(View.GONE);
                        PinActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(PinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        sessionManager.createLoginSession(member_id,upi_value);
                        if (isFromDeepLink){
                            Intent intent = new Intent(PinActivity.this, NotificationDetailActivity.class);
                            intent.putExtra("type", type);
                            intent.putExtra("loan_id", loanId);
                            intent.putExtra("notification_id", notificationId);
                            startActivity(intent);
                            finish();
                        } else if(isFromNotification){
                            Intent intent = null;
                            if (!type.equalsIgnoreCase("member_profile")){
                                intent = new Intent(PinActivity.this, NotificationDetailActivity.class);
                                intent.putExtra("type", type);
                                intent.putExtra("loan_id", loanId);
                                intent.putExtra("notification_id", notificationId);
                            }else {
                                intent = new Intent(PinActivity.this, HomeActivity.class);
                                intent.putExtra("is_from", "notification");
                                intent.putExtra("notification_id", notificationId);
                            }
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(PinActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }else {
                        progressBar.setVisibility(View.GONE);
                        PinActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(PinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    progressBar.setVisibility(View.GONE);
                    PinActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Toast.makeText(PinActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OtpVerifyResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                PinActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Toast.makeText(PinActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}