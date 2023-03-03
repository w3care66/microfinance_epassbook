package com.samraddhbestwin.microfinance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.samraddhbestwin.microfinance.Fragment.DashboardFragment;
import com.samraddhbestwin.microfinance.Fragment.InvestmentAccountFragment;
import com.samraddhbestwin.microfinance.Fragment.GroupLoanAccountFragment;
import com.samraddhbestwin.microfinance.Fragment.LoanAccountFragment;
import com.samraddhbestwin.microfinance.Fragment.NotificationListFragment;
import com.samraddhbestwin.microfinance.Fragment.ProfileFragment;
import com.samraddhbestwin.microfinance.Fragment.SSBAccountFragment;
import com.samraddhbestwin.microfinance.Response.MemberDetailsResponse;
import com.samraddhbestwin.microfinance.Rest.RestHandler;
import com.samraddhbestwin.microfinance.Session.SessionManager;
import com.google.android.material.navigation.NavigationView;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    Fragment fragment;
    FragmentTransaction fragmentTransaction;
    SessionManager sessionManager;
    public static TextView pageTitleTextView;
    public static ImageView draerIconImageView;
    public static ImageView notificationIconImageView;
    public static TextView profileName, profileMemberId, notificationCountTextView;
    public static ImageView profileImage;
    HashMap<String, String> userData;
    private DrawerLayout drawerLayout;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((notificationCounterReceiver), new IntentFilter("UPDATE_COUNTER"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(notificationCounterReceiver);
    }

    private BroadcastReceiver notificationCounterReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            notificationCountTextView.setText(String.valueOf(Integer.parseInt(notificationCountTextView.getText().toString()) + 1));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pageTitleTextView = findViewById(R.id.textViewtitle);
        draerIconImageView = (ImageView) findViewById(R.id.ic_drawer);
        notificationIconImageView = (ImageView) findViewById(R.id.iv_notification);
        notificationCountTextView = (TextView) findViewById(R.id.txt_notification_Count);
        userData = getUserDataToSession(this);
        member_id = userData.get(SessionManager.KEY_member_id);
        drawerLayout = findViewById(R.id.activity_main);
        sessionManager = new SessionManager(this);
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);

//
        try {
            if(getIntent().hasExtra("is_from")){
                getMemberDetails(member_id,token);
                fragment = new ProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putString("is_from", "notification");
                bundle.putString("notification_id", getIntent().getStringExtra("notification_id"));
                fragment.setArguments(bundle);
            }else {
                fragment = new DashboardFragment();
            }
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, fragment);
            fragmentTransaction.commit();
        }catch (Exception e){
            fragment = new DashboardFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, fragment);
            fragmentTransaction.commit();
            e.printStackTrace();
        }




        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        profileName=(TextView) hView.findViewById(R.id.Profile_name);
        profileImage = (ImageView) hView.findViewById(R.id.profile_image);
        profileMemberId = (TextView) hView.findViewById(R.id.txt_member_id);

        draerIconImageView.setOnClickListener(view -> {
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        hView.findViewById(R.id.ll_home).setOnClickListener(view -> {
            Fragment fragment = new DashboardFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();
            drawerLayout.closeDrawers();
        });

        hView.findViewById(R.id.ll_ssb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewFragment("SSB");
                drawerLayout.closeDrawers();
            }
        });

        hView.findViewById(R.id.ll_deposit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewFragment("Investment");
                drawerLayout.closeDrawers();
            }
        });

        hView.findViewById(R.id.ll_loan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewFragment("Loan");
                drawerLayout.closeDrawers();
            }
        });

        hView.findViewById(R.id.ll_group_loan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewFragment("Group Loan");
                drawerLayout.closeDrawers();
            }
        });

        hView.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutUser();
                startActivity(new Intent(HomeActivity.this, PinActivity.class));
                finish();
            }
        });
        profileImage.setOnClickListener(v -> {
            Fragment fragment = new ProfileFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            drawerLayout.closeDrawers();
        });

        notificationIconImageView.setOnClickListener(view -> {
            NotificationListFragment ssbAccountFragment=new NotificationListFragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,ssbAccountFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            drawerLayout.closeDrawers();
        });
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
                this.finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    public HashMap<String, String> getUserDataToSession(Context context) {
        sessionManager = new SessionManager(context);
        HashMap<String, String> userData = sessionManager.getUserDetails();
        return userData;
    }

    public void set_action_title(String title){
        pageTitleTextView.setText(String.valueOf(title));
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
                        profileName.setText(response.body().getMemberDetailsResult().getMemberDetail().getFirstName() +" "+response.body().getMemberDetailsResult().getMemberDetail().getLastName());
                        Glide
                                .with(HomeActivity.this)
                                .load(response.body().getMemberDetailsResult().getMemberDetail().getImageurl())
                                .centerCrop()
                                .placeholder(R.drawable.ic_baseline_person_24)
                                .into(profileImage);

                        profileMemberId.setText(response.body().getMemberDetailsResult().getMemberDetail().getMemberId());
                    }else {
                        Toast.makeText(HomeActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(HomeActivity.this, "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MemberDetailsResponse> call, Throwable t) {

            }
        });
    }


    public void openNewFragment(String type) {
        if (type.equals("SSB")){
            SSBAccountFragment fragment2=new SSBAccountFragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,fragment2);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else if (type.equalsIgnoreCase("Investment")){
            InvestmentAccountFragment ssbAccountFragment=new InvestmentAccountFragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,ssbAccountFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (type.equalsIgnoreCase("Loan")){
            LoanAccountFragment ssbAccountFragment=new LoanAccountFragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,ssbAccountFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (type.equalsIgnoreCase("Group Loan")){
            GroupLoanAccountFragment ssbAccountFragment=new GroupLoanAccountFragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,ssbAccountFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    public void showNotificationIcon(boolean isShow) {
        if (isShow){
            notificationIconImageView.setVisibility(View.GONE);
            notificationCountTextView.setVisibility(View.GONE);
        }else {
            notificationIconImageView.setVisibility(View.GONE);
            notificationCountTextView.setVisibility(View.GONE);
        }


    }
}