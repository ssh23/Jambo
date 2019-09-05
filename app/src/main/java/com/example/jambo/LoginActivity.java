package com.example.jambo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextpassword;
    private EditText editTextphoneno;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextInputLayout textInputLayoutphoneno;
    private TextInputLayout textInputLayoutpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog =new ProgressDialog(this);
        editTextpassword =(EditText)findViewById(R.id.password);
        editTextphoneno =(EditText)findViewById(R.id.phoneno);
        textInputLayoutphoneno=(TextInputLayout)findViewById(R.id.TextInput_phoneno);
        textInputLayoutpassword=(TextInputLayout)findViewById(R.id.TextInput_password);
    }

    public boolean chkinternet()
    {
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public void login(View view)
    {
        String phoneno = editTextphoneno.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        if(TextUtils.isEmpty(phoneno))
        {
            textInputLayoutphoneno.setError("Field can't be empty");
            return;
        }
        else
        {
            textInputLayoutphoneno.setError(null);
        }
        if(TextUtils.isEmpty(password))
        {
            textInputLayoutpassword.setError("Field can't be empty");
            return;
        }
        else
        {
            textInputLayoutpassword.setError(null);
        }
        boolean chkinternt=chkinternet();
        if(!chkinternt)
        {
            Toast.makeText(this,"No internet Connection..",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Authenticating User...");
        progressDialog.show();

    }
    public void gotoSignup(View view)
    {
        Intent SignupIntent= new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(SignupIntent);
        finish();
    }

}
