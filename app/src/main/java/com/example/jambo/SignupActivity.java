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

public class SignupActivity extends AppCompatActivity {

    private EditText editTextconfirmpassword;
    private EditText editTextpassword;
    private EditText editTextphoneno;
    private TextInputLayout textInputLayoutphoneno;
    private TextInputLayout textInputLayoutpassword;
    private TextInputLayout textInputLayoutconfirmpassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog =new ProgressDialog(this);
        editTextconfirmpassword =(EditText)findViewById(R.id.confirmpassword);
        editTextpassword =(EditText)findViewById(R.id.password);
        editTextphoneno =(EditText)findViewById(R.id.phoneno);
        textInputLayoutconfirmpassword=(TextInputLayout)findViewById(R.id.Inputsignup_confirmpassword);
        textInputLayoutpassword=(TextInputLayout)findViewById(R.id.Inputsignup_password);
        textInputLayoutphoneno=(TextInputLayout)findViewById(R.id.Inputsignup_phoneno);
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
    public void signup(View view)
    {
        String scpassword = editTextconfirmpassword.getText().toString().trim();
        String smail = editTextphoneno.getText().toString().trim();
        String spassword = editTextpassword.getText().toString().trim();
        if(TextUtils.isEmpty(smail))
        {
            textInputLayoutphoneno.setError("Field can't be empty");
            return;
        }
        else
        {
            textInputLayoutphoneno.setError(null);
        }
        if(TextUtils.isEmpty(scpassword))
        {
            textInputLayoutconfirmpassword.setError("Field can't be empty");
            return;
        }
        else
        {
            textInputLayoutconfirmpassword.setError(null);
        }
        if(TextUtils.isEmpty(spassword))
        {
            textInputLayoutpassword.setError("Field can't be empty");
            return;
        }
        else
        {
            textInputLayoutpassword.setError(null);

        }
        if(!spassword.equals(scpassword))
        {
            textInputLayoutconfirmpassword.setError("Password doesn't match");
            return;
        }
        else
        {
            textInputLayoutconfirmpassword.setError(null);
        }
        boolean chkinternt=chkinternet();
        if(!chkinternt)
        {
            Toast.makeText(this,"No internet Connection..",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        //Firebase to done here

    }
    public void gotoLogin(View view)
    {
        Intent LoginIntent= new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(LoginIntent);
        finish();
    }
}
