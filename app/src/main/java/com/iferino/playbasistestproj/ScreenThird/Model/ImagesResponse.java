package com.iferino.playbasistestproj.ScreenThird.Model;


import android.util.Log;
import android.widget.ListView;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class ImagesResponse {

@SerializedName("resources")
private List<Image> resourse;
@SerializedName("next_cursor")
private String nextPage;

public List<Image> getResourse() {
	return resourse;
}

public void setResourse(List<Image> resourse) {
	this.resourse = resourse;
}

public String getNextPage() {
	return nextPage;
}

public void setNextPage(String nextPage) {
	this.nextPage = nextPage;
}
}
