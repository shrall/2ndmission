package com.example.uteespete;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uteespete.model.Arrayd;
import com.example.uteespete.model.User;

import java.util.Iterator;

public class show_user extends AppCompatActivity {

    TextView uname, uage, uaddress;
    private User p;
    Button edit,delete;
    Toolbar toolbar;
    ProgressDialog progressDialog;

    static String EXTRA_USERS = "extra";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);
        if (getIntent().getParcelableExtra(EXTRA_USERS) != null) {
            p = getIntent().getParcelableExtra(EXTRA_USERS);
        }
        edit = findViewById(R.id.editbtn);
        delete = findViewById(R.id.deletebtn);
        uname = findViewById(R.id.userName);
        uage = findViewById(R.id.userAge);
        uaddress = findViewById(R.id.userAddress);

        uname.setText(p.getNama());
        uage.setText(p.getUmur()+" Years Old");
        uaddress.setText(p.getAlamat());
        toolbar = findViewById(R.id.tooladd);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(show_user.this);
        builder1.setMessage("Are you sure you want to delete this?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        progressDialog = new ProgressDialog(show_user.this);
                        progressDialog.show();
                        progressDialog.setContentView(R.layout.ini_loading);
                        Iterator<User> iter = Arrayd.dataa.iterator();
                        while (iter.hasNext()) {
                            User user = iter.next();
                            if (user.nama.equals(p.getNama())) {
                                iter.remove();
                            }
                        }
                        Toast.makeText(show_user.this, "Delete Success!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(show_user.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        final AlertDialog alert11 = builder1.create();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(show_user.this, MainActivity.class);
                    startActivity(intent);

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(show_user.this, AddUser.class);
                intent.putExtra(AddUser.EXTRA_USER, p);
                startActivity(intent);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert11.show();
            }
        });

    }
}
