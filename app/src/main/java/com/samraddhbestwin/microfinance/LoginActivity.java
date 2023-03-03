package com.samraddhbestwin.microfinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

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

import com.samraddhbestwin.microfinance.Adapter.DashbordItemListAdapter;
import com.samraddhbestwin.microfinance.Fragment.DashboardFragment;
import com.samraddhbestwin.microfinance.Response.LoginResponse;
import com.samraddhbestwin.microfinance.Response.OtpResult;
import com.samraddhbestwin.microfinance.Response.OtpSendResponse;
import com.samraddhbestwin.microfinance.Response.OtpVerifyResponse;
import com.samraddhbestwin.microfinance.Rest.RestHandler;
import com.samraddhbestwin.microfinance.Session.SessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button sendbtn;
    EditText editTextPhone;
    ProgressBar progressBar;
    SessionManager sessionManager;
    HashMap<String ,String > UserDataToken;
    public static String firebaseToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        sendbtn=(Button)findViewById(R.id.sendbtn);
        editTextPhone=(EditText) findViewById(R.id.editTextPhone);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        return;
                    }
                    // Get new FCM registration token
                    firebaseToken = task.getResult();
                    Log.d("Firebase Token", firebaseToken);
                });


        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String member_id=editTextPhone.getText().toString();
                if(member_id.equals("")){
                    Toast.makeText(LoginActivity.this, "Please Enter Member ID ", Toast.LENGTH_SHORT).show();
                }else {
                    SendOtp(member_id);
                }
            }
        });
        sessionManager = new SessionManager(this);
        UserDataToken =sessionManager.getUserDetails();
        sessionManager.logoutToken();
    }
    ////////////////////////SendOtp////////////////////////////SendOtp////////////////////////////////////////////SendOtp////////////
   public void SendOtp(String member_id){
       try {
           progressBar.setVisibility(View.VISIBLE);
           LoginActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                   WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
           RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
           RequestBody _fcm_token = RequestBody.create(MediaType.parse("multipart/form-data"), firebaseToken);
           Call<LoginResponse> applicationsListResponesCall = RestHandler.getApiService().OTP_SEND_RESPONES_CALL(_member_id, _fcm_token);
           applicationsListResponesCall.enqueue(new Callback<LoginResponse>() {
               @Override
               public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                   if (response.body().getCode()!=null){
                       if (response.body().getCode() == 200 ){
                           progressBar.setVisibility(View.GONE);
                           LoginActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                           Toast.makeText(LoginActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                           sessionManager.TokenSession(response.body().getToken());
                           Bundle bundle = new Bundle();
                           bundle.putString("member_id",member_id);
                           Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                           intent.putExtras(bundle);
                           startActivity(intent);
                           finish();
                       }else {
                           progressBar.setVisibility(View.GONE);
                           LoginActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                           Toast.makeText(LoginActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                       }
                   }else {
                       progressBar.setVisibility(View.GONE);
                       LoginActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                       Toast.makeText(LoginActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                   }
               }

               @Override
               public void onFailure(Call<LoginResponse> call, Throwable t) {
                   progressBar.setVisibility(View.GONE);
                   LoginActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                   Toast.makeText(LoginActivity.this, ""+t, Toast.LENGTH_SHORT).show();
               }
           });
       }catch (Exception e){
           progressBar.setVisibility(View.GONE);
           LoginActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
           e.printStackTrace();
       }



       ////////////////////////SendOtp////////////////////////////SendOtp////////////////////////////////////////////SendOtp////////////

   }
}