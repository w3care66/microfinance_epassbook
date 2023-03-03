package com.samraddhbestwin.microfinance.Rest;


import com.samraddhbestwin.microfinance.Model.AssociateLoanDetail;
import com.samraddhbestwin.microfinance.Model.SSBModelResponse;
import com.samraddhbestwin.microfinance.Response.DashBoardResponse;
import com.samraddhbestwin.microfinance.Response.GSTAmountResponse;
import com.samraddhbestwin.microfinance.Response.LoanListResponse;
import com.samraddhbestwin.microfinance.Response.LoginResponse;
import com.samraddhbestwin.microfinance.Response.MemberDetailsResponse;
import com.samraddhbestwin.microfinance.Response.NotificationDetailResponse;
import com.samraddhbestwin.microfinance.Response.NotificationListResponse;
import com.samraddhbestwin.microfinance.Response.OtpSendResponse;
import com.samraddhbestwin.microfinance.Response.OtpVerifyResponse;
import com.samraddhbestwin.microfinance.Response.PaymentOTPResponse;
import com.samraddhbestwin.microfinance.Response.PaymentSubmitResponse;
import com.samraddhbestwin.microfinance.Response.Respones.CommonResponseWithoutData;
import com.samraddhbestwin.microfinance.Response.Respones.OTPSendResponse;
import com.samraddhbestwin.microfinance.Response.SSBDepositResponse;
import com.samraddhbestwin.microfinance.Response.TranscationDetailsResponse;
import com.samraddhbestwin.microfinance.Response.TranscationListResponse;

import java.security.acl.LastOwnerException;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @Multipart
    @POST("sendotp")
    Call<LoginResponse> OTP_SEND_RESPONES_CALL(
            @Part("member_id") RequestBody _member_id,
            @Part("mobile_token") RequestBody _fcmToken
    );


    @Multipart
    @POST("otpvarified")
    Call<OtpVerifyResponse> Otp_Varified_RESPONES_CALL(
            @Part("member_id") RequestBody _member_id,
            @Part("otp") RequestBody _otp,
           @Part("token") RequestBody _token
    );

    @Multipart
    @POST("setupicode")
    Call<OtpVerifyResponse> SETUP_PIN_RESPONES_CALL(
            @Part("member_id") RequestBody _member_id,
            @Part("upi") RequestBody _upi,
            @Part("token") RequestBody _token,
            @Part("mobile_token") RequestBody _fcmToken
    );
    @Multipart
    @POST("loginwithupi")
    Call<OtpVerifyResponse> VERIFY_PIN_RESPONES_CALL(
            @Part("member_id") RequestBody _member_id,
            @Part("upi") RequestBody _upi,
            @Part("token") RequestBody _token
    );

    @Multipart
    @POST("memberdetails")
    Call<MemberDetailsResponse> MEMBER_DETAILS_RESPONES_CALL(
            @Part("member_id") RequestBody _member_id,
            @Part("token") RequestBody _token
    );

    @Multipart
    @POST("memberinvestments")
    Call<DashBoardResponse> Dashboard_RESPONES_CALL(
            @Part("member_id") RequestBody _member_id,
            @Part("account_number") RequestBody _account_number,
            @Part("token") RequestBody _token

    );
    @Multipart
    @POST("investmenttransactions")
    Call<TranscationListResponse> TRANSCATION_LIST_RESPONES_CALL(
            @Part("member_id") RequestBody _member_id,
            @Part("investment_id") RequestBody _investment_id,
            @Part("token") RequestBody _token,
            @Part("type") RequestBody _type
    );

    @Multipart
    @POST("viewtransactiondetails")
    Call<TranscationDetailsResponse> TRANSCATION_DETAILS_RESPONES_CALL(
            @Part("member_id") RequestBody _member_id,
            @Part("investment_id") RequestBody _investment_id,
            @Part("transaction_id") RequestBody _transcation_id,
            @Part("token") RequestBody _token,
            @Part("type") RequestBody _type
    );

    @Multipart
    @POST("get_ssb_account_balance")
    Call<SSBModelResponse> SSB_AMOUNT_DETAILS(
            @Part("member_id") RequestBody _member_id,
            @Part("token") RequestBody _token
    );

    @Multipart
    @POST("get_notification")
    Call<NotificationListResponse> NOTIFICATION_LIST(
            @Part("member_id") RequestBody _member_id,
            @Part("token") RequestBody _token
    );

    @Multipart
    @POST("get_account_details")
    Call<SSBDepositResponse> SSB_DEPOSIT_LIST(
            @Part("member_id") RequestBody _member_id,
            @Part("token") RequestBody _token,
            @Part("type") RequestBody _type
    );

    @Multipart
    @POST("get_account_details")
    Call<LoanListResponse> LOAN_DATA_LIST(
            @Part("member_id") RequestBody _member_id,
            @Part("token") RequestBody _token,
            @Part("type") RequestBody _type
    );

    @Multipart
    @POST("send_debit_card_amount")
    Call<CommonResponseWithoutData> DEBIT_AMOUNT(
            @Part("member_id") RequestBody _member_id,
            @Part("token") RequestBody _token,
            @Part("amount") RequestBody _type
    );

    @Multipart
    @POST("send_loan_otp")
    Call<PaymentOTPResponse> PAYMENT_OTP(
            @Part("member_id") RequestBody _member_id,
            @Part("token") RequestBody _token,
            @Part("type") RequestBody _type
    );

    @Multipart
    @POST("submit_associate_loan_payment")
    Call<PaymentSubmitResponse> SUBMIT_LOAN_PAYMENT(
            @Part("member_id") RequestBody _member_id,
            @Part("token") RequestBody _token,
            @Part("type") RequestBody _type,
            @Part("loan_id") RequestBody _loan_id,
            @Part("payment_mode") RequestBody _payment_mode,
            @Part("emi_amount") RequestBody _emiAmount
    );

    @Multipart
    @POST("submit_associate_group_loan_payment")
    Call<PaymentSubmitResponse> SUBMIT_GROUP_LOAN_PAYMENT(
            @Part("member_id") RequestBody _member_id,
            @Part("token") RequestBody _token,
            @Part("type") RequestBody _type,
            @Part("loan_id") RequestBody _loan_id,
            @Part("payment_mode") RequestBody _payment_mode,
            @Part("emi_amount") RequestBody _emiAmount
    );

    @Multipart
    @POST("get_notification_details")
    Call<NotificationDetailResponse> NOTIFICATION_DETAILS(
            @Part("member_id") RequestBody _member_id,
            @Part("token") RequestBody _token,
            @Part("type") RequestBody _type,
            @Part("loan_id") RequestBody _loan_id
    );

    @Multipart
    @POST("submit_investment_payment")
    Call<PaymentSubmitResponse> SUBMIT_INVESTMENT_PAYMENT(
            @Part("member_id") RequestBody _member_id,
            @Part("token") RequestBody _token,
            @Part("id") RequestBody _loan_id,
            @Part("payment_mode") RequestBody _payment_mode,
            @Part("deposit_amount") RequestBody _emiAmount
    );

    @Multipart
    @POST("notification_read_update")
    Call<CommonResponseWithoutData> UPDATE_NOTIFICATION_STATUS(
            @Part("member_id") RequestBody member_id,
            @Part("token") RequestBody token,
            @Part("id") RequestBody notificationID);
}

