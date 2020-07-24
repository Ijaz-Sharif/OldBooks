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

public class Sell_books extends AppCompatActivity {
    // for signup
   private TextInputLayout book_name,book_price,mobile_num,owner_name;
    private RadioButton inter,matric;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_books);
        book_name=findViewById(R.id.book_name);
        book_price=findViewById(R.id.book_price);
        mobile_num=findViewById(R.id.mbl_num);
        owner_name=findViewById(R.id.o_name);
        inter=findViewById(R.id.inter);
        matric=findViewById(R.id.matric);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
    }
    public void submit(View v){
        if (TextUtils.isEmpty(book_name.getEditText().getText().toString().trim()) || TextUtils.isEmpty(mobile_num.getEditText().getText().toString().trim()) ||
                TextUtils.isEmpty(owner_name.getEditText().getText().toString().trim())) {
            Toast.makeText(this, "enter all data", Toast.LENGTH_LONG).show();
        } else {
            if (inter.isChecked()) {
                databaseReference = FirebaseDatabase.getInstance().getReference("sell").child(owner_name.getEditText().getText().toString().trim());
                databaseReference.child("book").setValue(book_name.getEditText().getText().toString().trim());
                databaseReference.child("catgare").setValue("Inter");
                databaseReference.child("Mail").setValue(firebaseUser.getEmail());
                databaseReference.child("Price").setValue(book_price.getEditText().getText().toString().trim());
                databaseReference.child("mobile").setValue(mobile_num.getEditText().getText().toString().trim());
                databaseReference.child("name").setValue(owner_name.getEditText().getText().toString().trim());
                Toast.makeText(this, "your book is ready for sold", Toast.LENGTH_LONG).show();
            } else {
                databaseReference = FirebaseDatabase.getInstance().getReference("sell").child(owner_name.getEditText().getText().toString().trim());
                databaseReference.child("book").setValue(book_name.getEditText().getText().toString().trim());
                databaseReference.child("catgare").setValue("Matric");
                databaseReference.child("Mail").setValue(firebaseUser.getEmail());
                databaseReference.child("Price").setValue(book_price.getEditText().getText().toString().trim());
                databaseReference.child("mobile").setValue(mobile_num.getEditText().getText().toString().trim());
                databaseReference.child("name").setValue(owner_name.getEditText().getText().toString().trim());
                Toast.makeText(this, "your book is ready for sold", Toast.LENGTH_LONG).show();
            }
        }
    }
}
