package is.fb.oldbooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {
    RelativeLayout signup,signin,desion;
    // for signup
    TextInputLayout user_name,user_mail,user_pas;
    // for signin
    TextInputLayout mail,pas;
    //firebase authentication
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signin=findViewById(R.id.signin);
        signup=findViewById(R.id.signup);
        desion=findViewById(R.id.decios);
        desion.setVisibility(View.VISIBLE);
        signin.setVisibility(View.INVISIBLE);
        signup.setVisibility(View.INVISIBLE);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void desionForLogin(View v){
        signin.setVisibility(View.VISIBLE);
        signup.setVisibility(View.INVISIBLE);
        desion.setVisibility(View.INVISIBLE);

    }
    public void desionForsigup(View v){
        signup.setVisibility(View.VISIBLE);
        signin.setVisibility(View.INVISIBLE);
        desion.setVisibility(View.INVISIBLE);
    }
    // after sucessful signup move to main screen
    public void register(final View v){
        user_name=findViewById(R.id.user_nam);
        user_mail=findViewById(R.id.user_mail_input);
        user_pas=findViewById(R.id.user_pas_input);

        if(TextUtils.isEmpty(user_mail.getEditText().getText().toString().trim())||TextUtils.isEmpty(user_pas.getEditText().getText().toString().trim())){
            Toast.makeText(Signup.this,"enter all values",Toast.LENGTH_LONG).show();
        }
        else {
            firebaseAuth.createUserWithEmailAndPassword(user_mail.getEditText().getText().toString().trim(),user_pas.getEditText().getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        final FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                        firebaseUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                if(!firebaseUser.isEmailVerified()){
                                    Toast.makeText(Signup.this, "verified your email\n got to your inbox ", Toast.LENGTH_LONG).show();
                            }
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Signup.this, "entered  your verified mail or password", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                    else {
                        Toast.makeText(Signup.this, "entered  your verified mail or password", Toast.LENGTH_LONG).show();
                    }
                }
    });
        }
    }
    // after sucessful signin move to main screen
    public void login(View v){
        mail=findViewById(R.id.mail_input_admin);
        pas=findViewById(R.id.pas_input_admin);
        if(TextUtils.isEmpty(mail.getEditText().getText().toString().trim())||TextUtils.isEmpty(pas.getEditText().getText().toString().trim())){
            Toast.makeText(this,"enter both values",Toast.LENGTH_LONG).show();
        }
        else {
            firebaseAuth.signInWithEmailAndPassword(mail.getEditText().getText().toString().trim(),pas.getEditText().getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(Signup.this,"wrong mail or password"+task.getException(),Toast.LENGTH_LONG).show();
                    }
                    else if(task.isSuccessful()){

                        startActivity(new Intent(Signup.this,MainActivity.class));
                        finish();
                    }
                }
            });
        }
    }
}
