package com.iferino.playbasistestproj.ScreenThird.rest;

import com.iferino.playbasistestproj.ScreenThird.Model.Image;
import com.iferino.playbasistestproj.ScreenThird.Model.ImagesResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiInterface {

@GET("resources/image?direction=asc")
Call<ImagesResponse> getAllImages (@Header("Authorization") String authorization);

@GET("resources/image?direction=asc")
Call<ImagesResponse> getNextPage(@Header("Authorization") String authorization, @Query("next_cursor") String nextPage);
}
