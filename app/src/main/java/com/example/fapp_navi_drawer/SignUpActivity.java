package com.example.fapp_navi_drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_actitvity);
        TextView login = (TextView) findViewById(R.id.lnkLogin);
        login.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);


            }
        });
        Button register=(Button)findViewById(R.id.btnLogin);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(validate()) {
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                }


            }

        });
    }

    private boolean validate(){
        boolean result=true;
        EditText uname=findViewById(R.id.txtName);
        String username=uname.getText().toString();
        EditText pwd=findViewById(R.id.txtPwd);
        String password=pwd.getText().toString();
        EditText age=findViewById(R.id.txtAlter);
        String alter=age.getText().toString();
        EditText height=findViewById(R.id.txtGroesse);
        String groesse=height.getText().toString();
        EditText weight=findViewById(R.id.txtGewicht);
        String gewicht= weight.getText().toString();
        if(username.isEmpty()){
            uname.setError("Username not empty");
            result=false;
        }
        if(password.isEmpty()){
            pwd.setError("Password not empty");
        }
        if(alter.isEmpty()){
            age.setError("Alter not empty");
        }
        if(groesse.isEmpty()){
            height.setError("Groesse not empty");
        }
        if(gewicht.isEmpty()){
            weight.setError("Gewicht not empty");
        }
        return result;
    }
}
