package com.ikehirzel.wk1hw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private EditText usernameEdit;
	private EditText passwordEdit;
	private Button loginButton;

	final private String[] usernames = { "din_djarin",		"ike", 		"monte" };
	final private String[] passwords = { "baby_yoda_ftw",	"password", "csumb" };

	public boolean isPassword(int userId, String password) {
		return passwords[userId - 1].equals(password);
	}

	public int getUserId(String username) {
		for (int i = 0; i < usernames.length; i++) {
			if (username.equals(usernames[i])) return i + 1;
		}
		return 0;
	}

	public Intent getLandingPageIntent(String username, String password) {
		int id = getUserId(username);
		if (id == 0) {
			usernameEdit.setError("User '" + username + "' not found");
			return null;
		}
		if (!isPassword(id, password)) {
			passwordEdit.setError("Incorrect password");
			return null;
		}
		Intent intent = new Intent(MainActivity.this, ViewActivity.class);
		intent.putExtra("id", id);
		intent.putExtra("username", username);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		usernameEdit = findViewById(R.id.username_edit);
		passwordEdit = findViewById(R.id.password_edit);
		loginButton = findViewById(R.id.login_button);

		usernameEdit.setHint("Username");
		passwordEdit.setHint("Password");

		loginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String username = usernameEdit.getText().toString();
				String password = passwordEdit.getText().toString();
				Intent intent = getLandingPageIntent(username, password);
				if (intent != null) v.getContext().startActivity(intent);
			}
		});
	}
}