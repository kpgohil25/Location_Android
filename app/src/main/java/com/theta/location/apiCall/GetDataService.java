package com.theta.location.apiCall;

import androidx.annotation.Keep;

import com.theta.location.models.UserModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * The interface Get data service.
 */
public interface GetDataService {

    @GET("/api/users")
    Call<UserModel> getAllUsers(@Query("page") int page);
}