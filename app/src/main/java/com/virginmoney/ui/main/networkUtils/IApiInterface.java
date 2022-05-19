package com.virginmoney.ui.main.networkUtils;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface IApiInterface {

    String Live_BASE_URL = "https://61e947967bc0550017bc61bf.mockapi.io/";

    @Headers("Connection:close")
    @GET
    Call<ResponseBody> getAPI(@Url String url);

    @POST()
    Call<ResponseBody> PostAPI(@Url String url, @Body RequestBody body);

    @POST()
    Call<ResponseBody> PostJSONAPI(@Url String url, @Body RequestBody body);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/v1/people")
    Call<ResponseBody> getPeopleData();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/v1/rooms")
    Call<ResponseBody> getRoomData();

}

