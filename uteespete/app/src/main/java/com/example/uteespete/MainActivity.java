package com.example.uteespete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uteespete.adapter.MainAdapter;
import com.example.uteespete.model.Arrayd;
import com.example.uteespete.model.User;
import com.example.uteespete.utils.itemClickSupport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;

    TextView nodata;
    RecyclerView aRecyclerView;
    RecyclerView.LayoutManager aLayoutManager;
    RecyclerView.Adapter aAdapter;
    ArrayList<User> userArrayList;
    public boolean abcd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nodata = findViewById(R.id.nodata);
        userArrayList = Arrayd.dataa;
        fab = findViewById(R.id.addUser);

        if (Arrayd.dataa.size()>0){
            nodata.setVisibility(View.GONE);
        }

        aRecyclerView = findViewById(R.id.recycler_view);
        aAdapter = new MainAdapter(userArrayList);
        aLayoutManager = new LinearLayoutManager(this);
        aRecyclerView.setLayoutManager(aLayoutManager);
        aRecyclerView.setAdapter(aAdapter);
        aRecyclerView.setHasFixedSize(true);

        itemClickSupport.addTo(aRecyclerView).setOnItemClickListener(new itemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(MainActivity.this, show_user.class);
                intent.putExtra(show_user.EXTRA_USERS, userArrayList.get(position));
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUser.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume(){
        super.onResume();
        this.abcd = false;
    }

    @Override
    public void onBackPressed(){
        if(abcd){
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(a);
        } else{
            Toast.makeText(MainActivity.this, "Press back again to exit.", Toast.LENGTH_SHORT).show();
        }
        this.abcd = true;
    }

}