package com.example.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import com.google.firebase.auth.FirebaseUser;



import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    Button login, register;

    private final static String TAG =  StartActivity.class.getSimpleName();

   FirebaseUser firebaseUser;

   protected void onStart(){
       super.onStart();

       firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

       // check the user is null
       if (firebaseUser != null){
           Log.d(TAG,"Get in"  );
           Intent intent = new Intent(StartActivity.this, MainActivity.class);
           startActivity(intent);
           finish();
       }
       else{
           Log.d(TAG," Do not get in"  );
       }


   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();


        // check the user is null

        /*
       if (firebaseUser != null){
            Log.d(TAG,"Get in"  );
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
          }
          else{
           Log.d(TAG," Do not get in"  );
       }

*/

        login = findViewById(R.id.login);
        register = findViewById((R.id.register));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this,RegisterActivity.class));
            }
        });
    }
}
