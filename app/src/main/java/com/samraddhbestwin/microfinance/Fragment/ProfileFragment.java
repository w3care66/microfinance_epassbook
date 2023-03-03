package com.samraddhbestwin.microfinance.Fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.samraddhbestwin.microfinance.HomeActivity;
import com.samraddhbestwin.microfinance.R;
import com.samraddhbestwin.microfinance.Response.MemberDetailsResponse;
import com.samraddhbestwin.microfinance.Response.Respones.CommonResponseWithoutData;
import com.samraddhbestwin.microfinance.Rest.RestHandler;
import com.samraddhbestwin.microfinance.Session.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.SimpleTimeZone;

public class ProfileFragment extends Fragment {

    View view;
    ProgressBar progressBar;
    HashMap<String ,String > UserData;
    String member_id;
    TextView member_full_nameTV,form_noTv,join_dateTv,member_idTv,branch_miTv,member_f_nameTv,member_l_nameTv,emailTv,mobileTv,genderTv,dobTv,
            occuptionTv,annual_incomeTv,mother_nameTv,father_nameTv,marital_statsusTv,anniversary_dateTv,religionTv,categoriesTv,statusTv,aadharcardTv,pancardTv,
            nominee_full_nameTv,relationshipTv,nominee_genderTv,nominee_dobTv,nominee_ageTv,nominee_mobileTv,nominee_minorTv,aadharCardTvv,PanCardTvv;
    CircleImageView secondimag;
    ImageView firstimage;

    SessionManager sessionManager;
    HashMap<String ,String > UserDataToken;
    String token="";

