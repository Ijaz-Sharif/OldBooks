package is.fb.oldbooks;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Donate_books extends AppCompatActivity {
    private TextInputLayout book_name,mobile_num,name;
    private  RadioButton inter,matric;
    private  DatabaseReference databaseReference;
    private  FirebaseAuth firebaseAuth;
    private  FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_books);
        book_name=findViewById(R.id.book_name1);
        mobile_num=findViewById(R.id.mbl_number);
        name=findViewById(R.id.name1);
        inter=findViewById(R.id.inter1);
        matric=findViewById(R.id.matric1);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
    }
    public void donate(View v) {
        if (TextUtils.isEmpty(book_name.getEditText().getText().toString().trim()) || TextUtils.isEmpty(mobile_num.getEditText().getText().toString().trim()) ||
                TextUtils.isEmpty(name.getEditText().getText().toString().trim())) {
            Toast.makeText(this, "enter all data", Toast.LENGTH_LONG).show();
        } else {
            if (inter.isChecked()) {
                databaseReference = FirebaseDatabase.getInstance().getReference("donation").child(name.getEditText().getText().toString().trim());
                databaseReference.child("book").setValue(book_name.getEditText().getText().toString().trim());
                databaseReference.child("catgare").setValue("Inter");
                databaseReference.child("mail").setValue(firebaseUser.getEmail());
                databaseReference.child("mobile").setValue(mobile_num.getEditText().getText().toString().trim());
                databaseReference.child("name").setValue(name.getEditText().getText().toString().trim());
                Toast.makeText(this, "thanks for donation", Toast.LENGTH_LONG).show();
            } else {
                databaseReference = FirebaseDatabase.getInstance().getReference("donation").child(name.getEditText().getText().toString().trim());
                databaseReference.child("book").setValue(book_name.getEditText().getText().toString().trim());
                databaseReference.child("catgare").setValue("Matric");
                databaseReference.child("mail").setValue(firebaseUser.getEmail());
                databaseReference.child("mobile").setValue(mobile_num.getEditText().getText().toString().trim());
                databaseReference.child("name").setValue(name.getEditText().getText().toString().trim());
                Toast.makeText(this, "thanks for donation", Toast.LENGTH_LONG).show();
            }
        }
    }
}
