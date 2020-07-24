package is.fb.oldbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class Splsh extends AppCompatActivity {
    ProgressBar pb;
    int counter=0;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splsh);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent=new Intent(Splsh.this,Signup.class);
                    startActivity(intent);
                    finish();


                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            };
        thread.start();
        prog();
    }
    public  void prog(){
        pb=findViewById(R.id.p);
        Timer t=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);
            }
        };
        t.schedule(timerTask,0,30);
    }
}

