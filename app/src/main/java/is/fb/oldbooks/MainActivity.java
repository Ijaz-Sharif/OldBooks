package is.fb.oldbooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    int a=1;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    TextView name,mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.may_viewpager);
        setSupportActionBar(toolbar);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        drawerLayout=findViewById(R.id.lay_out);
        ActionBarDrawerToggle toogle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        navigationView =findViewById(R.id.nav_view);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        // add curent name and email to the profile
        View headerView=navigationView.getHeaderView(0);
      name= headerView.findViewById(R.id.name_user);
      mail= headerView.findViewById(R.id.mail_user);
       mail.setText( firebaseUser.getEmail());
        name.setText(firebaseUser.getDisplayName());
    }
    @Override
    protected void onStart() {
        drawerLayout.closeDrawer(GravityCompat.START);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.sell :
                      startActivity(new Intent(MainActivity.this,Sell_books.class));
                        break;
                    case R.id.donate:
                        startActivity(new Intent(MainActivity.this,Donate_books.class));
                        break;
                    case R.id.log_out :
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this,Signup.class));
                        break;
                }
                return true;
            }
        });

        super.onStart();
    }

    private void setUpViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Donation(),"Donation");
        viewPagerAdapter.addFragment(new Sell(),"Sell");
        viewPager.setAdapter(viewPagerAdapter);
    }
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }}
}
