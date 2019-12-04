package com.example.btlappvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btlappvideo.Adapter.SQLHelperUser;
import com.example.btlappvideo.R;

public class RegisterActivity extends AppCompatActivity {

    Button btnOke;
    EditText etUsername, etPassword, etFullname, etEmail;
    SQLHelperUser sqlHelperUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent_Register = getIntent();

        setContentView(R.layout.activity_register);

        btnOke = findViewById(R.id.btnOke);
        etUsername = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        etFullname = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);

        sqlHelperUser = new SQLHelperUser(getBaseContext());

        btnOke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String fullname = etFullname.getText().toString();
                String email = etEmail.getText().toString();

                sqlHelperUser.insertUser(username, password, fullname, email);

                Toast.makeText(getBaseContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
            }
        });
    }
}
