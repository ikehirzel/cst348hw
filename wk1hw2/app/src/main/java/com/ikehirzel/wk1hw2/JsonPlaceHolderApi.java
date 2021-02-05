package com.ikehirzel.wk1hw2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
	@GET("posts")
	Call<List<Post>> getPosts();
}
