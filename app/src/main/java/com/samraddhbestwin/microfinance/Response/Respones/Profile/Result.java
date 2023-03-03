package com.samraddhbestwin.microfinance.Response.Respones.Profile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("user_profile")
@Expose
private UserProfile userProfile;

public UserProfile getUserProfile() {
return userProfile;
}

public void setUserProfile(UserProfile userProfile) {
this.userProfile = userProfile;
}

}