package com.samraddhbestwin.microfinance.Fragment;

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

import com.samraddhbestwin.microfinance.Adapter.SSBDepositAdapter;
import com.samraddhbestwin.microfinance.HomeActivity;
import com.samraddhbestwin.microfinance.R;
import com.samraddhbestwin.microfinance.Response.NotificationListResponse;
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

public class SSBAccountFragment extends Fragment implements SSBDepositAdapter.SSBDepositListener {

    ProgressBar progressBar;
    RecyclerView ssbAccountRecyclerView;
    TextView noDataFoundTextView;
    SSBDepositAdapter adapter;
    ArrayList<SSBDepositResponse.SSBDepositData> ssbAccountList = new ArrayList<>();
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
        View rootView = inflater.inflate(R.layout.fragment_s_s_b_account, container, false);
        ((HomeActivity)getActivity()).set_action_title(getString(R.string.ssb_account_));
        ((HomeActivity)getActivity()).showNotificationIcon(true);
        progressBar = (ProgressBar) rootView.findViewById(R.id.spin_kit);
        ssbAccountRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_ssb_account);
        noDataFoundTextView = (TextView) rootView.findViewById(R.id.tv_no_data_found);
        ssbAccountRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ssbAccountRecyclerView.hasFixedSize();
        adapter = new SSBDepositAdapter(this.getContext(), ssbAccountList, "SSB", SSBAccountFragment.this);
        ssbAccountRecyclerView.setAdapter(adapter);
        UserData =((HomeActivity)getActivity()).getUserDataToSession(getActivity());
        member_id = UserData.get(SessionManager.KEY_member_id);
        sessionManager = new SessionManager(getActivity());
        UserDataToken = sessionManager.getUserDetailsToken();
        token = UserDataToken.get(SessionManager.KEY_TOKEN);

        getSSBAccountListData();

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

    private void getSSBAccountListData() {
        ssbAccountList.clear();
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody _type = RequestBody.create(MediaType.parse("multipart/form-data"), "ssb");
        Call<SSBDepositResponse> ssbDepositResponseCall = RestHandler.getApiService().SSB_DEPOSIT_LIST(_member_id,_token, _type);
        ssbDepositResponseCall.enqueue(new Callback<SSBDepositResponse>() {
            @Override
            public void onResponse(Call<SSBDepositResponse> call, Response<SSBDepositResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if (response.body().getCode() == 200){
                            if (response.body().getData().size() > 0){
                                for (int i=0; i< response.body().getData().size(); i++){
                                    SSBDepositResponse.SSBDepositData ssbDepositData = new SSBDepositResponse.SSBDepositData();
                                    ssbDepositData.setInvestmentId(response.body().getData().get(i).getInvestmentId());
                                    ssbDepositData.setAccountNumber(response.body().getData().get(i).getAccountNumber());
                                    ssbDepositData.setDenoAmount(response.body().getData().get(i).getDenoAmount());
                                    ssbDepositData.setMaturityDate(response.body().getData().get(i).getMaturityDate());
                                    ssbDepositData.setDeposit(response.body().getData().get(i).getDeposit());
                                    ssbDepositData.setOpeningDate(response.body().getData().get(i).getOpeningDate());
                                    ssbDepositData.setPlan(response.body().getData().get(i).getPlan());
                                    ssbDepositData.setDueAmount(response.body().getData().get(i).getDueAmount());
                                    ssbAccountList.add(ssbDepositData);
                                }
                                noDataFoundTextView.setVisibility(View.GONE);
                                ssbAccountRecyclerView.setVisibility(View.VISIBLE);
                            }else {
                                noDataFoundTextView.setVisibility(View.VISIBLE);
                                ssbAccountRecyclerView.setVisibility(View.GONE);
                            }

                            adapter.notifyDataSetChanged();
                        }else {
                            noDataFoundTextView.setVisibility(View.VISIBLE);
                            ssbAccountRecyclerView.setVisibility(View.GONE);
                        }
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                    }else {
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        progressBar.setVisibility(View.GONE);
                        noDataFoundTextView.setVisibility(View.VISIBLE);
                        ssbAccountRecyclerView.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }


            }
            @Override
            public void onFailure(Call<SSBDepositResponse> call, Throwable t) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);
                noDataFoundTextView.setVisibility(View.VISIBLE);
                ssbAccountRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onSSBAccountTransaction(String investment_id) {
        Fragment fragment = new MyTranscationListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("investment_id", investment_id);
        bundle.putString("type", "ssb");
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.addToBackStack(null);
        fragmentTransaction1.replace(R.id.container, fragment).commit();
    }

    @Override
    public void onInvestAccountTransaction(String investment_id, String type, int dueAmount) {}

}