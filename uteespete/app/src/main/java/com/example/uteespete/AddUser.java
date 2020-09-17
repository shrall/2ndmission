package com.example.uteespete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.example.uteespete.model.Arrayd;
import com.example.uteespete.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Iterator;

public class AddUser extends AppCompatActivity implements TextWatcher {

    TextInputLayout iname, iage, iaddress;
    TextInputEditText tName, tAge, tAddress;
    Button button;
    String name, address, age;
    Toolbar toolbar;
    ProgressDialog progressDialog;

    private User p;
    static String EXTRA_USER = "extra";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);


        tName = findViewById(R.id.tname);
        tAge = findViewById(R.id.tage);
        tAddress = findViewById(R.id.taddress);
        iname = findViewById(R.id.input_fname);
        iage = findViewById(R.id.input_age);
        iaddress = findViewById(R.id.input_address);
        button = findViewById(R.id.button_tambah);
        iname.getEditText().addTextChangedListener(this);
        iage.getEditText().addTextChangedListener(this);
        iaddress.getEditText().addTextChangedListener(this);

        toolbar = findViewById(R.id.tooladd);

        if (getIntent().getParcelableExtra(EXTRA_USER) != null) {
            p = getIntent().getParcelableExtra(EXTRA_USER);
            tName.setText(p.getNama());
            tAge.setText(p.getUmur());
            tAddress.setText(p.getAlamat());
            toolbar.setTitle("Edit User");
            button.setText("Update User");

        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getParcelableExtra(EXTRA_USER) == null) {
                    Intent intent = new Intent(AddUser.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(AddUser.this, show_user.class);
                    intent.putExtra(show_user.EXTRA_USERS, p);
                    startActivity(intent);
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getParcelableExtra(EXTRA_USER) == null) {
                    progressDialog = new ProgressDialog(AddUser.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.ini_loading);
                    User user = new User(name, address, age);
                    Arrayd.dataa.add(new User(name, address, age));
                    Intent intent = new Intent(AddUser.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    if (!p.getNama().equals(name) || !p.getAlamat().equals(address) || !p.getUmur().equals(age)) {
                        Iterator<User> iter = Arrayd.dataa.iterator();
                        while (iter.hasNext()) {
                            User user = iter.next();
                            if (user.nama.equals(p.getNama())) {
                                iter.remove();
                            }
                        }
                        progressDialog = new ProgressDialog(AddUser.this);
                        progressDialog.show();
                        progressDialog.setContentView(R.layout.ini_loading);
                        User user = new User(name, address, age);
                        Arrayd.dataa.add(new User(name, address, age));
                        Intent intent = new Intent(AddUser.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(AddUser.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        name = iname.getEditText().getText().toString().trim();
        age = iage.getEditText().getText().toString().trim();
        address = iaddress.getEditText().getText().toString().trim();

        if (!name.isEmpty() && !address.isEmpty() && !age.isEmpty()) {
            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

