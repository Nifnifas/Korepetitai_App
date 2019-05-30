package com.example.aplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {

    private TextView name, email, phone, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        name = findViewById(R.id.textView5);
        role = findViewById(R.id.textView6);
        phone = findViewById(R.id.textView12);
        email = findViewById(R.id.textView13);
        Intent intent = getIntent();
        String nname = intent.getStringExtra("name");
        String nsurname = intent.getStringExtra("surname");
        String nrole = intent.getStringExtra("role");
        String nphone = intent.getStringExtra("phone");
        String nemail = intent.getStringExtra("email");
        name.setText(nname + " " + nsurname);
        role.setText(nrole);
        phone.setText(nphone);
        email.setText(nemail);

    }
}
