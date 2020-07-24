package is.fb.oldbooks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ImageView> {
    private Context context;
    private List<DonateBook> donation;
    String number;

    public DonationAdapter(Context c,List<DonateBook> donate){
        this.context=c;
        this.donation=donate;
    }

    @NonNull
    @Override
    public ImageView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.donation_profile,viewGroup,false);
        return new DonationAdapter.ImageView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageView imageView, int i) {
        final DonateBook donateBook=donation.get(i);
       imageView.name.setText(donateBook.getName());
        imageView.b_name.setText(donateBook.getB_name());
        imageView.catogre.setText(donateBook.getCate());
        imageView.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             number=   donation.get(imageView.getAdapterPosition()).getMobile();
                makePhoneCall();


            }
        });


    }

    @Override
    public int getItemCount() {
        return donation.size();
    }

    public void filteredList(ArrayList<DonateBook> filterlist) {
           donation=filterlist;
           notifyDataSetChanged();
    }

    public static class ImageView extends RecyclerView.ViewHolder{
        public TextView name,b_name,catogre;
        public CardView cardView;
        public ImageView(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.on);
            name=itemView.findViewById(R.id.doner_n);
            b_name=itemView.findViewById(R.id.b_namee);
            catogre=itemView.findViewById(R.id.cate);

        }
    }
    private void makePhoneCall(){
        if(ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{  android.Manifest.permission.CALL_PHONE},1);
        }
        else {
            String dial="tel:" +number ;
            context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==requestCode){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
        }
    }
}
