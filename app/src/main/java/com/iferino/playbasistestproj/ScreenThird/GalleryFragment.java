package com.iferino.playbasistestproj.ScreenThird;

import android.media.ImageReader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.iferino.playbasistestproj.R;
import com.iferino.playbasistestproj.ScreenThird.Adapter.ImageAdapter;
import com.iferino.playbasistestproj.ScreenThird.Model.Image;
import com.iferino.playbasistestproj.ScreenThird.Model.ImagesResponse;
import com.iferino.playbasistestproj.ScreenThird.rest.ApiClient;
import com.iferino.playbasistestproj.ScreenThird.rest.ApiInterface;
import com.iferino.playbasistestproj.ScreenThird.rest.Helper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {
private ImagesResponse result_body;
private ImageAdapter imageAdapter;
@Override
public View onCreateView(LayoutInflater inflater,
                         @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {

	View view = inflater.inflate(R.layout.fragment_gallery, container, false);

	final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
	final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);

	recyclerView.setHasFixedSize(true);
	RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
	recyclerView.setLayoutManager(layoutManager);

	//connect cloudinary api
	final String basicAuth = "Basic " + Base64.encodeToString(String.format("%s:%s", Helper.getConfigValue(getActivity(),
	                                                                                                  "API_KEY"),
	                                                                  Helper.getConfigValue(getActivity(),"API_SECRET"))
	                                                          .getBytes(),Base64.NO_WRAP);
	final  ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
	Call<ImagesResponse> call = apiService.getAllImages(basicAuth);
	call.enqueue(new Callback<ImagesResponse>() {
		@Override public void onResponse(Call<ImagesResponse> call, final Response<ImagesResponse> response) {
			if (response.isSuccessful()) {
				result_body = response.body();
				List<Image> images = result_body.getResourse();
				Log.i("TAG", "Number of images: " + images.size());
				imageAdapter = new ImageAdapter(images,getContext());
				recyclerView.setAdapter(imageAdapter);
			}
		}

		@Override public void onFailure(Call<ImagesResponse> call, Throwable t) {
			Log.e("TAG",t.toString());
		}
	});

	swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
		@Override public void onRefresh() {
			Log.i("TAG", "refresh");
			if(result_body.getNextPage()!=null){
				Log.i("TAG", "nextpage");
				//call next page
				Call<ImagesResponse> call = apiService.getNextPage(basicAuth,result_body.getNextPage());
				call.enqueue(new Callback<ImagesResponse>() {
					@Override public void onResponse(Call<ImagesResponse> call,
					                                 Response<ImagesResponse> response)
					{
						if (response.isSuccessful()){
							Log.i("TAG","response of next page");
							result_body = response.body();
							List<Image> images = result_body.getResourse();
							imageAdapter.addAll(images);
							swipeRefreshLayout.setRefreshing(false);
						}

					}

					@Override public void onFailure(Call<ImagesResponse> call, Throwable t) {
						Log.e("TAG",t.toString());
					}
				});

			}
			swipeRefreshLayout.setRefreshing(false);

		}
	});

	return view;
}

}
