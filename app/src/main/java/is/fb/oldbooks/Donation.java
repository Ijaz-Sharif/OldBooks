package is.fb.oldbooks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Donation extends Fragment {
    private RecyclerView recyclerView;
    private DonationAdapter donationAdapter;
    private DatabaseReference mdata;
    private List<DonateBook> mupload;
    private FloatingActionButton floatingActionButton;
    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_donation, container, false);
        recyclerView=view.findViewById(R.id.dobation_bok);
        floatingActionButton=view.findViewById(R.id.add_donation);
        editText=view.findViewById(R.id.donation_search);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Donate_books.class));
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mupload=new ArrayList<>();
        mdata= FirebaseDatabase.getInstance().getReference().child("donation");
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    DonateBook upload=new DonateBook(postSnapshot.child("book").getValue(String.class)
                            ,postSnapshot.child("catgare").getValue(String.class),
                            postSnapshot.child("mobile").getValue(String.class), postSnapshot.child("name").getValue(String.class)
                    );
                    mupload.add(upload);
                }
                donationAdapter=new DonationAdapter(getContext(),mupload);
                recyclerView.setAdapter(donationAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
       filter(editable.toString());
            }

            private void filter(String text) {
                ArrayList<DonateBook> filterlist=new ArrayList<>();
                for(DonateBook item: mupload){
                    if(item.getCate().toLowerCase().contains(text.toLowerCase())){
                        filterlist.add(item);
                    }
                }
                donationAdapter.filteredList(filterlist);
            }
        });
        setHasOptionsMenu(true);
        return view;
    }
    

}
