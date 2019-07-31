package com.example.aplikacija;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserEditActivity extends AppCompatActivity {

    SessionManager sessionManager;
    private EditText name, surname, email, phone;
    private Button btn_edit;
    private static String URL_EDIT = "http://10.151.24.151/aplikacija/edit_profile.php";
    public String mID, mRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        name = findViewById(R.id.editText7);
        surname = findViewById(R.id.editText9);
        email = findViewById(R.id.editText3);
        phone = findViewById(R.id.editText10);

        HashMap<String, String> user = sessionManager.getUserDetail();
        final String mName = user.get(sessionManager.NAME);
        String mEmail = user.get(sessionManager.EMAIL);
        String mSurname = user.get(sessionManager.SURNAME);
        String mPhone = user.get(sessionManager.PHONE);
        mID = user.get(sessionManager.ID);
        mRole = user.get(sessionManager.ROLE);

        try {
            name.setText(mName);
            email.setText(mEmail);
            phone.setText(mPhone);
            surname.setText(mSurname);
        } catch (NullPointerException e) {
            // Do something
        }

        btn_edit = findViewById(R.id.button);

        btn_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String Email = email.getText().toString().trim();
                String Name = name.getText().toString().trim();
                String Surname = surname.getText().toString().trim();
                String Phone = phone.getText().toString().trim();
                if(Email.isEmpty())
                    email.setError("Įveskite el. paštą");
                if(Name.isEmpty())
                    name.setError("Įveskite vardą");
                if(Surname.isEmpty())
                    surname.setError("Įveskite pavardę");
                if(Phone.isEmpty())
                    phone.setError("Įveskite telefono nr.");
                if(!Email.isEmpty() && !Name.isEmpty() && !Surname.isEmpty() && !Phone.isEmpty())
                    EditProfile();
            }
        });
    }

    private void EditProfile() {
        //loading.setVisibility(View.VISIBLE);
        btn_edit.setVisibility(View.GONE);

        final String name = this.name.getText().toString().trim();
        final String surname = this.surname.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String phone = this.phone.getText().toString().trim();
        final String role = mRole;
        final String id = mID;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(UserEditActivity.this, "Duomenys pakeisti sėkmingai!", Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(name, surname, email, id, phone, role);
                                startActivity(new Intent(UserEditActivity.this, IndexActivity.class));
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UserEditActivity.this, "Klaida! " + e.toString(), Toast.LENGTH_SHORT).show();
                            //loading.setVisibility(View.GONE);
                            btn_edit.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserEditActivity.this, "Klaida " + error.toString(), Toast.LENGTH_SHORT).show();
                        //loading.setVisibility(View.GONE);
                        btn_edit.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("surname", surname);
                params.put("phone", phone);
                params.put("email", email);
                params.put("id", mID);
                //params.put("password", password);
                //params.put("role", role);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /*public void updateClick(View view){
        Intent intent = new Intent(this, IndexActivity.class);
        startActivity(intent);
    }*/
}
