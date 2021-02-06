package com.ikehirzel.wk1hw2;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewActivity extends AppCompatActivity {

	private int userId = 0;
	private String username;
	private TextView welcomeView;
	private TextView postResponseView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);

		Intent intent = getIntent();

		username = intent.getStringExtra("username");
		userId = intent.getIntExtra("id", -1);

		welcomeView = findViewById(R.id.welcome_view);
		postResponseView = findViewById(R.id.post_reponse_view);

		welcomeView.setText("Welcome " + username);
		postResponseView.setMovementMethod(new ScrollingMovementMethod());

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://jsonplaceholder.typicode.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		JsonPlaceHolderApi api = retrofit.create(JsonPlaceHolderApi.class);
		Call<List<Post>> call = api.getPosts();

		// calling get request
		call.enqueue(new Callback<List<Post>>() {
			@Override
			public void onResponse(Call<List<Post>> call, Response<List<Post>> res) {
				if (!res.isSuccessful()) {
					postResponseView.setText("Code: " + res.code());
					return;
				}

				List<Post> posts = res.body();

				for (Post post : posts) {
					if (post.getUserId() != userId) continue;
					String content = "";
					content += "ID: " + post.getId() + "\n";
					content += "User ID: " + post.getUserId() + "\n";
					content += "Title: " + post.getTitle() + "\n";
					content += "Body: " + post.getBody() + "\n\n";
					postResponseView.append(content);
				}
			}

			@Override
			public void onFailure(Call<List<Post>> call, Throwable t) {
				postResponseView.setText(t.getMessage());
			}
		});
		return;
	}
}