    /*Bank Details*/
    CardView bankDetailCardView;
    TextView accountNoTextView, bankNameTextView, bankIFSCCodeTextView, bankBranchTextView, bankAddressTextView;
    /*Bank Details*/
    CardView idProofDetailCardView;
    TextView firstIDProofNameTextView, firstIDProofNoTextView, firstIDProofAddressTextView, secondIDProofNameTextView, secondIDProofNoTextView, secondIDProofAddressTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.spin_kit);member_full_nameTV=(TextView) view.findViewById(R.id.user_profile_name) ;
        ((HomeActivity)getActivity()).showNotificationIcon(false);
        member_full_nameTV=(TextView) view.findViewById(R.id.user_profile_name) ;
        form_noTv=(TextView) view.findViewById(R.id.formNotv) ;
        join_dateTv=(TextView) view.findViewById(R.id.joindate) ;
        member_idTv=(TextView) view.findViewById(R.id.memberIdTv) ;
        branch_miTv=(TextView) view.findViewById(R.id.branchMiTv) ;
        member_f_nameTv=(TextView) view.findViewById(R.id.fnametv) ;
        member_l_nameTv=(TextView) view.findViewById(R.id.lnametv) ;
        emailTv=(TextView) view.findViewById(R.id.emailtv) ;
        mobileTv=(TextView) view.findViewById(R.id.mobileetv) ;
        genderTv=(TextView) view.findViewById(R.id.gendertv) ;
        dobTv=(TextView) view.findViewById(R.id.dobtv) ;
        occuptionTv=(TextView) view.findViewById(R.id.occupationtv) ;
        annual_incomeTv=(TextView) view.findViewById(R.id.annualtv) ;
        mother_nameTv=(TextView) view.findViewById(R.id.mothernametv) ;
        father_nameTv=(TextView) view.findViewById(R.id.fathernametv) ;
        marital_statsusTv=(TextView) view.findViewById(R.id.maritaltv) ;
        anniversary_dateTv=(TextView) view.findViewById(R.id.anniversarytv) ;
        religionTv=(TextView) view.findViewById(R.id.regisiontv) ;
        categoriesTv=(TextView) view.findViewById(R.id.categoriestv) ;
        statusTv=(TextView) view.findViewById(R.id.status) ;
        aadharcardTv=(TextView) view.findViewById(R.id.aadharCardtv) ;
        pancardTv=(TextView) view.findViewById(R.id.pancardtv) ;
        nominee_full_nameTv=(TextView) view.findViewById(R.id.fullnametvv) ;
        relationshipTv=(TextView) view.findViewById(R.id.releationshiptvv) ;
        nominee_genderTv=(TextView) view.findViewById(R.id.gendertvvv) ;
        nominee_dobTv=(TextView) view.findViewById(R.id.dobtvvv) ;
        nominee_ageTv=(TextView) view.findViewById(R.id.agetvvv) ;
        nominee_mobileTv=(TextView) view.findViewById(R.id.mobiletvvv) ;
        nominee_minorTv=(TextView) view.findViewById(R.id.marialv) ;
        aadharCardTvv=(TextView) view.findViewById(R.id.aadharCardtvv) ;
        PanCardTvv=(TextView) view.findViewById(R.id.pancardtvv) ;
        firstimage=(ImageView) view.findViewById(R.id.header_cover_image) ;
        secondimag=(CircleImageView) view.findViewById(R.id.profile_image) ;


        bankDetailCardView=(CardView) view.findViewById(R.id.card_bank_detail);
        accountNoTextView=(TextView) view.findViewById(R.id.account_no);
        bankNameTextView=(TextView) view.findViewById(R.id.bank_name);
        bankIFSCCodeTextView=(TextView) view.findViewById(R.id.bank_ifsc);
        bankBranchTextView=(TextView) view.findViewById(R.id.branch_name);
        bankAddressTextView=(TextView) view.findViewById(R.id.bank_address);


        idProofDetailCardView=(CardView) view.findViewById(R.id.id_proof_detail);
        firstIDProofNameTextView=(TextView) view.findViewById(R.id.first_id_proof);
        firstIDProofNoTextView=(TextView) view.findViewById(R.id.first_id_proof_no);
        firstIDProofAddressTextView=(TextView) view.findViewById(R.id.first_id_proof_address);
        secondIDProofNameTextView=(TextView) view.findViewById(R.id.second_id_proof);
        secondIDProofNoTextView=(TextView) view.findViewById(R.id.second_id_proof_no);
        secondIDProofAddressTextView=(TextView) view.findViewById(R.id.second_id_proof_address);


        ((HomeActivity)getActivity()).set_action_title(getString(R.string.profile));


        UserData =((HomeActivity)getActivity()).getUserDataToSession(getActivity());
        member_id=UserData.get(SessionManager.KEY_member_id);
        sessionManager = new SessionManager(getActivity());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        final Bundle bundle = getArguments();
        if (bundle != null && bundle.getString("is_from").equalsIgnoreCase("notification")) {
            updateNotificationStatus(bundle.getString("notification_id"));
        }

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    requireActivity().getSupportFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });
        getMemberDetails(member_id);
        return view;
    }

    private void getMemberDetails(String member_id) {
        progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(member_id));
        RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<MemberDetailsResponse> applicationsListResponesCall = RestHandler.getApiService().MEMBER_DETAILS_RESPONES_CALL(_member_id,_token);
        applicationsListResponesCall.enqueue(new Callback<MemberDetailsResponse>() {
            @Override
            public void onResponse(Call<MemberDetailsResponse> call, Response<MemberDetailsResponse> response) {
                if (response!=null){
                    if (response.body().getCode() == 200){
                        if(response.body().getMemberDetailsResult().getMemberDetail().getFirstName()!=null) {
                            member_full_nameTV.setText(response.body().getMemberDetailsResult().getMemberDetail().getFirstName() + " " + response.body().getMemberDetailsResult().getMemberDetail().getLastName());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getFormNo()!=null) {
                            form_noTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getFormNo().toString());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getReDate()!=null){
//
    /**/
                            try {
                                join_dateTv.setText(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(response.body().getMemberDetailsResult().getMemberDetail().getReDate().toString())));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        if(response.body().getMemberDetailsResult().getMemberDetail().getMemberId()!=null) {
                            member_idTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getMemberId().toString());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getBranchMi()!=null) {
                            branch_miTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getBranchMi().toString());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getFirstName()!=null) {
                            member_f_nameTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getFirstName());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getLastName()!=null) {
                            member_l_nameTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getLastName());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getEmail()!=null){
                            emailTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getEmail().toString());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getMobileNo()!=null) {
                            mobileTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getMobileNo().toString());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getGender()!=null) {
                        genderTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getGender().toString());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getDob()!=null) {

                            try {
                                dobTv.setText(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(response.body().getMemberDetailsResult().getMemberDetail().getDob().toString())));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

//     /**/                   dobTv.setText(new SimpleDateFormat("dd-MM-yyyy").format(response.body().getMemberDetailsResult().getMemberDetail().getDob().toString()));
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getOccupationId()!=null) {
                        occuptionTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getOccupationId().toString());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getAnnualIncome()!=null) {
                            annual_incomeTv.setText("\u20B9 "+response.body().getMemberDetailsResult().getMemberDetail().getAnnualIncome().toString());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getMotherName()!=null){
                            mother_nameTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getMotherName().toString());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getFatherHusband()!=null) {
                            father_nameTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getFatherHusband().toString());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getMaritalStatus()!=null) {
                            marital_statsusTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getMaritalStatus().toString());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getAnniversaryDate()!=null) {
                            try {
                                anniversary_dateTv.setText(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(response.body().getMemberDetailsResult().getMemberDetail().getAnniversaryDate().toString())));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
//           /**/                 anniversary_dateTv.setText(new SimpleDateFormat("dd-MM-yyyy").format(response.body().getMemberDetailsResult().getMemberDetail().getAnniversaryDate().toString()));
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getReligionId()!=null) {
                            religionTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getReligionId().toString());
                        }
                        if(response.body().getMemberDetailsResult().getMemberDetail().getSpecialCategoryId()!=null) {
                            categoriesTv.setText(response.body().getMemberDetailsResult().getMemberDetail().getSpecialCategoryId().toString());
                        }
                        if(response.body().getMemberDetailsResult().getIdProofDetail().getStatus()!=null) {
                            statusTv.setText(response.body().getMemberDetailsResult().getIdProofDetail().getStatus().toString());
                        }
                        if(response.body().getMemberDetailsResult().getNomineeDetail().getName()!=null) {
                            nominee_full_nameTv.setText(response.body().getMemberDetailsResult().getNomineeDetail().getName());
                        }
                        if(response.body().getMemberDetailsResult().getNomineeDetail().getRelation()!=null) {
                            relationshipTv.setText(response.body().getMemberDetailsResult().getNomineeDetail().getRelation().toString());
                        }
                        if(response.body().getMemberDetailsResult().getNomineeDetail().getGender()!=null) {
                            nominee_genderTv.setText(response.body().getMemberDetailsResult().getNomineeDetail().getGender().toString());
                        }
                        if(response.body().getMemberDetailsResult().getNomineeDetail().getDob()!=null) {
                            try {
                                nominee_dobTv.setText(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(response.body().getMemberDetailsResult().getNomineeDetail().getDob().toString())));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
//       /**/                     nominee_dobTv.setText(new SimpleDateFormat("dd-MM-yyyy").format(response.body().getMemberDetailsResult().getNomineeDetail().getDob()));
                        }
                        if(response.body().getMemberDetailsResult().getNomineeDetail().getAge()!=null) {
                            nominee_ageTv.setText(response.body().getMemberDetailsResult().getNomineeDetail().getAge().toString());
                        }
                        if(response.body().getMemberDetailsResult().getNomineeDetail().getMobileNo()!=null) {
                            nominee_mobileTv.setText(response.body().getMemberDetailsResult().getNomineeDetail().getMobileNo());
                        }
                        if(response.body().getMemberDetailsResult().getNomineeDetail().getIsMinor()!=null) {
                            nominee_minorTv.setText(response.body().getMemberDetailsResult().getNomineeDetail().getIsMinor().toString());
                        }
                        if(response.body().getMemberDetailsResult().getIdProofDetail().getFirstIdNo()!=null) {
                            aadharcardTv.setText(response.body().getMemberDetailsResult().getIdProofDetail().getFirstIdNo().toString());
                        }
                        if(response.body().getMemberDetailsResult().getIdProofDetail().getFirstIdTypeId()!=null) {
                            aadharCardTvv.setText(response.body().getMemberDetailsResult().getIdProofDetail().getFirstIdTypeId().toString());
                        }
                        if(response.body().getMemberDetailsResult().getIdProofDetail().getFirstIdTypeId().equals(response.body().getMemberDetailsResult().getIdProofDetail().getSecondIdTypeId()))
                        {

                        }else {
                            if (response.body().getMemberDetailsResult().getIdProofDetail().getSecondIdTypeId() != null) {
                                PanCardTvv.setText(response.body().getMemberDetailsResult().getIdProofDetail().getSecondIdTypeId().toString());
                            }

                            if (response.body().getMemberDetailsResult().getIdProofDetail().getSecondIdNo() != null) {
                                pancardTv.setText(response.body().getMemberDetailsResult().getIdProofDetail().getSecondIdNo().toString());
                            }
                        }


                        Glide
                                .with(getActivity())
                                .load(response.body().getMemberDetailsResult().getMemberDetail().getImageurl())
                                .centerCrop()
                                .placeholder(R.drawable.ic_baseline_person_24)
                                .into(firstimage);
                        Glide
                                .with(getActivity())
                                .load(response.body().getMemberDetailsResult().getMemberDetail().getImageurl())
                                .centerCrop()
                                .placeholder(R.drawable.ic_baseline_person_24)
                                .into(secondimag);


                        if (response.body().getMemberDetailsResult().getBankDetail() != null){
                            bankDetailCardView.setVisibility(View.VISIBLE);
                            accountNoTextView.setText(response.body().getMemberDetailsResult().getBankDetail().getAccountNo());
                            bankNameTextView.setText(response.body().getMemberDetailsResult().getBankDetail().getBankName());
                            bankIFSCCodeTextView.setText(response.body().getMemberDetailsResult().getBankDetail().getIfscCode());
                            bankBranchTextView.setText(response.body().getMemberDetailsResult().getBankDetail().getBranchName());
                            bankAddressTextView.setText(response.body().getMemberDetailsResult().getBankDetail().getAddress());
                        }else {
                            bankDetailCardView.setVisibility(View.GONE);
                        }

                        if (response.body().getMemberDetailsResult().getIdProofDetail() != null){
                            idProofDetailCardView.setVisibility(View.VISIBLE);
                            if (response.body().getMemberDetailsResult().getIdProofDetail().getFirstIdNo() != null
                            || !response.body().getMemberDetailsResult().getIdProofDetail().getFirstIdNo().equalsIgnoreCase("")){
                                firstIDProofNameTextView.setText(response.body().getMemberDetailsResult().getIdProofDetail().getFirstIdTypeId());
                                firstIDProofNoTextView.setText(response.body().getMemberDetailsResult().getIdProofDetail().getFirstIdNo());
                                firstIDProofAddressTextView.setText(response.body().getMemberDetailsResult().getIdProofDetail().getFirstAddress());
                                if (response.body().getMemberDetailsResult().getIdProofDetail().getSecondIdNo() != null
                                        || !response.body().getMemberDetailsResult().getIdProofDetail().getSecondIdNo().equalsIgnoreCase("")){
                                    secondIDProofNameTextView.setText(response.body().getMemberDetailsResult().getIdProofDetail().getSecondIdTypeId());
                                    secondIDProofNoTextView.setText(response.body().getMemberDetailsResult().getIdProofDetail().getSecondIdNo());
                                    secondIDProofAddressTextView.setText(response.body().getMemberDetailsResult().getIdProofDetail().getSecondAddress());
                                }else {
                                    secondIDProofNameTextView.setVisibility(View.GONE);
                                    secondIDProofNoTextView.setVisibility(View.GONE);
                                    secondIDProofAddressTextView.setVisibility(View.GONE);
                                }

                            }else {
                                firstIDProofNameTextView.setVisibility(View.GONE);
                                firstIDProofNoTextView.setVisibility(View.GONE);
                                firstIDProofAddressTextView.setVisibility(View.GONE);
                            }

                        }else {
                            idProofDetailCardView.setVisibility(View.GONE);
                        }

                        progressBar.setVisibility(View.GONE);
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    }else {
                        progressBar.setVisibility(View.GONE);
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(getActivity(), response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    progressBar.setVisibility(View.GONE);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    Toast.makeText(getActivity(), "Server Error...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MemberDetailsResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });
    }

    private void updateNotificationStatus(String notificationId) {
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

}