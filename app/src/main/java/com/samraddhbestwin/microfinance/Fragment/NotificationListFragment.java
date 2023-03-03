package com.samraddhbestwin.microfinance.Fragment;

import android.content.Intent;
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

import com.samraddhbestwin.microfinance.Adapter.NotificationAdapter;
import com.samraddhbestwin.microfinance.Adapter.SSBDepositAdapter;
import com.samraddhbestwin.microfinance.HomeActivity;
import com.samraddhbestwin.microfinance.Model.SSBModelResponse;
import com.samraddhbestwin.microfinance.NotificationDetailActivity;
import com.samraddhbestwin.microfinance.PinActivity;
import com.samraddhbestwin.microfinance.R;
import com.samraddhbestwin.microfinance.Response.NotificationListResponse;
import com.samraddhbestwin.microfinance.Rest.RestHandler;
import com.samraddhbestwin.microfinance.Session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationListFragment extends Fragment implements NotificationAdapter.NotificationListener {
    ProgressBar progressBar;
    RecyclerView notificationRecyclerView;
    TextView noDataFoundTextView;
    NotificationAdapter adapter;
    ArrayList<NotificationListResponse.NotificationData> notificationList = new ArrayList<>();
    HashMap<String, String> UserDataToken;
    String token = "";
    HashMap<String, String> UserData;
    String member_id;
    SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notification_list, container, false);
        ((HomeActivity)getActivity()).set_action_title(getString(R.string.notification_list));
        ((HomeActivity)getActivity()).showNotificationIcon(false);
        progressBar = (ProgressBar) rootView.findViewById(R.id.spin_kit);
        notificationRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_notification);
        noDataFoundTextView = (TextView) rootView.findViewById(R.id.tv_no_data_found);
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        notificationRecyclerView.hasFixedSize();
        adapter = new NotificationAdapter(this.getContext(), notificationList, NotificationListFragment.this);
        notificationRecyclerView.setAdapter(adapter);
        UserData =((HomeActivity)getActivity()).getUserDataToSession(getActivity());
        member_id = UserData.get(SessionManager.KEY_member_id);
        sessionManager = new SessionManager(getActivity());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);


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

    @Override
    public void onResume() {
        super.onResume();
        getNotificationData();
    }

    private void getNotificationData() {
        notificationList.clear();
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<NotificationListResponse> notificationListResponseCall = RestHandler.getApiService().NOTIFICATION_LIST(_member_id,_token);
        notificationListResponseCall.enqueue(new Callback<NotificationListResponse>() {
            @Override
            public void onResponse(Call<NotificationListResponse> call, Response<NotificationListResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if (response.body().getCode() == 200){
                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            progressBar.setVisibility(View.GONE);
                            if (response.body().getData().size() > 0){
                                for (int i=0; i< response.body().getData().size(); i++){
                                    NotificationListResponse.NotificationData notificationData = new NotificationListResponse.NotificationData();
                                    notificationData.setId(response.body().getData().get(i).getId());
                                    notificationData.setTitle(response.body().getData().get(i).getTitle());
                                    notificationData.setDescription(response.body().getData().get(i).getDescription());
                                    notificationData.setIsRead(response.body().getData().get(i).getIsRead());
                                    notificationData.setAccountNumber(response.body().getData().get(i).getAccountNumber());
                                    notificationData.setLoanType(response.body().getData().get(i).getLoanType());
                                    notificationData.setCreatedAt(response.body().getData().get(i).getCreatedAt());
                                    notificationData.setUpdatedAt(response.body().getData().get(i).getUpdatedAt());
                                    notificationData.setLoanId(response.body().getData().get(i).getLoanId());
                                    notificationData.setNotificationTime(response.body().getData().get(i).getNotificationTime());
                                    notificationList.add(notificationData);
                                }
                                noDataFoundTextView.setVisibility(View.GONE);
                                notificationRecyclerView.setVisibility(View.VISIBLE);
                            }else {
                                noDataFoundTextView.setVisibility(View.VISIBLE);
                                notificationRecyclerView.setVisibility(View.GONE);
                            }
                            adapter.notifyDataSetChanged();
                        }else {
                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            progressBar.setVisibility(View.GONE);
                            noDataFoundTextView.setVisibility(View.VISIBLE);
                            notificationRecyclerView.setVisibility(View.GONE);
                        }

                    }else {
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                        noDataFoundTextView.setVisibility(View.VISIBLE);
                        notificationRecyclerView.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<NotificationListResponse> call, Throwable t) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);
                noDataFoundTextView.setVisibility(View.VISIBLE);
                notificationRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onNotificationDetail(String loan_id, String type, String notificationId) {
        if (!type.equalsIgnoreCase("member_profile")){
            Intent intent = new Intent(getActivity(), NotificationDetailActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("loan_id", loan_id);
            intent.putExtra("notification_id", notificationId);
            startActivity(intent);
        }else {
            Fragment fragment = new ProfileFragment();
            Bundle bundle = new Bundle();
            bundle.putString("is_from", "notification");
            bundle.putString("notification_id", notificationId);
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
            fragmentTransaction1.addToBackStack(null);
            fragmentTransaction1.replace(R.id.container, fragment).commit();
        }
    }
}