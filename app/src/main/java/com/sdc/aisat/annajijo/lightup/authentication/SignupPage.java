package com.sdc.aisat.annajijo.lightup.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.sdc.aisat.annajijo.lightup.R;
import com.sdc.aisat.annajijo.lightup.my_class.PersonalRecord;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignupPage extends AppCompatActivity {


    Button signupBtn;
    EditText email, password;
    String stringEmail, stringPassword;
    //////////
    String stringUid;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    FirebaseDatabase db;
    DatabaseReference ref;

    //////////
    PersonalRecord personalRecord;
    ArrayList<PersonalRecord> personalRecordArrayList;
///////////////

    EditText firstNameEt, lastNameEt;

    String stringFirstName, stringLastName;

    ///////////



    /////////////
    ProgressDialog progressDialog;
    /////////////

/////////spinner/////
    Spinner dropdown;
    String stringDropdownValue;
    ///////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        /////////INSERT/////////

//        db = FirebaseDatabase.getInstance().;
//        ref = db.getReference().child("user");

        personalRecordArrayList = new ArrayList<>();


        firstNameEt = (EditText) findViewById(R.id.etUserFirstNameId);
        lastNameEt = (EditText) findViewById(R.id.etUserLastNameId);


        ///////////INSERT////////



        /////////
        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseUser = firebaseAuth.getCurrentUser();
//
//        if (firebaseAuth.getCurrentUser() == null){
//
//            // startActivity(new Intent(this, Login.class));
//        }else {
//            stringUid = firebaseUser.getUid();//
//
//        }

        ///////////



        ////////spiner///////
        dropdown = (Spinner)findViewById(R.id.spinnerId);
        String[] items = new String[]{"A +", "A-", "B+", "B-", "O+", "O-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                stringDropdownValue = dropdown.getItemAtPosition(position).toString();
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
      //  stringDropdownValue = dropdown.getSelectedItem().toString();
        //////////spiner/////////






        ////////
        progressDialog = new ProgressDialog(this);
        ///////////





        email = (EditText) findViewById(R.id.idEmailSs);
        password = (EditText) findViewById(R.id.idPasswordSs);




        signupBtn = (Button) findViewById(R.id.idSignupBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                ////////signup ///
                stringEmail = email.getText().toString().trim();
                stringPassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(stringEmail)){
                    // email is empty
                    Toast.makeText(SignupPage.this, "Email is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(stringPassword)){
                    // password is empty
                    Toast.makeText(SignupPage.this, "Password is empty", Toast.LENGTH_SHORT).show();
                    //stoppin the finction executting further
                    return;
                }



///////////////////////
                progressDialog.setMessage("registering user...");
                progressDialog.show();
                /////////////////////////



                firebaseAuth.createUserWithEmailAndPassword(stringEmail,stringPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            /////////
                            //finish();
                            ///////////insert/////////

                            stringFirstName = firstNameEt.getText().toString().trim();
                            stringLastName = lastNameEt.getText().toString().trim();



                            personalRecord = new PersonalRecord("data", firebaseAuth.getCurrentUser().getUid(), stringFirstName, stringLastName, stringDropdownValue, stringEmail);
                            // ref.child(personalRecord.getKey()).child(stringUid).setValue(personalRecord);

                            FirebaseDatabase.getInstance().getReference().child("users").child(personalRecord.getUid()).child(personalRecord.getKey()).setValue(personalRecord);

                            firstNameEt.setText("");
                            lastNameEt.setText("");


                            ///////////insert/////////
                            /////////
                            Toast.makeText(SignupPage.this, "successfully created", Toast.LENGTH_SHORT).show();
                            /////////
                            progressDialog.hide();
                            ////////////
                            startActivity(new Intent(SignupPage.this,LoginPage.class));
                        }
                        else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                Log.d("EMail1" , "email :" + email);
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                Log.d("Pwd1" , "email :" + email);

                            } catch (FirebaseNetworkException e) {
                                Toast.makeText(SignupPage.this,"error_message_failed_sign_in_no_network",Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Log.e("Exception", e.getMessage());
                            }
                            Toast.makeText(SignupPage.this, "Not created! Error", Toast.LENGTH_SHORT).show();
                            /////////
                            progressDialog.hide();
                            ////////////
                        }


                        ////////signup /////////////

                    }
                });
            }
        });
    }
}
