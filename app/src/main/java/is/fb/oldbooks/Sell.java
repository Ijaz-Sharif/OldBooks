package is.fb.oldbooks;


import android.app.Activity;
import android.content.Intent;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class Sell extends Fragment {

    private RecyclerView recyclerView;
    private SellAdapter sellAdapter;
    private DatabaseReference mdata;
    private List<SellBook> mupload;
    EditText editText;
    private FloatingActionButton floatingActionButton;
    public Sell() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sell, container, false);
        recyclerView=view.findViewById(R.id.sell_bok);
        floatingActionButton=view.findViewById(R.id.add_sell);
        editText=view.findViewById(R.id.sell_search);
        setHasOptionsMenu(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Sell_books.class));
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mupload=new ArrayList<>();
        mdata= FirebaseDatabase.getInstance().getReference().child("sell");
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    SellBook upload=new SellBook(postSnapshot.child("book").getValue(String.class),postSnapshot.child("Price").getValue(String.class)
                            ,postSnapshot.child("catgare").getValue(String.class),
                            postSnapshot.child("mobile").getValue(String.class), postSnapshot.child("name").getValue(String.class)
                    );
                    mupload.add(upload);
                }
                sellAdapter=new SellAdapter(getContext(),mupload);
                recyclerView.setAdapter(sellAdapter);
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
                ArrayList<SellBook> filterlist=new ArrayList<>();
                for(SellBook item: mupload){
                    if(item.getCate().toLowerCase().contains(text.toLowerCase())){
                        filterlist.add(item);
                    }
                }
                sellAdapter.filteredList(filterlist);
            }
        });
        return view;
    }

}
