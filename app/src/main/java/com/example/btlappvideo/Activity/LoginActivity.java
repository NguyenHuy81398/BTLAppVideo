package com.example.btlappvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btlappvideo.Adapter.SQLHelperUser;
import com.example.btlappvideo.Model.User;
import com.example.btlappvideo.R;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    SQLHelperUser sqlHelperUser;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnLogin = findViewById(R.id.btnLogin);
        final EditText etUsername = findViewById(R.id.etUserName);
        final EditText etPassword = findViewById(R.id.etPassword);

        Intent intent_login = getIntent();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_register = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent_register);
            }
        });

        sqlHelperUser = new SQLHelperUser(getBaseContext());


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userList = sqlHelperUser.getAllUserAdvanced();
                int dem = 0;

                for (int i=0; i<userList.size(); i++){

                    if (etUsername.getText().toString().equals(userList.get(i).getUsername())){
                        if(etPassword.getText().toString().equals(userList.get(i).getPassword())){
                            Toast.makeText(getBaseContext(), "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
                            dem++;
                        }
                    }
                }

                if(dem == 0){
                    Toast.makeText(getBaseContext(), "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
