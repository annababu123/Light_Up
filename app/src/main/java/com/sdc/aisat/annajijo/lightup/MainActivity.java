package com.sdc.aisat.annajijo.lightup;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.sdc.aisat.annajijo.lightup.authentication.LoginPage;
import com.sdc.aisat.annajijo.lightup.authentication.SignupPage;

public class MainActivity extends AppCompatActivity {

    Button signinBtn;
    Button signupBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signupBtn = (Button) findViewById(R.id.btnSignUp);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,SignupPage.class));
            }
        });


        signinBtn = (Button) findViewById(R.id.btnSingIn);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,LoginPage.class));
            }
        });
    }
}
