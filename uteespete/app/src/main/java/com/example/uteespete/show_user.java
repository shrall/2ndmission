package com.example.uteespete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.uteespete.model.Arrayd;
import com.example.uteespete.model.User;

import java.util.Iterator;

public class show_user extends AppCompatActivity {

    TextView uname, uage, uaddress;
    private User p;
    Button edit,delete;
    Toolbar toolbar;

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
        uage.setText(p.getUmur());
        uaddress.setText(p.getAlamat());
        toolbar = findViewById(R.id.tooladd);

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
                Iterator<User> iter = Arrayd.dataa.iterator();
                while (iter.hasNext()) {
                    User user = iter.next();
                    if (user.nama.equals(p.getNama())) {
                        iter.remove();
                    }
                }
                Intent intent = new Intent(show_user.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
